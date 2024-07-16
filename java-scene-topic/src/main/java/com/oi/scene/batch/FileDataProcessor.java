package com.oi.scene.batch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * 分批处理文件数据，避免oom
 *
 */
@Service
public class FileDataProcessor {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final int BATCH_SIZE = 1000; // 每次处理的记录数

    public void processFile(String filePath) {
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(filePath))) {
            String line;
            List<String> batch = new ArrayList<>();
            while ((line = reader.readLine()) != null) {
                batch.add(line);
                if (batch.size() >= BATCH_SIZE) {
                    processBatch(batch);
                    batch.clear();
                }
            }
            // 处理最后一批数据
            if (!batch.isEmpty()) {
                processBatch(batch);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void processBatch(List<String> batch) {
        List<ProcessedData> processedDataList = new ArrayList<>();
        for (String line : batch) {
            // 业务处理逻辑
            ProcessedData processedData = processLine(line);
            processedDataList.add(processedData);
        }
        saveToDatabase(processedDataList);
    }

    private ProcessedData processLine(String line) {
        // 示例业务处理逻辑
        ProcessedData processedData = new ProcessedData();
        processedData.setField(line);
        return processedData;
    }

    private void saveToDatabase(List<ProcessedData> processedDataList) {
        String sql = "INSERT INTO processed_data (field) VALUES (?)";
        List<Object[]> batchArgs = new ArrayList<>();
        for (ProcessedData data : processedDataList) {
            batchArgs.add(new Object[] { data.getField() });
        }
        jdbcTemplate.batchUpdate(sql, batchArgs);
    }

    public static class ProcessedData {
        private String field;

        public String getField() {
            return field;
        }

        public void setField(String field) {
            this.field = field;
        }
    }
}
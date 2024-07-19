//package com.oi.scene.batch;
//
//import java.io.*;
//import java.nio.charset.StandardCharsets;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.concurrent.*;
//import java.util.stream.Stream;
//
//import org.apache.commons.io.FileUtils;
//import org.apache.commons.io.LineIterator;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//
//public class LargeFileProcessor {
//
//    private static final Logger logger = LogManager.getLogger(LargeFileProcessor.class);
//    private static final int LINES_PER_FILE = 100000;
//    private static final int BATCH_SIZE = 1000;
//
//    public static void main(String[] args) {
//        try {
//            splitFileAndRead("temp/test.txt");
//        } catch (Exception e) {
//            logger.error("Failed to process large file", e);
//        }
//    }
//
//    public static void splitFileAndRead(String largeFileName) throws Exception {
//        // 先将大文件拆分成小文件
//        List<File> fileList = splitLargeFile(largeFileName);
//
//        // 创建一个最大线程数为10，队列最大数为100的线程池
//        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
//                10, 10, 60L, TimeUnit.SECONDS, new LinkedBlockingQueue<>(100));
//        List<Future<?>> futureList = new ArrayList<>();
//
//        for (File file : fileList) {
//            Future<?> future = threadPoolExecutor.submit(() -> {
//                try (Stream<String> inputStream = Files.lines(file.toPath(), StandardCharsets.UTF_8);
//                     Connection conn = getConnection()) {
//                    List<String> batch = new ArrayList<>();
//                    inputStream.forEach(line -> {
//                        batch.add(line);
//                        if (batch.size() == BATCH_SIZE) {
//                            insertBatch(conn, batch);
//                            batch.clear();
//                        }
//                    });
//                    if (!batch.isEmpty()) {
//                        insertBatch(conn, batch);
//                    }
//                } catch (IOException | SQLException e) {
//                    logger.error("Error processing file: " + file.getName(), e);
//                }
//            });
//            futureList.add(future);
//        }
//
//        for (Future<?> future : futureList) {
//            future.get(); // 等待所有任务执行结束
//        }
//        threadPoolExecutor.shutdown();
//    }
//
//    private static List<File> splitLargeFile(String largeFileName) throws IOException {
//        LineIterator fileContents = FileUtils.lineIterator(new File(largeFileName), StandardCharsets.UTF_8.name());
//        List<String> lines = new ArrayList<>();
//        int num = 1;
//        List<File> files = new ArrayList<>();
//        while (fileContents.hasNext()) {
//            String nextLine = fileContents.nextLine();
//            lines.add(nextLine);
//            if (lines.size() == LINES_PER_FILE) {
//                createSmallFile(lines, num++, files);
//            }
//        }
//        if (!lines.isEmpty()) {
//            createSmallFile(lines, num, files);
//        }
//        return files;
//    }
//
//    private static void createSmallFile(List<String> lines, int num, List<File> files) throws IOException {
//        Path filePath = Files.createTempFile("smallfile_" + num, ".txt");
//        Files.write(filePath, lines, StandardCharsets.UTF_8);
//        files.add(filePath.toFile());
//        lines.clear(); // 清空lines列表以便重新使用
//    }
//
//    private static void insertBatch(Connection conn, List<String> batch) {
//        String insertSQL = "INSERT INTO my_table (column1, column2) VALUES (?, ?)";
//        try (PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {
//            for (String line : batch) {
//                String[] parts = line.split(",");
//                pstmt.setString(1, parts[0]);
//                pstmt.setString(2, parts[1]);
//                pstmt.addBatch();
//            }
//            pstmt.executeBatch();
//            conn.commit();
//        } catch (SQLException e) {
//            logger.error("Error inserting batch into database", e);
//        }
//    }
//
//    private static Connection getConnection() throws SQLException {
//        String jdbcUrl = "jdbc:mysql://localhost:3306/mydatabase";
//        String username = "username";
//        String password = "password";
//        Connection conn = DriverManager.getConnection(jdbcUrl, username, password);
//        conn.setAutoCommit(false); // 手动提交事务
//        return conn;
//    }
//}

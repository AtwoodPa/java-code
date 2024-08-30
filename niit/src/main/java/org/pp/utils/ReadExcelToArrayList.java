package org.pp.utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

public class ReadExcelToArrayList {
    public static void main(String[] args) {
        String excelFilePath = "/Users/panpan/Downloads/迎新统计详情导出文件.xlsx";
        ArrayList<String> idNumbers = new ArrayList<>();

        try (FileInputStream fis = new FileInputStream(new File(excelFilePath));
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheetAt(0); // 获取第一个Sheet
            for (Row row : sheet) {
                Cell cell = row.getCell(0); // 假设身份证号在第1列（索引为0）
                if (cell != null) {
                    idNumbers.add(cell.getStringCellValue());
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        // 每500个身份证号为一组
        int batchSize = 500;
        int totalBatches = (int) Math.ceil((double) idNumbers.size() / batchSize);

        for (int batch = 1; batch < totalBatches; batch++) {
            int start = batch * batchSize;
            int end = Math.min(start + batchSize, idNumbers.size());

            System.out.println("[");
            for (int i = start; i < end; i++) {
                System.out.print("    \"" + idNumbers.get(i) + "\"");
                if (i < end - 1) {
                    System.out.print(",\n");
                } else {
                    System.out.println();
                }
            }
            System.out.println("];");
        }
    }
}

package org.pp;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class ReadExcelToArrayList {
    public static void main(String[] args) {
        String excelFilePath = "/Users/panpan/Downloads/迎新统计详情导出文件.xlsx";
        String outputDirectory = "/Library/PP/Java/github/java-code/niit/js/2024"; // 指定输出目录
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

        for (int batch = 0; batch < totalBatches; batch++) {
            int start = batch * batchSize;
            int end = Math.min(start + batchSize, idNumbers.size());
            StringBuilder jsContent = new StringBuilder();

            jsContent.append("// niit迎新脚本\n\n");
            jsContent.append("// 身份证号数组\n");
            jsContent.append("var idArray = [\n");

            for (int i = start; i < end; i++) {
                jsContent.append("    \"").append(idNumbers.get(i)).append("\"");
                if (i < end - 1) {
                    jsContent.append(",\n");
                } else {
                    jsContent.append("\n");
                }
            }
            jsContent.append("];\n\n");
            jsContent.append("var inputElement = document.querySelector(\".bh-advancedQuery-quick-search-wrap .bh-form-control\");\n");
            jsContent.append("var index = 0;\n\n");
            jsContent.append("// 定义定时器函数\n");
            jsContent.append("var intervalId = setInterval(function() {\n");
            jsContent.append("    if (index >= idArray.length) {\n");
            jsContent.append("        clearInterval(intervalId);\n");
            jsContent.append("    } else {\n");
            jsContent.append("        inputElement.value = idArray[index];\n");
            jsContent.append("        index++;\n");
            jsContent.append("    }\n");
            jsContent.append("}, 5000);\n");

            String filePath = outputDirectory + "idNumbersBatch" + (batch + 1) + ".js";
            try (FileWriter fileWriter = new FileWriter(filePath)) {
                fileWriter.write(jsContent.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
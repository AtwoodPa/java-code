package com.oi;

import java.io.*;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 * 使用Files.lines()方法读取大文件
 *
 * @author supanpan
 * @date 2024/07/11
 */
public class ReadLargeFile {
    public static void main(String[] args) {
        // TODO: 读取大文件
//        String path = "/Library/PP/Java/github/java-code/files/largefile.txt";
//        // 创建一个8KB缓冲区
//        // byte[] buffer = new byte[8192];
//        try(Stream<String> lines = Files.lines(Paths.get(path))) {
//            // 遍历文件行
//            lines.forEach(System.out::println);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        testFileChannelTime();
    }

    // 计算Files.lines读取文件的耗时方法 => 耗时：1850ms
    public static void testFilesLinesTime() {
        String path = "/Library/PP/Java/github/java-code/files/largefile.txt";
        long start = System.currentTimeMillis();
        try(Stream<String> lines = Files.lines(Paths.get(path))) {
            // 遍历文件行
            lines.forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.printf("耗时：%dms\n", end - start);
    }
    // 计算BufferedReader + Stream 读取文件的耗时方法 => 耗时：1779ms
    public static void testBufferedReaderTime() {
        String path = "/Library/PP/Java/github/java-code/files/largefile.txt";
        long start = System.currentTimeMillis();
        try(BufferedReader reader = new BufferedReader(new FileReader(path))) {
            reader.lines().forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.printf("耗时：%dms\n", end - start);
    }

    // 计算 使用 FileInputStream 和 BufferedInputStream 逐块读取文件 的耗时方法 => 耗时：51ms
    public static void testFileInputStreamTime() {
        String path = "/Library/PP/Java/github/java-code/files/largefile.txt";
        // 创建一个8KB缓冲区
        byte[] buffer = new byte[8192];
        long start = System.currentTimeMillis();

        try(BufferedInputStream bis = new BufferedInputStream(new FileInputStream(path))) {
            int len;
            while ((len = bis.read(buffer)) != -1) {
                System.out.println("读取了" + len + "字节");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.printf("耗时：%dms\n", end - start);
    }
    // 计算使用 FileChannel 和 MappedByteBuffer 读取大文件 的耗时方法 => 耗时：44373ms
    public static void testFileChannelTime() {
        String path = "/Library/PP/Java/github/java-code/files/largefile.txt";
        long start = System.currentTimeMillis();
        try(RandomAccessFile raf = new RandomAccessFile(path, "r")) {
            FileChannel channel = raf.getChannel();
            MappedByteBuffer buffer = channel.map(FileChannel.MapMode.READ_ONLY, 0, channel.size());
            // 遍历buffer
            for (int i = 0; i < buffer.limit(); i++) {
                buffer.get(i);  // 模拟读取操作
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.printf("耗时：%dms\n", end - start);
    }

}

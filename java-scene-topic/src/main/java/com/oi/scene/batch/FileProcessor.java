//package com.oi.scene.batch;
//
//import lombok.SneakyThrows;
//import org.apache.commons.io.FileUtils;
//import org.apache.commons.io.LineIterator;
//
//import java.io.File;
//import java.nio.charset.StandardCharsets;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.concurrent.*;
//
//public class FileProcessor {
//
//    @SneakyThrows
//    public static void readInApacheIOWithThreadPool() {
//        // 创建一个最大线程数为10，队列最大数为100的线程池
//        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10, 10, 60L, TimeUnit.SECONDS, new LinkedBlockingDeque<>(100));
//
//        // 使用 Apache 的方式逐行读取数据
//        try (LineIterator fileContents = FileUtils.lineIterator(new File("temp/test.txt"), StandardCharsets.UTF_8.name())) {
//            List<String> lines = new ArrayList<>();
//            while (fileContents.hasNext()) {
//                String nextLine = fileContents.nextLine();
//                lines.add(nextLine);
//
//                // 读取到十万行时，拆分成两个50000行的列表，交给异步线程处理
//                if (lines.size() == 100000) {
//                    processLinesInBatches(lines, threadPoolExecutor);
//                    lines.clear(); // 清除已处理的内容
//                }
//            }
//
//            // 处理剩余的行
//            if (!lines.isEmpty()) {
//                processTask(lines);
//            }
//        } finally {
//            threadPoolExecutor.shutdown();
//        }
//    }
//
//    private static void processLinesInBatches(List<String> lines, ThreadPoolExecutor threadPoolExecutor) throws InterruptedException, ExecutionException {
//        List<List<String>> partitions = Lists.partition(lines, 50000);
//        List<Future<?>> futureList = new ArrayList<>();
//
//        for (List<String> partition : partitions) {
//            Future<?> future = threadPoolExecutor.submit(() -> processTask(partition));
//            futureList.add(future);
//        }
//
//        // 等待所有任务执行结束，防止OOM
//        for (Future<?> future : futureList) {
//            future.get();
//        }
//    }
//
//    private static void processTask(List<String> lines) {
//        for (String line : lines) {
//            // 模拟业务执行
//            try {
//                TimeUnit.MILLISECONDS.sleep(10L);
//            } catch (InterruptedException e) {
//                Thread.currentThread().interrupt();
//                e.printStackTrace();
//            }
//        }
//    }
//}

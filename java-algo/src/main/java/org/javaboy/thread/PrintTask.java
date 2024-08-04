package org.javaboy.thread;

/**
 * 三个线程打印字符，每个线程打印3个字符，打印顺序为abcabcabc
 * @author supanpan
 * @date 2024/08/04
 */
public class PrintTask implements Runnable {
    private char toPrint;
    private int count;
    private static final Object lock = new Object();
    private static int currentIndex = 0; // 假设这是一个静态变量，用于跟踪当前应打印的字符索引

    public PrintTask(char toPrint, int count) {
        this.toPrint = toPrint;
        this.count = count;
    }

    @Override
    public void run() {
        for (int i = 0; i < count; i++) {
            synchronized (lock) {
                // 等待直到当前索引是我们应该打印的字符
                while (currentIndex % 3 != (toPrint - 'a')) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        return;
                    }
                }

                // 打印字符
                System.out.print(toPrint);

                // 更新索引并通知其他线程
                currentIndex++;
                lock.notifyAll();
            }
        }
    }

    public static void main(String[] args) {
        Thread threadA = new Thread(new PrintTask('a', 3));
        Thread threadB = new Thread(new PrintTask('b', 3));
        Thread threadC = new Thread(new PrintTask('c', 3));

        threadA.start();
        threadB.start();
        threadC.start();
    }
}

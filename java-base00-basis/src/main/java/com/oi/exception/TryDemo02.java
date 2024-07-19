package com.oi.exception;

/**
 *
 * @author supanpan
 * @date 2024/07/19
 */
public class TryDemo02 {
    public static void main(String[] args) {
        System.out.println(test());
    }

    /**
     * 执行结果2
     * try返回前执行finally
     * 在finally中直接return了，不走try了
     * @return
     */
    public static int test(){
        try {
            return 1;
        } finally {
            return 2;
        }
    }
}

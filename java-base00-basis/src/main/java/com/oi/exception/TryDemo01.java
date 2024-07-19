package com.oi.exception;

/**
 * @author supanpan
 * @date 2024/07/19
 */
public class TryDemo01 {
    public static void main(String[] args) {
        System.out.println(test());
    }


    /**
     *
     * 在return之前会先执行finally语句块
     * 所以是先输出finally里的3
     * 再输出return的1
     *
     */
    public static int test(){
        try {
            return 1;
        }catch (Exception e){
            return 2;
        }finally {
            System.out.println("3");
        }
    }
}

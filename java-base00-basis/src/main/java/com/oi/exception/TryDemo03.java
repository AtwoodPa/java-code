package com.oi.exception;

/**
 * @author supanpan
 * @date 2024/07/19
 */
public class TryDemo03 {
    public static void main(String[] args) {
        System.out.println(test());
    }
    public static int test(){
        int i = 0;
        try{
            i = 2;
            return i;
        }finally {
            i = 3;
            // return i;
        }
    }
}

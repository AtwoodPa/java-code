package com.oi.interger;

/**
 * String转Integer
 *
 * @author supanpan
 * @date 2024/07/19
 */
public class StringConvertInteger {
    public static void main(String[] args) {
        String str = "123";
        int i = Integer.parseInt(str);
        Integer i1 = Integer.valueOf(str);
        System.out.println("i = " + i);
        System.out.println("i1 = " + i1);
    }
}

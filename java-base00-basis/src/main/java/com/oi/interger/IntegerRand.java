package com.oi.interger;

/**
 * Integer缓存池，默认范围是 -128 到 127
 * 通过JVM-XX：AutoBoxCacheMax=
 * 来修改缓存的最大值，最小值改不了
 * @author supanpan
 * @date 2024/07/19
 */
public class IntegerRand {
    public static void main(String[] args) {
        Integer a = 127;
        Integer b = 127;
        Integer b1 = new Integer(127);
        // 在缓冲池范围内，为true
        System.out.println(a == b);
        // 不是同一个对象，为false
        System.out.println(b == b1);
        Integer c = 128;
        Integer d = 128;
        // 不在缓冲池范围内，为false
        System.out.println(c == d);
    }
}

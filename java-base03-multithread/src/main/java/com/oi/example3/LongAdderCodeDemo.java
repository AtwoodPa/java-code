package com.oi.example3;

import java.util.concurrent.atomic.LongAdder;

/**
 * @author supanpan
 * @date 2024/07/13
 */
public class LongAdderCodeDemo {
    public static void main(String[] args) {
        LongAdder longAdder = new LongAdder();
        longAdder.add(10);
        System.out.println(longAdder);
    }
}

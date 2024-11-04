package com.oi.map.computeIfAbsent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Map.computeIfAbsent 使用案例
 * @author karl
 */
public class ComputeIfAbsentExample {
    public static void main(String[] args) {
        Map<String, List<Integer>> courseScores = new HashMap<>();
        // 添加成绩
        addScore(courseScores, "Math", 85);
        addScore(courseScores, "Science", 90);
        addScore(courseScores, "Math", 92);
        addScore(courseScores, "English", 78);

        // 输出结果
        courseScores.forEach((course, scores) -> {
            System.out.println(course + ": " + scores);
        });
    }
    public static void addScore(Map<String, List<Integer>> map, String course, int score) {
        // 使用computeIfAbsent进行初始化
        map.computeIfAbsent(course, k -> new ArrayList<>()).add(score);
    }

    public static void addScoreOld(Map<String, List<Integer>> map, String course, int score){
        if (!map.containsKey(course)){
            map.put(course, new ArrayList<>());
        }
        map.get(course).add(score);
    }
}

package org.javaboy.strings;

/**
 * 给你一份『词汇表』（字符串数组） words 和一张『字母表』（字符串） chars。
 * 假如你可以用 chars 中的『字母』（字符）拼写出 words 中的某个『单词』，那么我们就认为你掌握了这个单词。
 * 注意：每次拼写（指拼写词汇表中的一个单词）时，chars 中的每个字母都只能用一次。
 * 返回词汇表 words 中你掌握的所有单词的 长度之和。
 * 示例 1：
 * 输入：words = ["cat","bt","hat","tree"], chars = "atach"
 * 输出：6
 * 解释：
 * 我们可以使用 6 个字母拼写出词汇表中的 6 个单词，如下：
 * 字母表：a, t, c, h, at, ch
 * 单词表：cat, hat, tree
 * 我们可以得到长度为 6 的单词 cathat。
 * 示例 2：
 * 输入：words = ["hello","world","leetcode"], chars = "welldonehoneyr"
 * 输出：10
 * 解释：
 * 我们可以使用 7 个字母拼写出词汇表中的 3 个单词，如下：
 * 字母表：e, l, o
 * 单词表：hello, world
 * 我们可以得到长度为 10 的单词 helloledhoneyr。
 * 提示：
 * 0 <= words.length <= 10^4
 * 1 <= words[i].length, chars.length <= 100
 * 所有字符串的字符均由小写英文字母组成
 * 题目数据保证每个单词在词汇表里唯一
 *
 * @author supanpan
 * @date 2024/08/04
 */
public class WordLengthSum {

    public static int countCharacters(String[] words, String chars) {
        // 将字母表转换为字符频率映射
        int[] charCounts = new int[26];
        for (char c : chars.toCharArray()) {
            charCounts[c - 'a']++;
        }

        int sum = 0;
        // 遍历词汇表中的每个单词
        for (String word : words) {
            int[] wordCounts = new int[26];
            boolean canForm = true;
            // 统计单词中每个字符的频率
            for (char c : word.toCharArray()) {
                wordCounts[c - 'a']++;
            }
            // 检查是否能用字母表中的字母拼写出该单词
            for (int i = 0; i < 26; i++) {
                if (wordCounts[i] > charCounts[i]) {
                    canForm = false;
                    break;
                }
            }
            // 如果可以拼写，则累加单词长度
            if (canForm) {
                sum += word.length();
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        String[] words = {"cat", "bt", "hat", "tree"};
        String chars = "atach";
        System.out.println(countCharacters(words, chars)); // 输出: 6

        words = new String[]{"hello", "worlo", "leetcode"};
        chars = "welldonehoneyr";
        System.out.println(countCharacters(words, chars)); // 输出: 10 (注意：示例中AA可能是个错误，这里假设没有AA)
    }
}

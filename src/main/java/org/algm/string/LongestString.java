package org.algm.string;

import java.util.HashMap;
import java.util.HashSet;
import java.util.TreeSet;

public class LongestString {
    /**
     * 获取最长不重复字符串的长度
     * @param message
     * @return
     */
    public static int getLongestStringLength(String message) {
        int prev = 0;  //保存最长不重复字符串的起始位置
        int maxLen = 0; //最长不重复字符串的长度
        HashMap<Character, Integer> map = new HashMap(); //存放字符串中的字符对应的下标
        for (int i = 0; i < message.length(); i++) {
            Character c = message.charAt(i);
            if(map.containsKey(c)) {
                //如果map中已经包含当前字符了，为了保证字符串的收尾字符不重复，使字符串的起始位置向后移一个位置
                prev = map.get(c) + 1;
            }
            map.put(c, i); //更新下标，不重复字符串的结束位置后移一位
            int currLen = i - prev + 1; //当前位置下，字符串的长度
            maxLen = Math.max(maxLen, currLen); //比较当前字符串的长度和已有的最长长度，取大
        }
        return maxLen;
    }

    /**
     * 先获取不重复子串的最大长度，根据这个长度逐一获取子串，取出所有没有重复字符的子串
     * @param message
     * @return
     */
    public static TreeSet<String> getLongestNonRepetitiveSubString(String message) {
        int maxLen = getLongestStringLength(message);
        int strLen = message.length();
        TreeSet<String> set = new TreeSet<String>();
        for (int i = 0; i < strLen - maxLen + 1; i++) {
            String subStr = message.substring(i, i + maxLen);
            if(checkRepetitive(subStr)) {
                set.add(subStr);
            }
        }
        return set;
    }

    /**
     * 通过将字符放入hashset，判断字符是否有重复
     * @param message
     * @return
     */
    public static boolean checkRepetitive(String message) {
        if(message == null) {
            return false;
        }
        HashSet<Character> set = new HashSet<Character>(message.length());
        for (int i = 0; i < message.length(); i++) {
            if(set.contains(message.charAt(i))) {
                return false;
            }
            set.add(message.charAt(i));
        }
        return true;
    }
    public static void main(String[] args) {
        String message = "abcafghicfkd";
        int maxLen = getLongestStringLength(message);
        System.out.println("Max longest unrepeatable string length:" + maxLen);
        TreeSet<String> maxSub = getLongestNonRepetitiveSubString(message);
        System.out.println("Max longest unrepeatable string : " + maxSub.toString());
    }
}

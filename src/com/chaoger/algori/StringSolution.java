package com.chaoger.algori;

import java.util.*;

public class StringSolution {


    /**
     * 无重复字符的最长子串:给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
        Set<Character>  set = new HashSet<>();
        int n = s.length();
        int res=0,i=0,j=0;
        while (i<n&&j<n){
            if(!set.contains(s.charAt(j))){
                set.add(s.charAt(j++));
                res = Math.max(res,j-i);
            }else {
                set.remove(s.charAt(i++));
            }
        }

        return res;

    }

    /**
     * 最长回文子串:给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
     * @param s
     * @return
     */
    public String longestPalindrome(String s) {
        if(s == null || s.length()<1) return "";
        int start = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            int len1 = expandAroundCenter(s,i,i);
            int len2 = expandAroundCenter(s,i,i+1);
            int len = Math.max(len1,len2);
            if(len>end-start){
                start = i-(len-1)/2;
                end = i+len/2;
            }

        }
        return s.substring(start,end+1);


    }

    private int expandAroundCenter(String s, int left, int right) {
        while (left>=0&&right<s.length()&&s.charAt(left)==s.charAt(right)){
            left--;
            right++;
        }
        return right-left-1;
    }
}

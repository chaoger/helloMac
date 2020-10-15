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




    //正则表达式匹配




    /**
     * 电话号码的字母组合
     * @param digits
     * @return
     */


    public List<String> letterCombinations(String digits) {
        List<String> output = new ArrayList<>();
        if(digits.length()==0) return output;

        Map<String, String> phone = new HashMap<String, String>() {{
            put("2", "abc");
            put("3", "def");
            put("4", "ghi");
            put("5", "jkl");
            put("6", "mno");
            put("7", "pqrs");
            put("8", "tuv");
            put("9", "wxyz");
        }};

        backTraceLetterCombinations(phone, output, new StringBuilder(), digits);

        return output;
    }

    private void backTraceLetterCombinations(Map<String, String> phone,List<String> output,StringBuilder res,String digits){

        if(digits.length()==0){
            output.add(res.toString());
            return;
        }
        String letters = phone.get(digits.substring(0, 1));
        for (int i = 0; i < letters.length(); i++) {
            res.append(letters.charAt(i));
            backTraceLetterCombinations(phone,output,res,digits.substring(1));
            res.deleteCharAt(res.length()-1);
        }
    }


    /**
     * 有效的括号:给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
     *
     * 有效字符串需满足：左括号必须用相同类型的右括号闭合。左括号必须以正确的顺序闭合。
     * @param s
     * @return
     */
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if(c=='('||c=='['||c=='{'){
                stack.push(s.charAt(i));
            }else {
                if(stack.isEmpty()){
                    return false;
                }
                switch (c){

                    case ')':
                        if(stack.pop()=='('){
                            continue;
                        }else {
                            return false;
                        }

                    case ']':
                        if(stack.pop()=='['){
                            continue;
                        }else {
                            return false;
                        }

                    case '}':
                        if(stack.pop()=='{'){
                            continue;
                        }else {
                            return false;
                        }

                    default:
                        return false;

                }
            }
        }

        return stack.isEmpty();
    }


    /**
     * 括号生成:数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
     * @param n
     * @return
     */
    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        backTraceGenerateParenthesis(res,new StringBuilder(),0,0,n);
        return res;


    }

    private void backTraceGenerateParenthesis(List<String> res,StringBuilder cur,int open,int close,int max){
        if(cur.length()==max*2){
            res.add(cur.toString());
            return;
        }
        if(open<max){
            cur.append("(");
            backTraceGenerateParenthesis(res, cur, open+1, close, max);
            cur.deleteCharAt(cur.length()-1);
        }
        if(close<open){
            cur.append(")");
            backTraceGenerateParenthesis(res, cur, open, close+1, max);
            cur.deleteCharAt(cur.length()-1);
        }
    }


    /**
     * 字母异位词分组:给定一个字符串数组，将字母异位词组合在一起。字母异位词指字母相同，但排列不同的字符串。
     * @param strs
     * @return
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String,List<String>> map = new HashMap<>();
        int[] count = new int[26];
        for (int i = 0; i < strs.length; i++) {
            Arrays.fill(count,0);
            for (char c:strs[i].toCharArray()) {
                count[c-'a']++;
            }
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < 26; j++) {
                sb.append(count[j]+"#");
            }
            String key = sb.toString();
            if(!map.containsKey(key)){
                map.put(key,new ArrayList<>());
            }
            map.get(key).add(strs[i]);

        }
        return new ArrayList<>(map.values());

    }

}

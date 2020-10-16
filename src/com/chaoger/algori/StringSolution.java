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



    /**
     * 正则表达式匹配:给你一个字符串 s 和一个字符规律 p，
     * 请你来实现一个支持 '.' 和 '*' 的正则表达式匹配。
     * @param s
     * @param p
     * @return
     */
    public boolean isMatch(String s, String p) {
        int m = s.length();
        int n = p.length();
        boolean[][] dp = new boolean[m+1][n+1];
        dp[0][0] = true;
        for (int i = 1; i < n; i++) {
            if(p.charAt(i)=='*'&&dp[0][i-1]){
                dp[0][i+1] = true;
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if(p.charAt(j)=='.'||s.charAt(i)==p.charAt(j)){
                    dp[i+1][j+1] = dp[i][j];
                }

                if(j>0&&p.charAt(j)=='*'){
                    if(p.charAt(j-1)!=s.charAt(i)&&p.charAt(j-1)!='.'){
                        dp[i+1][j+1] = dp[i+1][j-1];
                    }else {
                        //分别 匹配0个、1个或者多个
                        dp[i+1][j+1] = dp[i+1][j-1] ||dp[i+1][j] || dp[i][j+1];
                    }
                }
            }
        }
        return dp[m][n];
    }



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
     * 最长有效括号:给定一个只包含 '(' 和 ')' 的字符串，找出最长的包含有效括号的子串的长度。
     * @param s
     * @return
     */
    public int longestValidParentheses(String s) {
        int n = s.length();
        if(n<2) return 0;
        int[] dp = new int[n];
        int max = 0;
        for (int i = 1; i < n; i++) {
            char c0 = s.charAt(i);
            char c1 = s.charAt(i-1);
            if(c0==')'){
                if(c1=='('){
                    dp[i] = (i >= 2 ? dp[i - 2] : 0) + 2;
                }else if(i-dp[i-1]>0&&s.charAt(i-dp[i-1]-1) =='('){
                    dp[i] =dp[i-1]+((i-dp[i-1]>=2)?dp[i-dp[i-1]-2]:0)+2;
                }
            }
            max = Math.max(max,dp[i]);

        }
        return max;

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



    /**
     * 最小覆盖子串:给你一个字符串 S、一个字符串 T 。
     * 请你设计一种算法，可以在 O(n) 的时间复杂度内，从字符串 S 里面找出：包含 T 所有字符的最小子串。
     * @param s
     * @param t
     * @return
     */
    public String minWindow(String s, String t) {
        if(s.length()==0||t.length()==0) return "";
        Map<Character,Integer> dictT = new HashMap<>();
        for (int i = 0; i < t.length(); i++) {
            int count = dictT.getOrDefault(t.charAt(i), 0)+1;
            dictT.put(t.charAt(i),count);
        }
        int[] ans = new int[]{-1,0,0};
        int r = 0,l =0,formed=0;
        int required = dictT.size();
        Map<Character,Integer> window = new HashMap<>();
        while (r<s.length()){
            char c = s.charAt(r);
            int count = window.getOrDefault(c, 0)+1;
            window.put(c,count);
            if(dictT.containsKey(c)&&dictT.get(c)==count){
                formed++;
            }
            while (l<=r&&formed==required){
                if(ans[0]==-1||r-l+1<ans[0]){
                    ans[0] = r-l+1;
                    ans[1] = l;
                    ans[2] = r;
                }
                char c1 = s.charAt(l);
                int count1 = window.get(c1)-1;
                window.put(c1,count1);
                if(dictT.containsKey(c1)&&dictT.get(c1)>count1){
                    formed--;
                }

                l++;
            }
            r++;
        }


        return ans[0]==-1?"":s.substring(ans[1],ans[2]+1);
    }



    /**
     * 回文子串:给定一个字符串，你的任务是计算这个字符串中有多少个回文子串。
     * 具有不同开始位置或结束位置的子串，即使是由相同的字符组成，也会被视作不同的子串。
     * @param s
     * @return
     */
    public int countSubstrings(String s) {
        int length = s.length();
        int ans = 0;
        for (int i = 0; i < length*2; i++) {
            int left = i/2;
            int right = (i+1)/2;
            while (left>=0&&right<length&&s.charAt(left)==s.charAt(right)){
                ans++;
                left--;
                right++;
            }
        }
        return ans;

    }



}

package com.chaoger.algori;

import java.util.*;

public class HashSolution {


    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }


    /**
     * 二叉树的中序遍历
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();

        return res;

    }

    private void inorder(TreeNode root,List<Integer> res){
        if(root==null){
            return;
        }
        inorder(root.left,res);
        res.add(root.val);
        inorder(root.right,res);
    }

    /**
     * 只出现一次的数字:给定一个非空整数数组，除了某个元素只出现一次以外，
     * 其余每个元素均出现两次。找出那个只出现了一次的元素。
     *
     * @param nums
     * @return
     */
    public int singleNumber(int[] nums) {
        int res = nums[0];
        for (int i = 1; i < nums.length; i++) {
            res = res ^ nums[i];
        }

        return res;

    }




    /**
     * 前 K 个高频元素:给定一个非空的整数数组，返回其中出现频率前 k 高的元素。
     * @param nums
     * @param k
     * @return
     */
    public int[] topKFrequent(int[] nums, int k) {
        int[] res = new int[k];
        Map<Integer,Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num,map.getOrDefault(num,0)+1);
        }
        PriorityQueue<int[]> queue = new PriorityQueue<>(new Comparator<int[]>() {
            public int compare(int[] m, int[] n) {
                return m[1] - n[1];
            }
        });
        for (Integer num : map.keySet()) {
            int count = map.get(num);
            if(queue.size()==k){
                if(count>queue.peek()[1]){
                    queue.poll();
                    queue.offer(new int[]{num,count});
                }
            }else {
                queue.offer(new int[]{num,count});
            }
        }

        for (int i = 0;i<k;i++) {
            res[i] = queue.poll()[0];
        }

        return res;

    }



    /**
     * 给定一个字符串 s 和一个非空字符串 p，找到 s 中所有是 p 的字母异位词的子串，返回这些子串的起始索引。
     * 字符串只包含小写英文字母，并且字符串 s 和 p 的长度都不超过 20100。
     *
     * 说明：
     * 字母异位词指字母相同，但排列相同或不同的字符串。
     * 不考虑答案输出的顺序。
     * @param s
     * @param p
     * @return
     */
    public List<Integer> findAnagrams(String s, String p) {
        char[] arrS = s.toCharArray();
        char[] arrP = p.toCharArray();
        List<Integer> ans = new ArrayList<>();

        int[] needs = new int[26];
        int[] window = new int[26];

        int left = 0;
        int right = 0;

        for (char c : arrP) {
            needs[c-'a']++;
        }
        while (right<arrS.length){
            int curR = arrS[right]-'a';
            right++;
            window[curR]++;
            while (window[curR]>needs[curR]){
                int curL = arrS[left]-'a';
                window[curL]--;
                left++;
            }
            if(right-left==arrP.length){
                ans.add(left);
            }
        }

        return ans;

    }



    /**
     * 每日温度：请根据每日 气温 列表，重新生成一个列表。对应位置的输出为：要想观测到更高的气温，
     * 至少需要等待的天数。如果气温在这之后都不会升高，请在该位置用 0 来代替。
     *
     *
     * @param T
     * @return
     */
    public int[] dailyTemperatures(int[] T) {
        int n = T.length;
        int[] res = new int[n];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < n; i++) {
            if(stack.isEmpty()||T[i]<=T[stack.peek()]){
                stack.push(i);
            }else {
                while (!stack.isEmpty()&&T[i]<=T[stack.peek()]){
                    res[stack.peek()] = i-T[stack.pop()];
                }
                stack.push(i);
            }
        }
        while (!stack.isEmpty()){
            res[stack.pop()] = 0;
        }
        return res;
    }
}

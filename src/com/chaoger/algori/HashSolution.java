package com.chaoger.algori;

import java.util.*;

public class HashSolution {


    //二叉树的中序遍历


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



    //前 K 个高频元素

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


    //找到字符串中所有字母异位词



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

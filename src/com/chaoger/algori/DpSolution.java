package com.chaoger.algori;

import java.util.Arrays;

public class DpSolution {


    /**
     * 打家劫舍:你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，
     * 影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
     * 给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。
     * @param nums
     * @return
     */
    public int rob(int[] nums) {
        int n =  nums.length;
        if(n==0) return 0;
        if(n==1) return nums[0];
        int preMax = nums[0];
        int pre = nums[1];
        int cur;
        for (int i = 2; i <n ; i++) {
            cur = nums[i]+preMax;
            preMax = Math.max(preMax,pre);
            pre = cur;
        }

        return Math.max(preMax,pre);

    }

    /**
     * 零钱兑换:给定不同面额的硬币 coins 和一个总金额 amount。编写一个函数来计算可以凑成总金额所需的最少的硬币个数。如果没有任何一种硬币组合能组成总金额，返回 -1。
     *
     *
     * @param coins
     * @param amount
     * @return
     */
    public int coinChange(int[] coins, int amount) {
        //公式：count[s] = Min(count[s-coinsI])+1
        int[] count = new int[amount+1];
        Arrays.fill(count,-1);
        count[0] = 0;
        for (int i = 1; i <=amount; i++) {
            int min = Integer.MAX_VALUE;
            for (int j = 0; j <coins.length; j++) {
                if(i-coins[j]>=0&&count[i-coins[j]]>=0){
                    min = Math.min(min,count[i-coins[j]]);
                }
            }
            if(min !=Integer.MAX_VALUE ){
                count[i] = min+1;
            }

        }
        return count[amount];
    }

}

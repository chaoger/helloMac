package com.chaoger.algori;

import java.util.Arrays;

public class DpSolution {


    public static void main(String[] args) {
        int[] arr = new int[]{3,1,5,8};
        maxCoins(arr);
    }
    /**
     * 爬楼梯:假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
     *
     * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
     * @param n
     * @return
     */
    public int climbStairs(int n) {
        //公式：f[n] = f[n-1]+f[n-2]
        if(n==1) return 1;
        int first = 1;
        int second = 2;
        for (int i = 3; i <= n; i++) {
            int third = first+second;
            first = second;
            second = third;
        }
        return second;

    }

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
     * 戳气球:有 n 个气球，编号为0 到 n-1，每个气球上都标有一个数字，这些数字存在数组 nums 中。
     *
     * 现在要求你戳破所有的气球。如果你戳破气球 i ，就可以获得 nums[left] * nums[i] * nums[right] 个硬币。 
     * 这里的 left 和 right 代表和 i 相邻的两个气球的序号。注意当你戳破了气球 i 后，气球 left 和气球 right 就变成了相邻的气球。
     *
     * 求所能获得硬币的最大数量。
     * @param nums
     * @return
     */
    public static int maxCoins(int[] nums) {
        int n = nums.length;
        int[][] dp = new int[n+2][n+2];
        int[] arr = new int[n+2];
        arr[0] =1;
        arr[n+1] = 1;
        for (int i = 0; i < n; i++) {
            arr[i+1] = nums[i];
        }
        for (int i = n-1; i >=0; i--) {
            for (int j = i+2; j <=n+1 ; j++) {
                for (int k = i+1; k < j; k++) {
                    int sum = arr[i] * arr[k] * arr[j];
                    sum += dp[i][k] + dp[k][j];
                    dp[i][j] = Math.max(dp[i][j], sum);
                }
            }
        }

        return dp[0][n+1];
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

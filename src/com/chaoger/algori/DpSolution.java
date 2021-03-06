package com.chaoger.algori;

import java.util.Arrays;
import java.util.*;

/**
 * 动态规划 14题
 */
public class DpSolution {

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
     * 编辑距离:给你两个单词 word1 和 word2，请你计算出将 word1 转换成 word2 所使用的最少操作数 。
     * 你可以对一个单词进行如下三种操作：
     * 插入一个字符
     * 删除一个字符
     * 替换一个字符
     * @return
     */
    public int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        int[][] dp = new int[m+1][n+1];
        for (int i = 1; i <=m; i++) {
            dp[i][0] = i;
        }
        for (int i = 1; i <=n; i++) {
            dp[0][i] = i;
        }
        for (int i = 1; i <=m; i++) {
            for (int j = 1; j <=n ; j++) {
                int left = dp[i-1][j]+1;
                int down = dp[i][j-1]+1;
                int left_down = dp[i-1][j-1];
                if(word1.charAt(i-1)!=word2.charAt(j-1)){
                    left_down++;

                }
                dp[i][j] = Math.min(left_down,Math.min(left,down));

            }
        }
        return dp[m][n];


    }


    /**
     * 不同的二叉搜索树
     * @return
     */
    public int numTrees(int n) {
        int dp[] = new int[n+1];
        dp[0] =1;
        dp[1] = 1;
        for (int i = 2; i <=n; i++) {
            for (int j = 1; j <=i; j++) {
                dp[i] +=dp[j-1]*dp[i-j];
            }

        }
        return dp[n];
    }



    /**
     * 单词拆分:给定一个非空字符串 s 和一个包含非空单词的列表 wordDict，
     * 判定 s 是否可以被空格拆分为一个或多个在字典中出现的单词。
     * 拆分时可以重复使用字典中的单词。
     * 你可以假设字典中没有重复的单词。
     * @param s
     * @param wordDict
     * @return
     */
    public boolean wordBreak(String s, List<String> wordDict) {
        boolean[] dp = new boolean[s.length()+1];
        dp[0] = true;
        HashSet<String> set = new HashSet<>(wordDict);
        for (int i = 1; i <=s.length(); i++) {
            for (int j = 0; j <i ; j++) {
                String sub = s.substring(j,i);
                if(dp[j]&&set.contains(sub)){
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length()];

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
     * 最大正方形：在一个由 0 和 1 组成的二维矩阵内，找到只包含 1 的最大正方形，并返回其面积。
     * @param matrix
     * @return
     */
    public int maximalSquare(char[][] matrix) {
        //dp(i,j) 表示以 (i, j) 为右下角，且只包含 1 的正方形的边长最大值
        int m = matrix.length;
        if(m==0) return 0;
        int n = matrix[0].length;
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if(matrix[i][j]=='1'){
                    if(j==0||i==0){
                        dp[i][j] = 1;
                    }else {
                        dp[i][j] = Math.min(dp[i-1][j-1],Math.min(dp[i][j-1],dp[i-1][j]))+1;
                    }
                }
            }
        }

        int res = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                res = Math.max(dp[i][j],res);
            }
        }
        return res*res;
    }



    /**
     * 完全平方数
     * @return
     */
    public int numSquares(int n) {
        //dp 组成n的最小平方数个数
        int[] dp = new int[n+1];
        Arrays.fill(dp,Integer.MAX_VALUE);
        dp[0] = 0;
        int sqrt = (int) Math.sqrt(n);
        for (int i = 1; i <= n; i++) {
            int min = Integer.MAX_VALUE;
            for (int j = 0; j <=sqrt&&j*j<=i; j++) {
                min = Math.min(dp[i-j*j],min);
            }
            dp[i] = min+1;
        }
        return dp[n];
    }



    /**
     * 最长上升子序列：给定一个无序的整数数组，找到其中最长上升子序列的长度。
     * @param nums
     * @return
     */
    public int lengthOfLIS(int[] nums) {
        //dp[i] 以下标为i的元素结尾的最长上升子序列
        int n = nums.length;
        if(n<=1) return n;
        int[] dp = new int[n];
        for (int i = 0; i <n ; i++) {
            int max = 0;
            for (int j = 0; j < i; j++) {
                if(nums[i]>nums[j]){
                    max = Math.max(max,dp[j]);
                }
            }
            dp[i] = max+1;
        }
        int res = 0;
        for (int i = 0; i < n; i++) {
            res = Math.max(res,dp[i]);

        }
        return res;


    }



    /**
     * 最佳买卖股票时机含冷冻期
     * @return
     */
    public int maxProfit(int[] prices) {
        //f0目前持有一支股票，对应的「累计最大收益」
        //f1目前不持有任何股票，并且处于冷冻期中，对应的「累计最大收益」
        //f2目前不持有任何股票，并且不处于冷冻期中，对应的「累计最大收益」
        int n = prices.length;
        if(n<=1) return 0;
        int f0 = -prices[0];
        int f1 = 0;
        int f2 = 0;
        for (int i =1; i <n ; i++) {
            int newf0 = Math.max(f0,f2-prices[i]);
            int newf1 = f0+prices[i];
            int newf2 = Math.max(f1,f2);
            f0 = newf0;
            f1 = newf1;
            f2 = newf2;
        }
        return Math.max(f1,f2);
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



    /**
     * 比特位计数:给定一个非负整数 num。对于 0 ≤ i ≤ num 范围中的每个数字 i ，
     * 计算其二进制数中的 1 的数目并将它们作为数组返回。
     * @param num
     * @return
     */
    public int[] countBits(int num) {
        int[] res = new int[num+1];
        int b = 1;
        while (b<=num){
            for (int i = 0; i <b && i+b<=num; i++) {
                res[i+b] = res[i]+1;
            }
            b = b<<1;
        }

        return res;
    }



    /**
     * 分割等和子集：给定一个只包含正整数的非空数组。是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。
     * @return
     */
    public boolean canPartition(int[] nums) {
        int n = nums.length;
        if(n<2) return false;
        int sum = 0, maxNum = 0;
        for (int num : nums) {
            sum += num;
            maxNum = Math.max(maxNum, num);
        }
        if (sum % 2 != 0) {
            return false;
        }
        int target = sum / 2;
        if (maxNum > target) {
            return false;
        }
        boolean[] dp = new boolean[target + 1];
        dp[0] = true;
        for (int i = 0; i < n; i++) {
            int num = nums[i];
            for (int j = target; j >= num; --j) {
                dp[j] |= dp[j - num];
            }
        }
        return dp[target];
    }



    /**
     * 目标和:给定一个非负整数数组，a1, a2, ..., an, 和一个目标数，
     * S。现在你有两个符号 + 和 -。对于数组中的任意一个整数，你都可以从 + 或 -中选择一个符号添加在前面。
     *
     * 返回可以使最终数组和为目标数 S 的所有添加符号的方法数。
     * @return
     */
    public int findTargetSumWays(int[] nums, int S) {

        //转换为背包问题
        int sum = 0;
        for (int num : nums) {
            sum+=num;
        }
        if(S>sum){
            return 0;
        }
        if((sum + S)%2==1){
            return 0;
        }

        int target = (sum + S)/2;

        int dp[] = new int[target+1];
        dp[0] =1;
        for (int i = 0; i < nums.length; i++) {
            for (int j = target; j >=nums[i]; j--) {
                dp[j] = dp[j]+dp[j-nums[i]];
            }
        }

        return dp[target];


    }

}

package com.chaoger.algori;

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



    //找到字符串中所有字母异位词



    //每日温度
}

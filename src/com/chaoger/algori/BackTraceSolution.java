package com.chaoger.algori;


import java.util.ArrayList;
import java.util.List;

/**
 * 回溯
 */
public class BackTraceSolution {

    List<List<Integer>> output = new ArrayList<>();

    boolean[] visited;

    /**
     * 全排列:给定一个 没有重复 数字的序列，返回其所有可能的全排列。
     */
    public List<List<Integer>> permute(int[] nums) {
        visited = new boolean[nums.length];
        backTracePermute(new ArrayList<>(),nums);
        return output;
    }

    public void backTracePermute(List<Integer> list,int[] nums){
        if(list.size()==nums.length){
            output.add(new ArrayList<>(list));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if(!visited[i]){
                list.add(nums[i]);
                visited[i] = true;
                backTracePermute(list,nums);
                list.remove(list.size()-1);
                visited[i] = false;
            }
        }

    }
}

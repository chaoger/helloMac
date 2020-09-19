package com.chaoger.algori;

import java.util.*;

public class ArraySolution {




    //

    /**
     * 两数之和:给定一个整数数组 nums 和一个目标值 target，
     * 请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
     *
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                return new int[]{map.get(target - nums[i]), i};
            }
            map.put(nums[i], i);
        }
        return null;
    }

    /**
     * 盛最多水的容器:给你 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。
     * 在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0)。找出其中的两条线，
     * 使得它们与 x 轴共同构成的容器可以容纳最多的水。
     *
     * @param height
     * @return
     */
    public int maxArea(int[] height) {
        int head = 0;
        int last = height.length - 1;
        int max = 0;
        while (last > head) {
            if (height[last] > height[head]) {
                max = Math.max(max, height[head] * (last - head));
                head++;
            } else {
                max = Math.max(max, height[last] * (last - head));
                last--;
            }
        }
        return max;

    }

    /**
     * 三数之和:给你一个包含 n 个整数的数组 nums，
     * 判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有满足条件且不重复的三元组。
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                return res;
            }
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int cur = nums[i];
            int L = i + 1;
            int R = nums.length - 1;
            while (L < R) {
                int tmp = cur + nums[L] + nums[R];
                if (tmp == 0) {
                    List<Integer> list = new ArrayList<>();
                    list.add(cur);
                    list.add(nums[L]);
                    list.add(nums[R]);
                    res.add(list);

                    while (L < R && nums[L] == nums[L + 1]) L++;
                    while (L < R && nums[R] == nums[R - 1]) R--;

                    R--;
                    L++;
                } else if (tmp > 0) {
                    R--;
                } else {
                    L++;
                }
            }
        }
        return res;

    }

    /**
     * 下一个排列:实现获取下一个排列的函数，算法需要将给定数字序列重新排列成字典序中下一个更大的排列。
     *
     * @param nums
     */
    public void nextPermutation(int[] nums) {
        //1.第一个降序的index
        int len = nums.length;
        int i = len - 2;
        while (i >= 0 && nums[i] >= nums[i + 1]) {
            i--;
        }
        //2.交换刚好大于降序元素的index
        if (i >= 0) {
            int j = len - 1;
            while (j >= 0 && nums[j] <= nums[i]) {
                j--;
            }
            swap(nums, i, j);
        }

        //3.翻转后面的顺序
        int begin = i + 1;
        int end = len - 1;
        while (begin < end) {
            swap(nums, begin, end);
            begin++;
            end--;
        }


    }

    public void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    /**
     * 搜索旋转排序数组:假设按照升序排序的数组在预先未知的某个点上进行了旋转。
     * ( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。
     * 搜索一个给定的目标值，如果数组中存在这个目标值，则返回它的索引，否则返回 -1 。
     * 你可以假设数组中不存在重复的元素。
     *
     * @param nums
     * @param target
     * @return
     */
    public int search(int[] nums, int target) {
        //1.比较是否命中了前面的升序区间
        //2.比较是否命中了前面的升序区间
        if (nums == null || nums.length <= 0) return -1;
        int n = nums.length;
        int begin = 0;
        int end = n - 1;
        while (begin <= end) {
            int mid = (begin + end) / 2;
            if (target == nums[mid]) return mid;
            if (nums[0] <= nums[mid]) {
                if (target >= nums[0] && target < nums[mid]) {
                    end = mid - 1;
                } else {
                    begin = mid + 1;
                }
            } else {
                if (target > nums[mid] && target <= nums[n - 1]) {
                    begin = mid + 1;
                } else {
                    end = mid - 1;
                }
            }
        }
        return -1;

    }


    /**
     * 在排序数组中查找元素的第一个和最后一个位置:
     * 给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
     *
     * @param nums
     * @param target
     * @return
     */
    public int[] searchRange(int[] nums, int target) {
        int[] res = new int[]{-1, -1};
        if (nums == null || nums.length == 0) return res;
        int begin = 0;
        int end = nums.length - 1;
        int mid = -1;
        while (begin <= end) {
            mid = (begin + end) / 2;
            if (target > nums[mid]) {
                begin = mid + 1;
            } else if (target < nums[mid]) {
                end = mid - 1;
            } else {
                break;
            }
        }
        if (begin > end) return res;
        int first = mid;
        int last = mid;
        while (first >= 0 && nums[first] == target) first--;
        while (last < nums.length && nums[last] == target) last++;
        res[0] = first + 1;
        res[1] = last - 1;
        return res;
    }

    /**
     * 组合总和:给定一个无重复元素的数组 candidates 和一个目标数 target ，
     * 找出 candidates 中所有可以使数字和为 target 的组合。
     * @param candidates
     * @param target
     * @return
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> res = new ArrayList<>();
        backTraceCombinationSum(res,candidates,target,new ArrayList<>());
        return res;

    }

    public void backTraceCombinationSum(List<List<Integer>> res,int[] candidates,int target,List<Integer> list){
        if(target<0){
            return;
        }
        if(target==0){
            res.add(new ArrayList<>(list));
        }
        for (int candidate : candidates) {
            list.add(candidate);
            if(list.size()>1&&candidate<list.get(list.size()-2)){
                list.remove(list.size()-1);
                continue;
            }
            backTraceCombinationSum(res,candidates,target-candidate,list);
            list.remove(list.size()-1);
        }
    }


    /**
     * 旋转图像:给定一个 n × n 的二维矩阵表示一个图像。
     *
     * 将图像顺时针旋转 90 度。
     * @param matrix
     */

    public void rotate(int[][] matrix){

        //1.主对角线互换
        int n = matrix.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = tmp;
            }
        }

        //2.纵向对称互换
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <n/2; j++) {
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[i][n-1-j];
                matrix[i][n-1-j] = tmp;
            }

        }

    }


    /**
     * 最大子序和:给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
     * @param nums
     * @return
     */
    public int maxSubArray(int[] nums) {
        int max = Integer.MIN_VALUE;
        int sum = 0;
        for (int num : nums) {
            sum += num;
            max = Math.max(sum,max);
            if(sum<0){
                sum = 0;
            }
        }
        return max;

    }


    /**
     * 跳跃游戏:给定一个非负整数数组，你最初位于数组的第一个位置。
     * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
     * 判断你是否能够到达最后一个位置。
     * @param nums
     * @return
     */
    public boolean canJump(int[] nums) {
        if(nums.length<=1) return  true;
        int max = nums[0];
        for (int i = 1; i <=max&&i<nums.length; i++) {
            max = Math.max(max,i+nums[i]);
        }
        return max>=nums.length-1;

    }


    /**
     * 合并区间:给出一个区间的集合，请合并所有重叠的区间。
     * @param intervals
     * @return
     */
    public int[][] merge(int[][] intervals) {
        //1.先排序
        if(intervals.length<=1) return intervals;
        List<int[]> res = new ArrayList<>();

        Arrays.sort(intervals,(a,b) -> a[0]-b[0]);

        res.add(intervals[0]);

        //2.再合并
        for (int i = 1; i < intervals.length; i++) {
            int[] last = res.get(res.size() - 1);
            if(intervals[i][0]>last[1]){
                res.add(intervals[i]);
            }else {
                last[1] = Math.max(last[1],intervals[i][1]);
            }
        }
        return res.toArray(new int[0][]);

    }


    /**
     * 不同路径:一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。
     *
     * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）
     *
     * @param m
     * @param n
     * @return
     */
    public int uniquePaths(int m, int n) {
        int[] arr = new int[n];
        Arrays.fill(arr,1);
        for (int i = 1; i <m; i++) {
            for (int j = 1; j <n ; j++) {
                arr[j]+=arr[j-1];
            }
        }
        return arr[n-1];

    }


    /**
     * 最小路径和:
     * 给定一个包含非负整数的 m x n 网格，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
     *
     * 说明：每次只能向下或者向右移动一步。
     * @param grid
     */

    public int minPathSum(int[][] grid) {

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if(i== 0&& j == 0){
                    continue;
                }else if(i==0 && j!=0){
                    grid[0][j] = grid[0][j]+grid[0][j-1];
                }else if(i!=0 && j==0){
                    grid[i][0] = grid[i][0]+grid[i-1][0];
                }else {
                    grid[i][j] = grid[i][j] + Math.min(grid[i-1][j],grid[i][j-1]);
                }

            }
        }

        return grid[grid.length-1][grid[0].length-1];

    }

    /**
     * 颜色分类:给定一个包含红色、白色和蓝色，一共 n 个元素的数组，原地对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。
     *
     * 此题中，我们使用整数 0、 1 和 2 分别表示红色、白色和蓝色。
     *
     * @param nums
     */
    public void sortColors(int[] nums) {
        int p0=0,cur = 0,p2 = nums.length-1;
        while (cur<=p2){
            int tmp = nums[cur];
            if(nums[cur]==0){
                nums[cur++] = nums[p0];
                nums[p0++] = tmp;

            }else if(nums[cur]==2){
                nums[cur] = nums[p2];
                nums[p2--]= tmp;
            }else {
                cur++;
            }
        }

    }


    public static void main(String[] args) {


    }

}
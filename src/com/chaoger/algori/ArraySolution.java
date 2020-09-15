package com.chaoger.algori;

import java.util.*;

public class ArraySolution {


    public static void main(String[] args) {


    }

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
     * @param nums
     * @param target
     * @return
     */
    public int[] searchRange(int[] nums, int target) {
        int[] res = new int[]{-1,-1};
        if(nums==null||nums.length==0) return res;
        int begin = 0;
        int end = nums.length-1;
        int mid = -1;
        while (begin<=end){
            mid = (begin+end)/2;
            if(target>nums[mid]){
                begin = mid+1;
            }else if(target<nums[mid]){
                end = mid-1;
            }else {
                break;
            }
        }
        if(begin>end) return res;
        int first = mid;
        int last = mid;
        while (first>=0&&nums[first]==target) first--;
        while (last<nums.length&&nums[last]==target) last++;
        res[0] = first+1;
        res[1] = last-1;
        return res;
    }
}
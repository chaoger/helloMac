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

    /**
     * 子集:给定一组不含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
     * @param nums
     * @return
     */
    int subsetsK = 0;
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i <=nums.length; i++) {
            backTraceSubsets(result,new ArrayList<>(),nums,0);
            subsetsK++;
        }
        return result;

    }

    public void backTraceSubsets(List<List<Integer>> result,List<Integer> list,int[] nums,int first){
        if(list.size()==subsetsK){
            result.add(new ArrayList<>(list));
            return;
        }
        for (int i = first; i < nums.length ; i++) {
            list.add(nums[i]);
            backTraceSubsets(result,list,nums,i+1);
            list.remove(list.size()-1);
        }

    }

    /**
     * 单词搜索:给定一个二维网格和一个单词，找出该单词是否存在于网格中。
     *
     * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。
     * @param board
     * @param word
     * @return
     */

    int[][] existDirection = {{-1, 0}, {0, -1}, {0, 1}, {1, 0}};
    boolean[][] existMark ;

    public boolean exist(char[][] board, String word) {
        int m = board.length;
        if(m==0) return false;
        int n = board[0].length;
        existMark = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if(backTraceExist(board,word,0,i,j)){
                    return true;
                }
            }
        }

        return false;

    }

    public boolean backTraceExist(char[][] board,String word,int start,int i,int j){

        if(start==word.length()-1){
            return board[i][j] == word.charAt(start);
        }

        if(board[i][j] == word.charAt(start)){
            existMark[i][j] = true;
            for (int k = 0; k < 4; k++) {
                int x = existDirection[k][0]+i;
                int y = existDirection[k][1]+j;
                if(inArea(x, y, board)&&!existMark[x][y]){
                    if(backTraceExist(board, word, start+1,x , y)){
                        return true;
                    }
                }
            }
            existMark[i][j] = false;
        }

        return false;

    }

    private boolean inArea(int x,int y,char[][] board) {
        int m = board.length;
        int n = board[0].length;
        return x>=0&&x<m&&y>=0&&y<n;
    }


    /**
     *买卖股票的最佳时机:给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
     * 如果你最多只允许完成一笔交易（即买入和卖出一支股票一次），设计一个算法来计算你所能获取的最大利润。
     * @param prices
     */
    public int maxProfit(int[] prices) {
        int profit = 0;
        if(prices.length<=1) return profit;
        int min = prices[0];
        for (int i = 1; i < prices.length; i++) {
            profit = Math.max(profit,prices[i]-min);
            min = Math.min(min,prices[i]);
        }
        return profit;
    }


    /**
     * 乘积最大子数组:给你一个整数数组 nums ，请你找出数组中乘积最大的连续子数组
     * （该子数组中至少包含一个数字），并返回该子数组所对应的乘积。
     *
     * @param nums
     * @return
     */
    public int maxProduct(int[] nums) {
        //其实就是正负号的问题
        int maxF = nums[0],minF = nums[0],ans = nums[0];
        for (int i = 1; i < nums.length; i++) {
            int mx = maxF,mn = minF;
            maxF = Math.max(nums[i],Math.max(nums[i]*mx,nums[i]*mn));
            minF = Math.min(nums[i],Math.min(nums[i]*mx,nums[i]*mn));
            ans = Math.max(ans,maxF);
        }
        return ans;

    }

    /**
     *  多数元素:给定一个大小为 n 的数组，找到其中的多数元素。多数元素是指在数组中出现次数大于 ⌊ n/2 ⌋ 的元素。
     * 你可以假设数组是非空的，并且给定的数组总是存在多数元素。
     * @param nums
     * @return
     */
    public int majorityElement(int[] nums) {
        int count = 0 ;
        int res = 0;
        for (int num : nums) {
            if(count==0){
                res = num;
            }
            count+=(res==num)?1:-1;
        }

        return res;
    }


    /**
     * 除自身以外数组的乘积:给你一个长度为 n 的整数数组 nums，其中 n > 1，
     * 返回输出数组 output ，其中 output[i] 等于 nums 中除 nums[i] 之外其余各元素的乘积。
     * @param nums
     * @return
     */
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] ans = new int[n];
        ans[0] =1;
        for (int i = 1; i <n ; i++) {
            ans[i] = nums[i-1]*ans[i-1];
        }
        int R = 1;
        for (int i = n-1; i >=0 ; i--) {
            ans[i]*=R;
            R *= nums[i];
        }
        return ans;
    }




    /**
     * 移动零：给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
     * @param nums
     */
    public void moveZeroes(int[] nums) {
        int index = 0;
        for (int num : nums) {
            if(num!=0){
                nums[index++] = num;
            }
        }
        while (index<nums.length){
            nums[index++] = 0;
        }

    }


    /**
     * 寻找重复数：给定一个包含 n + 1 个整数的数组 nums，其数字都在 1 到 n 之间（包括 1 和 n），
     * 可知至少存在一个重复的整数。假设只有一个重复的整数，找出这个重复的数。
     *
     * @param nums
     * @return
     */
    public int findDuplicate(int[] nums) {
        int slow = 0, fast = 0;
        do {
            slow = nums[slow];
            fast = nums[nums[fast]];
        } while (slow != fast);
        slow = 0;
        while(slow!=fast){
            slow = nums[slow];
            fast = nums[fast];
        }

        return slow;
    }

    /**
     * 找到所有数组中消失的数字:给定一个范围在  1 ≤ a[i] ≤ n ( n = 数组大小 ) 的 整型数组，数组中的元素一些出现了两次，另一些只出现一次。
     *
     * 找到所有在 [1, n] 范围之间没有出现在数组中的数字。
     * @param nums
     * @return
     */
    public List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            while (true){
                if(nums[i]<=0||nums[i]>nums.length){
                    nums[i] = 0;
                    break;
                }
                if(nums[i] == i+1){
                    break;
                }else {
                    if(nums[i]==nums[nums[i]-1]){
                        nums[i]=0;
                        break;
                    }
                    int tmp = nums[nums[i]-1];
                    nums[nums[i]-1]  = nums[i];
                    nums[i] =  tmp;

                }
            }
        }
        for (int i = 0; i < nums.length; i++) {
            if(nums[i]==0){
                res.add(i+1);
            }
        }
        return res;

    }


    /**
     * 和为K的子数组:给定一个整数数组和一个整数 k，你需要找到该数组中和为 k 的连续的子数组的个数。
     * @param nums
     * @param k
     * @return
     */
    public int subarraySum(int[] nums, int k) {
        Map<Integer,Integer> map = new HashMap<>();
        int total = 0,count = 0;
        map.put(0,1);
        for (int num : nums) {
            total+=num;
            if(map.containsKey(total-k)){
                count+=map.get(total-k);
            }
            map.put(total,map.getOrDefault(total,0)+1);
        }
        return count;
    }


    /**
     * 最短无序连续子数组:给定一个整数数组，你需要寻找一个连续的子数组，
     * 如果对这个子数组进行升序排序，那么整个数组都会变为升序排序。
     * 你找到的子数组应是最短的，请输出它的长度。
     * @param nums
     * @return
     */
    public int findUnsortedSubarray(int[] nums) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        for (int i = 0; i < nums.length - 1; i++) {
            if(nums[i]>nums[i+1]){
                min = Math.min(min,nums[i+1]);
            }
        }

        for (int i =nums.length - 1;i>0; i--) {
            if(nums[i]<nums[i-1]){
                max = Math.max(max,nums[i-1]);
            }
        }

        int l, r;
        for (l = 0; l < nums.length; l++) {
            if (min < nums[l])
                break;
        }
        for (r = nums.length - 1; r >= 0; r--) {
            if (max > nums[r])
                break;
        }
        return r - l < 0 ? 0 : r - l + 1;

    }


    /**
     * 任务调度器:给定一个用字符数组表示的 CPU 需要执行的任务列表。其中包含使用大写的 A - Z 字母表示的26 种不同种类的任务。
     * 任务可以以任意顺序执行，并且每个任务都可以在 1 个单位时间内执行完。CPU 在任何一个单位时间内都可以执行一个任务，或者在待命状态。
     * 然而，两个相同种类的任务之间必须有长度为 n 的冷却时间，因此至少有连续 n 个单位时间内 CPU 在执行不同的任务，或者在待命状态。
     * 你需要计算完成所有任务所需要的最短时间。
     * @param tasks
     * @param n
     * @return
     */
    public int leastInterval(char[] tasks, int n) {
        int[] map = new int[26];
        for (char c: tasks) {
            map[c - 'A']++;
        }
        Arrays.sort(map);
        int max_val = map[25] - 1, idle_slots = max_val * n;
        for (int i = 0; i <= 24 ; i++) {
            idle_slots -= Math.min(map[i], max_val);
        }
        return idle_slots > 0 ? idle_slots + tasks.length : tasks.length;

    }



    public static void main(String[] args) {


    }

}
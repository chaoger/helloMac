package com.chaoger.algori;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.*;
import java.util.stream.Collectors;

public class Solution {

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    //给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
    //你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。
    //
    //来源：力扣（LeetCode）
    //链接：https://leetcode-cn.com/problems/two-sum
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap();
        for (int i = 0; i < nums.length; i++) {
            int sub = target - nums[i];
            if (map.containsKey(sub)) {
                return new int[]{i, map.get(sub)};
            }
            map.put(i, nums[i]);
        }
        throw new IllegalArgumentException("No two sum solution");
    }


    /**
     * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
     * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
     * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
     * <p>
     * 链接：https://leetcode-cn.com/problems/add-two-numbers
     */

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        ListNode head = new ListNode(0);
        ListNode p = l1, q = l2, curr = head;
        int carry = 0;

        while (p != null || q != null) {
            int x = (p != null) ? p.val : 0;
            int y = (q != null) ? q.val : 0;
            int sum = x + y + carry;
            carry = sum / 10;
            curr.next = new ListNode(sum % 10);
            curr = curr.next;
            if (p != null) p = p.next;
            if (q != null) q = q.next;
        }
        if (carry > 0) {
            curr.next = new ListNode(carry);
        }
        return head.next;
    }

    /**
     * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
     */
    public int lengthOfLongestSubstring(String s) {
        int res = 0, i = 0, j = 0;
        if (s == null || s.length() <= 0) {
            return res;
        }
        int n = s.length();
        Set<Character> set = new HashSet<>();
        while (i < n && j < n) {
            if (!set.contains(s.charAt(j))) {
                set.add(s.charAt(j++));
                res = Math.max(res, j - i);
            } else {
                set.remove(s.charAt(i++));
            }
        }
        return res;

    }

    /**
     * 给定两个大小为 m 和 n 的有序数组 nums1 和 nums2。
     * <p>
     * 请你找出这两个有序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {

        return 0d;

    }

    /**
     * 反转整数
     *
     * @param x
     * @return
     */
    public int reverse(int x) {
        int rev = 0;
        while (x != 0) {
            int pop = x % 10;
            x /= 10;
            if (rev > Integer.MAX_VALUE / 10 || (rev == Integer.MAX_VALUE / 10 && pop > Integer.MAX_VALUE % 10))
                return 0;
            if (rev < Integer.MIN_VALUE / 10 || (rev == Integer.MIN_VALUE / 10 && pop < Integer.MIN_VALUE % 10))
                return 0;
            rev = rev * 10 + pop;
        }
        return rev;
    }

    /**
     * 编写一个算法来判断一个数 n 是不是快乐数。
     * 「快乐数」定义为：对于一个正整数，每一次将该数替换为它每个位置上的数字的平方和，然后重复这个过程直到这个数变为 1，也可能是 无限循环 但始终变不到 1。如果 可以变为  1，那么这个数就是快乐数。
     * 如果 n 是快乐数就返回 True ；不是，则返回 False 。
     *
     * @param n
     * @return
     */
    public boolean isHappy(int n) {
        Set<Integer> result = new HashSet<>();
        if (n == 1) return true;
        result.add(n);
        int sum, bit;

        while (true) {
            sum = 0;
            while (n > 0) {
                bit = n % 10;
                sum += bit * bit;
                n = n / 10;
            }
            n = sum;
            if (sum == 1) {
                return true;
            }
            if (result.contains(sum)) {
                return false;
            } else {
                result.add(sum);
            }
        }
    }

    public boolean isPalindrome(int x) {
        if (x < 0) return false;
        if (x == 0) return true;

        int n = x;
        long res = 0;
        while (n > 0) {

            res = res * 10 + n % 10;
            n = n / 10;
        }
        return res == x;


    }


    public String longestCommonPrefix(String[] strs) {
        int len = strs.length;
        if (len == 0) return "";
        if (len == 1) return strs[0];
        StringBuilder sb = new StringBuilder();
        int j = 0;
        while (true) {
            if (strs[0].length() - 1 < j) {
                return sb.toString();
            }
            char c = strs[0].charAt(j);
            for (int i = 1; i < len; i++) {
                if (strs[i].length() - 1 >= j && c == strs[i].charAt(j)) {
                    continue;
                } else {
                    return sb.toString();
                }
            }
            sb.append(c);
            j++;
        }

    }

    public int maxArea(int[] height) {
        int head = 0, last = height.length;
        int max = 0;
        while (head < last) {
            if (height[head] < height[last]) {
                max = Math.max(max, height[head] * (last - head));
                head++;
            } else {
                max = Math.max(max, height[last] * (last - head));
                last++;
            }
        }

        return max;
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(0);
        ListNode p = head;

        while (l1 != null || l2 != null) {
            if (l1.val <= l2.val) {
                p.next = l1;
                l1 = l1.next;
            } else {
                p.next = l2;
                l2 = l2.next;

            }
            p = p.next;
        }
        if (l1 != null) {
            p.next = l1;
        }
        if (l2 != null) {
            p.next = l2;
        }
        return head.next;

    }

    /**
     * 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
     */
    public List<String> generateParenthesis(int n) {

        List<String> res = new ArrayList<>();
        StringBuilder cur = new StringBuilder();
        backStrackGenerateParenthesis(res, cur, 0, 0, n);

        return res;


    }

    private void backStrackGenerateParenthesis(List<String> res, StringBuilder cur, int open, int close, int max) {
        if (cur.length() == max * 2) {
            res.add(cur.toString());
            return;
        }
        if (open < max) {
            cur.append("(");
            backStrackGenerateParenthesis(res, cur, open + 1, close, max);
            cur.deleteCharAt(cur.length() - 1);

        }
        if (close < open) {
            cur.append(")");
            backStrackGenerateParenthesis(res, cur, open, close + 1, max);
            cur.deleteCharAt(cur.length() - 1);
        }
    }


    Map<String, String> phone = new HashMap<String, String>() {{
        put("2", "abc");
        put("3", "def");
        put("4", "ghi");
        put("5", "jkl");
        put("6", "mno");
        put("7", "pqrs");
        put("8", "tuv");
        put("9", "wxyz");
    }};

    List<String> output = new ArrayList<String>();

    /**
     * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。
     *
     * @param digits
     * @return
     */
    public List<String> letterCombinations(String digits) {
        if (digits.length() != 0)
            backtrackLetterCombinations(new StringBuilder(), digits);
        return output;
    }

    private void backtrackLetterCombinations(StringBuilder cur, String nextDigits) {
        if (nextDigits.length() == 0) {
            output.add(cur.toString());
            return;
        }
        String digit = nextDigits.substring(0, 1);
        String letter = phone.get(digit);
        for (int i = 0; i < letter.length(); i++) {
            cur.append(letter.charAt(i));
            backtrackLetterCombinations(cur, nextDigits.substring(1));
            cur.deleteCharAt(cur.length() - 1);
        }
    }

    List<List<Integer>> combinationSum = new ArrayList<>();

    public List<List<Integer>> combinationSum(int[] candidates, int target) {

        Arrays.sort(candidates);
        backtrackLetterCombinationSum(candidates, target, new ArrayList<>());
        return combinationSum;

    }

    private void backtrackLetterCombinationSum(int[] candidates, int target, List<Integer> result) {
        if (target < 0) {
            return;
        }
        if (target == 0) {
            combinationSum.add(new ArrayList<>(result));
            return;
        }
        for (int candidate : candidates) {
            result.add(candidate);
            if (result.size() > 1 && candidate < result.get(result.size() - 2)) {
                return;
            }
            backtrackLetterCombinationSum(candidates, target - candidate, result);
            result.remove(result.size() - 1);
        }


    }




    public int removeDuplicates0(int[] nums) {

        int len = nums.length;
        if (len <= 1) return len;
        int index = 0;
        for (int i = 1; i < len; i++) {
            if (nums[index] != nums[i]) {
                nums[++index] = nums[i];
            }
        }

        return index++;

    }

    public int uniquePaths(int m, int n) {
        int[] cur = new int[n];
        Arrays.fill(cur, 1);
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                cur[j] += cur[j - 1];
            }
        }
        return cur[n - 1];


    }

    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;


        int[][] min = new int[m][n];
        for (int[] ints : min) {
            Arrays.fill(ints, Integer.MAX_VALUE);

        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int cur = grid[i][j];
                if (j > 0) {
                    cur += min[i][j - 1] == Integer.MAX_VALUE ? grid[i][j - 1] : min[i][j - 1];
                }
                if (j < n - 1) {
                    cur += min[i][j + 1] == Integer.MAX_VALUE ? grid[i][j + 1] : min[i][j + 1];
                }
                if (i > 0) {
                    cur += min[i - 1][j] == Integer.MAX_VALUE ? grid[i - 1][j] : min[i - 1][j];
                }
                if (i < n - 1) {
                    cur += min[i + 1][j] == Integer.MAX_VALUE ? grid[i + 1][j] : min[i + 1][j];
                }
                min[i][j] = Math.min(cur, min[i][j]);
            }

        }
        return min[m - 1][n - 1];

    }


    public static int longestValidParentheses(String s) {
        int left = 0, right = 0, maxlength = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                left++;
            } else {
                right++;
            }
            System.out.println("right=" + right + ",left=" + left);

            if (left == right) {
                maxlength = Math.max(maxlength, 2 * right);
            } else if (right >= left) {
                left = right = 0;
            }
        }
        left = right = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == '(') {
                left++;
            } else {
                right++;
            }
            if (left == right) {
                maxlength = Math.max(maxlength, 2 * left);
            } else if (left >= right) {
                left = right = 0;
            }
        }
        return maxlength;


    }

    private boolean isVilid(String str) {
        Stack<Character> stack = new Stack<>();
        int len = str.length();
        for (int i = 0; i < len; i++) {
            if (str.charAt(i) == '(') {
                stack.push(str.charAt(i));
            } else {
                if (stack.isEmpty()) {
                    return false;
                } else {
                    stack.pop();
                }
            }
        }
        return stack.isEmpty();
    }


    public boolean canJump(int[] nums) {
        int max = nums[0];
        for (int i = 1; i <= max && i < nums.length; i++) {

            max = Math.max(max, i + nums[i]);
        }
        return max >= nums.length - 1;

    }


    public int jump(int[] nums) {
        if (nums.length <= 1) return 0;
        int times = 0;
        int i = 0;

        while (i < nums.length - 1 && i + nums[i] < nums.length - 1) {
            int left = i;
            int right = left + nums[left];
            int max = left + nums[left];
            int maxIndex = left;
            for (int j = left; j <= right && j < nums.length; j++) {
                if (j + nums[j] > max) {
                    max = j + nums[j];
                    maxIndex = j;
                }
            }
            times++;
            i = maxIndex;


        }
        return times + 1;
    }


    public int[] plusOne(int[] digits) {
        int len = digits.length;
        if (len <= 0) return new int[0];
        boolean carryFlag = true;
        for (int i = 0; i < len; i++) {
            if (digits[i] != 9) {
                carryFlag = false;
                break;
            }
        }
        int size = len + (carryFlag ? 1 : 0);
        int[] res = new int[size];
        int index = size - 1;
        int carry = 0;
        for (int i = len - 1; i > 0; i--) {
            res[index--] = (digits[i] + 1 + carry) % 10;
            carry = (digits[i] + 1 + carry) / 10;
        }

        if (carry == 1) {
            res[0] = 1;
        }

        return res;

    }


    public void setZeroes(int[][] matrix) {
        int row = matrix.length;
        int col = matrix[0].length;

        boolean firstCol = false;
        for (int i = 0; i < row; i++) {
            if (matrix[i][0] == 0) {
                firstCol = true;
                break;
            }
        }

        for (int i = 0; i < col; i++) {
            if (matrix[0][i] == 0) {
                matrix[0][0] = 0;
                break;
            }
        }

        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                if (matrix[i][j] == 0) {
                    matrix[0][j] = 0;
                    matrix[i][0] = 0;
                }
            }
        }


        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                if (matrix[0][j] == 0 || matrix[i][0] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }
        if (matrix[0][0] == 0) {
            for (int i = 0; i < col; i++) {
                matrix[0][i] = 0;
            }
        }
        if (firstCol) {
            for (int i = 0; i < row; i++) {
                matrix[i][0] = 0;
            }
        }
    }


    public boolean searchMatrix(int[][] matrix, int target) {
        int row = matrix.length;
        if (row <= 0) return false;
        int col = matrix[0].length;
        int i = 0;
        int j = col - 1;
        while (i < row && j >= 0) {
            if (matrix[i][j] > target) {
                j--;
                continue;
            }
            if (matrix[i][j] < target) {
                i++;
                continue;

            }
            if (matrix[i][j] == target) {
                return true;
            }
        }
        return false;
    }


    public void sortColors(int[] nums) {
        int p0 = 0, curr = 0;
        // 对于所有 idx > k : nums[idx > k] = 2
        int p2 = nums.length - 1;

        int tmp;
        while (curr <= p2) {
            if (nums[curr] == 0) {
                // 交换第 p0个和第curr个元素
                // i++，j++
                tmp = nums[p0];
                nums[p0++] = nums[curr];
                nums[curr++] = tmp;
            } else if (nums[curr] == 2) {
                // 交换第k个和第curr个元素
                // p2--
                tmp = nums[curr];
                nums[curr] = nums[p2];
                nums[p2--] = tmp;
            } else curr++;
        }


    }


    public int maxSubArray(int[] nums) {
        int max = Integer.MIN_VALUE;
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += sum + nums[i];
            max = Math.max(max, sum);
            if (sum < 0) {
                sum = 0;
            }
        }
        return max;

    }

    public String countAndSay(int n) {
        String ans = "";
        while (n-- > 0) {
            if (ans.length() <= 0) {
                ans = "1";
                continue;
            }
            String temp = "";
            int j = 0;
            while (j < ans.length()) {
                int k = j;
                while (j < ans.length() && ans.charAt(j) == ans.charAt(k)) j++;//计算个数
                temp += Integer.toString(j - k) + ans.charAt(k);
            }
            ans = temp;
        }
        return ans;


    }


    public int firstMissingPositive(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            while (true) {
                if (nums[i] <= 0 || nums[i] > nums.length) {
                    nums[i] = 0;
                    break;
                } else if (nums[i] == i + 1) {
                    break;
                } else {
                    if (nums[i] == nums[nums[i] - 1]) {
                        nums[i] = 0;
                        break;
                    }
                    int tmp = nums[i];
                    nums[i] = nums[tmp - 1];
                    nums[tmp - 1] = tmp;
                }
            }

        }
        int index = 0;
        while (index < nums.length && nums[index] == index + 1)
            index++;
        return index + 1;


    }


    public int trap(int[] height) {
        int res = 0;
        if (height.length <= 1) return 0;
        Stack<Integer> stack = new Stack<>();
        int max = height[0];
        for (int i = 1; i < height.length; i++) {
            if (height[i] > max) {
                while (!stack.isEmpty()) {
                    res += max - stack.pop();
                }
                max = height[i];
            } else {
                stack.push(height[i]);
            }
        }
        while (!stack.isEmpty()) {
            max = stack.peek();
            if (max < stack.peek()) {
                max = stack.pop();
                continue;
            }
            res += max - stack.pop();

        }
        return res;

    }


    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> output = new ArrayList<>();
        for (int num : nums) {
            output.add(num);
        }
        backTracePermute(res, output, nums.length, 0);
        return res;


    }


    private void backTracePermute(List<List<Integer>> res, List<Integer> output, int len, int first) {
        if (first == len) {
            res.add(new ArrayList<>(output));
        }
        for (int i = first; i < len; i++) {
            Collections.swap(output, i, first);
            backTracePermute(res, output, len, first + 1);
            Collections.swap(output, i, first);

        }

    }


    public void rotate(int[][] matrix) {
        //副对角线，交互
        int n = matrix.length;
        int tmp;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i + j >= n - 1) {
                    break;
                }
                tmp = matrix[i][j];
                matrix[i][j] = matrix[n - 1 - j][n - 1 - i];
                matrix[n - 1 - j][n - 1 - i] = tmp;

            }
        }
        //上下交换
        for (int i = 0; i < n / 2; i++) {
            for (int j = 0; j < n; j++) {
                tmp = matrix[i][j];
                matrix[i][j] = matrix[n - 1 - i][j];
                matrix[n - 1 - i][j] = tmp;

            }
        }


    }

    public int[][] merge(int[][] intervals) {
        if (intervals.length <= 0) return new int[0][];
        List<int[]> res = new ArrayList<>();
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        int i = 0;
        while (i < intervals.length) {
            int left = intervals[i][0];
            int right = intervals[i][1];
            while (i < intervals.length - 1 && right >= intervals[i + 1][0]) {
                right = Math.max(right, intervals[i + 1][1]);
                i++;
            }
            res.add(new int[]{left, right});
            i++;
        }
        return res.toArray(new int[0][]);
    }


    public String simplifyPath(String path) {
        List<String> dirList = new ArrayList<>();
        String[] split = path.split("/");

        int jump = 0;
        for (int i = split.length - 1; i >= 0; i--) {
            if (split[i].equals("") || split[i].equals(".")) {
                continue;
            }
            if (split[i].equals("..")) {
                jump++;
                continue;
            }
            if (jump > 0) {
                jump--;
                continue;
            }
            dirList.add(0, split[i]);

        }

        return "/" + String.join("/", dirList);

    }




    public int maximalRectangle(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int[] height = new int[n];
        int[] left = new int[n];
        int[] right = new int[n];
        Arrays.fill(right, n);
        int result = 0;

        for (int i = 0; i < m; i++) {
            int cur_left = 0;
            int cur_right = n;
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 1) {
                    height[j]++;
                } else {
                    height[j] = 0;
                }
            }
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 1) {
                    left[j] = Math.max(left[j], cur_left);
                } else {
                    left[j] = 0;
                    cur_left = j + 1;
                }
            }

            for (int j = n - 1; j >= 0; j--) {
                if (matrix[i][j] == 1) {
                    right[j] = Math.min(right[j], cur_right);
                } else {
                    right[j] = n;
                    cur_right = j;
                }
            }
            for (int j = 0; j < n; j++) {
                System.out.print(left[j] + "," + right[j] + "," + height[j]);
                System.out.println();
            }
            System.out.println("--------------");
            for (int j = 0; j < n; j++) {
                result = Math.max(result, height[j] * (right[j] - left[j]));
            }
        }
        return result;


    }


    public String addBinary(String a, String b) {
        StringBuilder sb = new StringBuilder();
        int lenA = a.length();
        int lenB = b.length();
        if (lenA < lenB) {
            int tmp = lenA;
            lenA = lenB;
            lenB = tmp;
            String tmpStr = a;
            a = b;
            b = tmpStr;
        }
        int carry = 0;
        for (int i = lenA - 1; i >= 0; i--) {
            int j = i - (lenA - lenB);
            int bit1 = a.charAt(i) - '0';
            int bit2 = j >= 0 ? b.charAt(j) - '0' : 0;
            int sum = bit1 + bit2 + carry;
            carry = sum / 2;
            sb.insert(0, sum % 2);
        }
        if (carry > 0) sb.insert(0, carry);

        return sb.toString();
    }


    public int mySqrt(int x) {
        double a = x;
        double x0 = x;
        double x1 = (x0 + x / x0) / 2;
        while (Math.abs(x0 - x1) >= 1) {
            x0 = x;
            x1 = (x0 + x / x0) / 2;
        }


        return (int) x1;
    }








    public ListNode deleteDuplicates2(ListNode head) {
        ListNode no1=new ListNode(0);
        no1.next=head;
        ListNode p1=no1,p2=head;
        int r=0;
        while(p1.next!=null){
            while(p2.next!=null&&p2.next.val==p1.next.val){
                p2=p2.next;
                r=1;//如果有重复节点则r置为1
            }
            if(r==1){
                p1.next=p2.next;//删掉重复节点
                p2=p2.next;//再指针后移！！这时候p1不用动！
                r=0;
            }else{
                p1=p2;//指针后移!p1,p2都向后移动一下
                if(p2.next!=null)p2=p2.next;
            }
        }
        return no1.next;


    }

    public int lengthOfLastWord(String s) {
        String str = s.trim();

        int len = str.length();
        if(len==0) return 0;
        int index = len-1;
        while (index>=0){
            if(str.charAt(index)==' '){
                break;
            }
            index--;
        }

        return len-1-index;

    }

    public ListNode rotateRight(ListNode head, int k) {
        int len = 0;
        ListNode p = head;
        while(p!=null){
            p = p.next;
            len++;
        }
        int index = len-k%len;
        if(index==0||index==len){
            return head;
        }
        ListNode newLast = new ListNode(0);
        newLast.next = head;

        while (index>0){
            newLast = newLast.next;
            index--;

        }
        ListNode newHead = newLast.next;


        p = newHead;
        while (p.next!=null){
            p = p.next;
        }
        p.next = head;
        newLast.next = null;
        return  newHead;

    }


    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new ArrayList<>();
        int R = matrix.length;
        if(R==0) return res;
        int C = matrix[0].length;

        boolean[][] seen = new boolean[R][C];
        int[] dc = {1,0,-1,0};
        int[] dr = {0,1,0,-1};
        int di = 0,r = 0,c = 0;
        for (int i = 0; i < R * C; i++) {
            res.add(matrix[r][c]);
            seen[r][c] = true;
            int rr = r + dr[di];
            int cc = c + dc[di];
            if(0<=rr&&rr<R&&0<=cc&&cc<C&&!seen[rr][cc]){
                r = rr;
                c = cc;
            }else {
                di = (di+1)%4;
                r = r+dr[di];
                c = c+dc[di];
            }

        }
        return res;



    }

    public int[][] generateMatrix(int n) {
        int[][] matrix = new int[n][n];
        int[] dc = {1,0,-1,0};
        int[] dr = {0,1,0,-1};
        int di = 0,r = 0,c = 0;
        for (int i = 1; i <= n*n; i++) {
            System.out.println(r+","+c);
            matrix[r][c] = i;
            System.out.println(matrix[r][c]);
            int rr = r + dr[di];
            int cc = c + dc[di];
            if(0<=rr&&rr<n&&0<=cc&&cc<n&&matrix[rr][cc]==0){
                r = rr;
                c = cc;
            }else {
                di = (di+1)%4;
                r = r+dr[di];
                c = c+dc[di];
            }
        }
        return matrix;

    }


    public int uniquePathsWithObstacles(int[][] obstacleGrid) {


        int r = obstacleGrid.length;
        if(r==0) return 0;

        int c = obstacleGrid[0].length;
        int[] dp = new int[c];
        if(obstacleGrid[0][0]==0) dp[0] = 1;
        else return 0;
        for (int i = 1; i < c; i++) {
            if(obstacleGrid[0][i]==1){
                dp[i]=0;
            }else {
                dp[i] = dp[i-1]==0?0:1;
            }

        }
        for (int i = 1; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if(obstacleGrid[i][j]==1){
                    dp[j]=0;
                }else {
                    if(j>=1){
                        dp[j] = (dp[j]>0?dp[j]:0) + (dp[j-1]>0?dp[j-1]:0);
                    }

                }
            }
            printArray(dp);

        }
        return dp[c-1];

    }



    public int[][] insert(int[][] intervals, int[] newInterval) {
        LinkedList<int[]> output = new LinkedList<>();
        int row = intervals.length;
        int newStart = newInterval[0];
        int newEnd = newInterval[1];
        int index =0;
        while (index < row && newStart > intervals[index][0]){
            output.add(intervals[index++]);
        }

        if(output.isEmpty()|| output.getLast()[1]<newStart){
            output.add(newInterval);
        }else {
            int[] last = output.removeLast();
            last[1] = Math.max(last[1],newEnd);
            output.add(last);

        }

        while (index<row){
            int[] interval = intervals[index++];
            int start = interval[0];
            int end = interval[1];
            if(output.getLast()[1]<start){
                output.add(interval);
            }else {
                int[] last = output.removeLast();
                last[1] = Math.max(last[1],end);
                output.add(last);

            }
        }



        return output.toArray(new int[output.size()][2]);


    }




        public List<String> fullJustify(String[] words, int maxWidth) {
            List<String> res = new ArrayList<>();
            int i = 0;
            int n = words.length;
            while (i < n) {
                // 找到一行可以容下单词
                int left = i;
                // 至少一行能放下一个单词
                int cur_row_len = words[i].length();
                i++;

                while (i < n) {
                    if (cur_row_len + words[i].length() + 1 > maxWidth) break;

                    cur_row_len += words[i].length() + 1;
                    i++;
                }


                StringBuilder tmp = new StringBuilder();
                // 考虑最后一行
                if (i == n) {
                    for (int j = left; j < i; j++) {
                        tmp.append(words[j] + " ");
                    }
                    tmp.deleteCharAt(tmp.length() - 1);
                    for (int j = tmp.length(); j < maxWidth; j++)
                        tmp.append(" ");
                    res.add(tmp.toString());
                    break;
                }
                // 所有单词长度
                int all_word_len = 0;
                for (int j = left; j < i; j++) {
                    all_word_len += words[j].length();
                }
                //System.out.println(all_word_len);
                // 至少空格个数
                int space_num = i - left - 1;
                // 可以为空格的位置个数
                int remain_space = maxWidth - all_word_len;
                int a = 0;
                int b = 0;
                if (space_num != 0) {
                    a = remain_space / space_num;
                    b = remain_space % space_num;
                }
                for (int j = left; j < i; j++) {
                    if (tmp.length() > 0) {
                        for (int k = 0; k < a; k++) tmp.append(" ");
                        if (b != 0) {
                            tmp.append(" ");
                            b--;
                        }
                    }
                    tmp.append(words[j]);
                }
                for (int j = tmp.length(); j < maxWidth; j++) {
                    tmp.append(" ");
                }
                res.add(tmp.toString());
            }
            return res;
        }












    private String printArray(int[] arr){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            sb.append(arr[i]+",");
        }

        return sb.toString();
    }


    public String getPermutation(int n, int k) {
        StringBuilder sb = new StringBuilder();
        int[] factors = new int[n];
        List<Integer> nums = new ArrayList<>();
        factors[0] = 1;
        nums.add(1);
        for (int i = 1; i < n; i++) {
            factors[i] = i*factors[i-1];
            nums.add(i+1);
        }
        k--;
        int idx = 0;
        for (int i = n-1; i >=0 ; i--) {
            idx = k/factors[i];
            k = k-idx*factors[i];
            sb.append(nums.get(idx));
            nums.remove(idx);
        }

        return sb.toString();
    }

    public int minDistance(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();
        int[][] dp = new int[m+1][n+1];
        for (int i = 1; i <=m; i++) {
            dp[i][0] = i;
        }
        for (int i = 1; i <=n; i++) {
            dp[0][i] = i;
        }
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                int left = dp[i-1][j]+1;
                int down = dp[i][j-1]+1;
                int left_down = dp[i-1][j-1];

                if(word1.charAt(i-1)!=word2.charAt(j-1)){
                    left_down ++;
                }
                dp[i][j] =  Math.min(left_down,Math.min(left,down));
            }
        }

        return dp[m][n];
    }



    public ListNode swapPairs(ListNode head) {
        ListNode res = new ListNode(0);
        if(head ==null||head.next==null) return head;
        res.next = head.next;

        ListNode first = head;
        while (first!=null&&first.next!=null){
            ListNode second = first.next;
            first.next = second.next;
            second.next = first;

            if(first.next!=null&&first.next.next!=null){
                ListNode next = first.next;
                first.next = next.next;
                first = next;
            }else{
                first = first.next;
            }


        }

        return res.next;


    }





    public ListNode removeNthFromEnd(ListNode head, int n) {
        int len = 0;
        if(head==null||head.next==null) return null;
        ListNode p = head;
        while (p!=null){
            len++;
            p=p.next;
        }
        ListNode res = new ListNode(0);
        p = res;
        res.next = head;
        int i = len-n;
        while (i>0){
            p = p.next;
            i--;
        }
        p.next = p.next.next;
        return res.next;


    }


    public int romanToInt(String s) {
        if(s.length()==0) return 0;
        int sum = 0;
        int preNum = getValue(s.charAt(0));
        for (int i = 1; i < s.length(); i++) {
            int num = getValue(s.charAt(i));
            if(preNum<num){
                sum -= preNum;
            }else {
                sum += preNum;
            }
            preNum = num;
        }

        return sum+preNum;

    }



    private int getValue(char ch) {
        switch(ch) {
            case 'I': return 1;
            case 'V': return 5;
            case 'X': return 10;
            case 'L': return 50;
            case 'C': return 100;
            case 'D': return 500;
            case 'M': return 1000;
            default: return 0;
        }
    }



    public String intToRoman(int num) {
        int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] symbols = {"M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"};
        StringBuilder sb = new StringBuilder();
        int index = 0;
        while (num>0){
            if(num>=values[index]){
                sb.append(symbols[index]);
                num -= values[index];
            }else {
                index++;
            }
        }
        return sb.toString();

    }



    public List<List<Integer>> threeSum(int[] nums) {

        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            if(nums[i]>0){
                return result;
            }
            if(i>0&&nums[i]==nums[i-1]){
                continue;
            }
            int cur = nums[i];
            int L = i+1;
            int R = nums.length-1;
            while (L<R){
                int tmp = cur + nums[L]+ nums[R];
                if(tmp==0){
                    List<Integer> list = new ArrayList<>();
                    list.add(cur);
                    list.add(nums[L]);
                    list.add(nums[R]);
                    result.add(list);
                    while (L<R&&nums[L]==nums[L+1]) L++;
                    while (L<R&&nums[R]==nums[R-1]) R--;
                    R--;
                    L++;
                }else if(tmp>0) {
                    R--;
                }else {
                    L++;
                }
            }
        }
        return result;

    }





    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int ans = nums[0] + nums[1] + nums[2];
        for(int i=0;i<nums.length;i++) {
            int start = i+1, end = nums.length - 1;
            while(start < end) {
                int sum = nums[start] + nums[end] + nums[i];
                if(Math.abs(target - sum) < Math.abs(target - ans))
                    ans = sum;
                if(sum > target)
                    end--;
                else if(sum < target)
                    start++;
                else
                    return ans;
            }
        }
        return ans;
    }





    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();
        if(nums==null||nums.length<4) return result;
        Arrays.sort(nums);
        int len = nums.length;
        for (int i = 0; i < len - 3; i++) {
            if(i>0&&nums[i]==nums[i-1]){
                continue;
            }
            int min = nums[i]+nums[i+1]+nums[i+2]+nums[i+3];
            if(min>target){
                break;
            }
            int max = nums[i]+nums[len-3]+nums[len-2]+nums[len-1];
            if(max<target){
                continue;
            }
            for (int j = i+1; j <len-2 ; j++) {
                if(j>i+1&&nums[j]==nums[j-1]){
                    continue;
                }
                int L = j+1;
                int R = len-1;
                while (L<R){
                    int tmp = nums[i]+nums[j]+nums[L]+nums[R];
                    if(tmp==target){
                        List<Integer> list = new ArrayList<>();
                        list.add(nums[i]);
                        list.add(nums[j]);
                        list.add(nums[L]);
                        list.add(nums[R]);
                        result.add(list);
                        while (L<R&&nums[L]==nums[L+1]) L++;
                        while (L<R&&nums[R]==nums[R-1]) R--;
                        L++;
                        R--;
                    }else if(tmp>target){
                        R--;
                    }else {
                        L++;
                    }
                }
            }
        }
        return result;

    }



    public int searchInsert(int[] nums, int target) {
        int len = nums.length;
        int left = 0;
        int right = len-1;
        int mid = 0;

        while (left<=right){
            mid = (left+right)/2;
            if(nums[mid]>target){
                right = mid-1;
            }else if(nums[mid]<target){
                left = mid+1;
            }else {
                return mid;
            }
        }
        return left;


    }



    public int[] searchRange(int[] nums, int target) {
        int len = nums.length;
        int left = 0;
        int right = len-1;
        int mid = 0;

        int[] res = new int[]{-1,-1};
        while (left<=right){
            mid = (left+right)/2;
            if(nums[mid]>target){
                right = mid-1;
            }else if(nums[mid]<target){
                left = mid+1;
            }else {
                break;
            }
        }
        if(left>right){
            return res;
        }
        int first = mid,last = mid;
        while (first>=0&&nums[first]==target)first--;
        while (last<len&&nums[last]==target)last++;
        res[0] = first;
        res[1] = last;
        return res;

    }

    public int search(int[] nums, int target) {
        int len = nums.length;
        if(len==0) return -1;
        int left_min = nums[0];
        int right_max = nums[len-1];
        int left = 0 ;
        int right = len-1;
        int mid ;

        if(target>=left_min){
            while (left<=right){
                mid = (left+right)/2;
                if(nums[mid]==target){
                    return mid;
                }else if(nums[mid]>target){
                    right = mid-1;
                }else {
                    if(nums[mid]<left_min){
                        right = mid-1;
                    }else {
                        left = mid+1;
                    }
                }
            }
            return -1;

        }else if(target<=right_max){
            while (left<=right){
                mid = (left+right)/2;
                if(nums[mid]==target){
                    return mid;
                }else if(nums[mid]<target){
                    left = mid+1;
                }else {
                    if(nums[mid]>right_max){
                        left = mid+1;
                    }else {
                        right = mid-1;
                    }
                }
            }
            return -1;

        }else {
            return -1;
        }


    }


    //分治mergeTwoLists
    public ListNode mergeKLists(ListNode[] lists) {
        Queue<ListNode> minHeap = new PriorityQueue<>((v1, v2) -> v1.val - v2.val);
        for (ListNode listNode : lists) {
            if(listNode!=null){
                minHeap.offer(listNode);
            }
        }
        ListNode dummyHead = new ListNode(0);
        ListNode p = dummyHead;
        while (!minHeap.isEmpty()){
            ListNode minNode = minHeap.poll();
            p.next = minNode;
            p = minNode;
            if(minNode.next!=null){
                minHeap.offer(minNode.next);
            }


        }

        return dummyHead.next;

    }




    public int strStr(String haystack, String needle) {
        int len1 = haystack.length();
        int len2 = needle.length();
        if(len2==0) return 0;
        if(len2>len1){
            return -1;
        }
        for (int i = 0; i <=len1-len2; i++) {
            if(needle.equals(haystack.substring(i,i+len2))){
                return i;
            }
        }
        return -1;


    }


    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> res = new ArrayList<>();
        int wordNum = words.length;
        if (wordNum == 0) {
            return res;
        }
        int wordLen = words[0].length();

        HashMap<String, Integer> allWords = new HashMap<String, Integer>();
        for (String word : words) {
            Integer value = allWords.getOrDefault(word, 0);
            allWords.put(word,value+1);
        }
        for (int i = 0; i < s.length()-wordLen*wordNum; i++) {
            int num = 0;
            HashMap<String, Integer> hasWords = new HashMap<>();
            while (num<wordNum){
                String word = s.substring(i+num*wordNum,i+(num+1)*wordNum);
                if(!allWords.containsKey(word)){
                    break;
                }
                Integer value = hasWords.getOrDefault(word, 0);
                hasWords.put(word,value+1);
                if(hasWords.get(word)>allWords.get(word)){
                    break;
                }else {
                    num++;
                }

            }
            hasWords.clear();
            if(num==wordNum){
                res.add(i);
            }

        }

        return res;


    }



    public int divide(int dividend, int divisor) {
        if(divisor == 1) return dividend;
        if(divisor == -1 && dividend == Integer.MIN_VALUE)
            return Integer.MAX_VALUE;

        long a = dividend;
        long b = divisor;

        long ans;
        if(a >0 && b > 0)
            ans = div(a,b);
        else if(a <0 && b < 0)
            ans = div(-a, -b);
        else
            ans = -div(Math.abs(a), Math.abs(b));

        return (int) ans;

    }



    private long div(long a ,long b){
        if(a<b){
            return 0;
        }
        long i = 0;
        while (a>=(b<<i)){
            i++;
        }
        i--;
        return (1<<i)+div(a-(b<<i),b);
    }


    public void nextPermutation(int[] nums) {
        int len = nums.length;
        if(len<=1) return;

        //1.找到第一个降序的元素
        int i = len-2;
        while (i>=0&&nums[i]>=nums[i+1]){
            i--;
        }
        if(i>=0){
            int j = nums.length - 1;
            //2.交互刚好大于降序元素的index
            while (j >= 0 && nums[j] <= nums[i]) {
                j--;
            }
            swap(nums, i, j);
        }
        //3.翻转后面的顺序
        reverse(nums,i+1);



    }
    private void reverse(int[] nums, int start) {
        int i = start, j = nums.length - 1;
        while (i < j) {
            swap(nums, i, j);
            i++;
            j--;
        }

    }
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }



    public ListNode reverseKGroup(ListNode head, int k) {
        if(head==null||head.next==null||k==1){
            return head;
        }


        ListNode tail = head;

        for (int i = 0; i < k; i++) {
            if(tail==null){
                return head;
            }
            tail = tail.next;
        }
        ListNode newHead = reverseKGroup(head,tail);
        head.next = reverseKGroup(tail,k);
        return  newHead;


    }


    public ListNode reverseKGroup(ListNode head, ListNode tail) {
        ListNode next = null;
        ListNode pre = null;
        while (head!=tail){
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }

        return pre;



    }

    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> output = new ArrayList<>();
        output.add(new ArrayList<>());
        for (int num : nums) {
            List<List<Integer>> addList = new ArrayList<>();
            for (List<Integer> curList : output) {
                addList.add(new ArrayList<Integer>(curList){{add(num);}});
            }
            for (List<Integer> addRecord : addList) {
                output.add(addRecord);
            }
        }

        return output;

    }

    private int k = 0;
    private List<List<Integer>> result = new ArrayList<>();
    public List<List<Integer>> subsetsMethod2(int[] nums) {

        for (int i = 0; i <=k; i++) {
            backTrackSubSets(0,nums,new ArrayList<>());
        }
        return result;

    }


    private void backTrackSubSets(int first,int[] nums,List<Integer> list){
        if(list.size()==k){
            result.add(new ArrayList<>(list));
            return;
        }
        for (int i = first; i <nums.length ; i++) {
            list.add(nums[i]);
            backTrackSubSets(i+1,nums,list);
            list.remove(list.size()-1);
        }
    }



    public int removeDuplicates(int[] nums) {
        int times = 1;
        int len = nums.length;
        if(len<3) return len;
        int index = 1;
        for (int i = 1; i < len; i++) {
            if(nums[i]==nums[i-1]){
                times++;
            }else {
                times=1;
                nums[index++] = nums[i];
            }
            if(times>2){
                nums[index] = nums[i];
            }
        }
        return index;


    }

    public boolean search1(int[] nums, int target) {
        int begin = 0;
        int end = nums.length-1;



        while (begin<=end){
            int mid = (begin+end)/2;
            if(nums[mid] == target) return true;
            if (nums[begin] == nums[mid]) {
                begin++;
                continue;
            }
            if(nums[begin]<nums[mid]){
                if(nums[mid]>target&&nums[begin]<=target){
                    end = mid-1;
                }else{
                    begin = mid+1;
                }
            }else{
                if(nums[mid]<target&&nums[end]>=target){
                    begin = mid+1;
                }else{
                    end = mid-1;
                }
            }
        }
        return false;

    }




    public String minWindow(String s, String t) {

        if (s.length() == 0 || t.length() == 0) {
            return "";
        }
        Map<Character, Integer> dictT = new HashMap<>();
        for (int i = 0; i < t.length(); i++) {
            int count = dictT.getOrDefault(t.charAt(i), 0);
            dictT.put(t.charAt(i), count + 1);
        }
        int required = dictT.size();
        int l = 0, r = 0;
        int formed = 0;
        Map<Character, Integer> windowCounts = new HashMap<>();

        // ans list of the form (window length, left, right)
        int[] ans = {-1, 0, 0};

        while (r < s.length()) {

            char c = s.charAt(r);
            int count = windowCounts.getOrDefault(c, 0);
            windowCounts.put(c, count + 1);


            if (dictT.containsKey(c) && windowCounts.get(c).intValue() == dictT.get(c).intValue()) {
                formed++;
            }

            while (l <= r && formed == required) {
                c = s.charAt(l);
                if (ans[0] == -1 || r - l + 1 < ans[0]) {
                    ans[0] = r - l + 1;
                    ans[1] = l;
                    ans[2] = r;
                }
                windowCounts.put(c, windowCounts.get(c) - 1);
                if (dictT.containsKey(c) && windowCounts.get(c).intValue() < dictT.get(c).intValue()) {
                    formed--;
                }
                l++;
            }
            r++;
        }

        return ans[0] == -1 ? "" : s.substring(ans[1], ans[2] + 1);
    }





    public ListNode partition(ListNode head, int x) {
        ListNode cur = head;
        while (cur!=null&&cur.val>=x) cur = cur.next;
        ListNode head1 = cur,p1 = cur;
        cur = head;
        while (cur!=null&&cur.val<x) cur = cur.next;
        ListNode head2 = cur,p2 = cur;

        while (cur!=null){
            if(cur.val>=x){
                p2.next = cur;
                p2 = p2.next;

            }else {
                p1.next = cur;
                p1 = p1.next;
            }
            cur = cur.next;
        }
        p1.next = head2;

        return head1;

    }


    public class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
  }

    public boolean isValidBST(TreeNode root) {

        return compareTreeNode(root,null,null);

    }

    public boolean compareTreeNode(TreeNode root,Integer lower,Integer upper){
        if(root==null) return true;
        int val =root.val;
        if(lower!=null&&val<=lower) return false;
        if(upper!=null&&val>=upper) return false;
        if(!compareTreeNode(root.left,lower,val)) return false;
        if(!compareTreeNode(root.right,val,upper)) return false;
        return true;

    }


    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        help(res,root);
        return res;

    }

    private void help(List<Integer> res ,TreeNode treeNode){
        if(treeNode==null) return;
        help(res,treeNode.left);
        res.add(treeNode.val);
        help(res,treeNode.right);

    }





    public ListNode reverseBetween(ListNode head, int m, int n) {
        if (head == null)  return null;

        ListNode cur = head, prev = null;
        while (m > 1) {
            prev = cur;
            cur = cur.next;
            m--;
            n--;
        }

        ListNode con = prev, tail = cur;
        ListNode third = null;
        while (n > 0) {
            third = cur.next;
            cur.next = prev;
            prev = cur;
            cur = third;
            n--;
        }

        if (con != null) {
            con.next = prev;
        } else {
            head = prev;
        }

        tail.next = cur;
        return head;
    }





    public List<String> restoreIpAddresses(String s) {
        int len = s.length();
        List<String> res = new ArrayList<>();
        if (len > 12 || len < 4) {
            return res;
        }

        Deque<String> path = new ArrayDeque<>(4);
        dfs(s, len, 0, 4, path, res);
        return res;
    }

    // 需要一个变量记录剩余多少段还没被分割

    private void dfs(String s, int len, int begin, int residue, Deque<String> path, List<String> res) {
        if (begin == len) {
            if (residue == 0) {
                res.add(String.join(".", path));
            }
            return;
        }

        for (int i = begin; i < begin + 3; i++) {
            if (i >= len) {
                break;
            }

            if (residue * 3 < len - i) {
                continue;
            }

            if (judgeIpSegment(s, begin, i)) {
                String currentIpSegment = s.substring(begin, i + 1);
                path.addLast(currentIpSegment);

                dfs(s, len, i + 1, residue - 1, path, res);
                path.removeLast();
            }
        }
    }

    private boolean judgeIpSegment(String s, int left, int right) {
        int len = right - left + 1;
        if (len > 1 && s.charAt(left) == '0') {
            return false;
        }

        int res = 0;
        while (left <= right) {
            res = res * 10 + s.charAt(left) - '0';
            left++;
        }

        return res >= 0 && res <= 255;
    }


    public List<List<Integer>> result1 = new LinkedList<>();


    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        if(candidates.length==0) return result1;

        if(candidates.length == 0){
            return result1;
        }
        Arrays.sort(candidates);
        backTraceCombinationSum2(candidates,target,0,new LinkedList<Integer>());
        return result1;




    }

    private void backTraceCombinationSum2(int[] candidates, int target,int begin,LinkedList<Integer> list){
        if(target==0) {
            result1.add(new ArrayList<>(list));
            return;
        }
        if(target>0){
            for (int i = begin; i < candidates.length; i++) {
                if(i>begin&&candidates[i]==candidates[i-1]) continue;
                list.add(candidates[i]);
                backTraceCombinationSum2(candidates, target-candidates[i], i+1, list);
                list.removeLast();
            }
        }
    }


    public String multiply(String num1, String num2) {
        if("0".equals(num1)||"0".equals(num2)){
            return "0";
        }
        int[] result = new int[num1.length()+num2.length()];
        for (int i = num1.length()-1; i >=0 ; i--) {
            int  n = num1.charAt(i)-'0';
            for (int j = num2.length()-1; j>=0; j--) {
                int sum = result[i+j+1]+n*(num2.charAt(j)-'0');
                result[i+j] += sum/10;
                result[i+j+1] = sum%10;
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < result.length; i++) {
            if (i == 0 && result[i] == 0) continue;
            sb.append(result[i]);
        }

        return sb.toString();


    }


    public String longestPalindrome(String s) {
        if (s == null || s.length() < 1) return "";
        int start = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            int len1 = expandAroundCenter(s, i, i);
            int len2 = expandAroundCenter(s, i, i + 1);
            int len = Math.max(len1, len2);
            if (len > end - start) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        return s.substring(start, end + 1);
    }

    private int expandAroundCenter(String s, int left, int right) {
        int L = left, R = right;
        while (L >= 0 && R < s.length() && s.charAt(L) == s.charAt(R)) {
            L--;
            R++;
        }
        return R - L - 1;
    }


    boolean isValidSudoku(char[][] board) {
        int[][] row = new int[9][10];// 哈希表存储每一行的每个数是否出现过，默认初始情况下，每一行每一个数都没有出现过
        // 整个board有9行，第二维的维数10是为了让下标有9，和数独中的数字9对应。
        int[][] col = new int[9][10];// 存储每一列的每个数是否出现过，默认初始情况下，每一列的每一个数都没有出现过
        int[][] box = new int[9][10];// 存储每一个box的每个数是否出现过，默认初始情况下，在每个box中，每个数都没有出现过。整个board有9个box。
        for(int i=0; i<9; i++){
            for(int j = 0; j<9; j++){
                // 遍历到第i行第j列的那个数,我们要判断这个数在其所在的行有没有出现过，
                // 同时判断这个数在其所在的列有没有出现过
                // 同时判断这个数在其所在的box中有没有出现过
                if(board[i][j] == '.') continue;
                int curNumber = board[i][j]-'0';
                if(row[i][curNumber]==1) return false;
                if(col[j][curNumber]==1) return false;
                if(box[j/3 + (i/3)*3][curNumber]==1) return false;

                row[i][curNumber] = 1;// 之前都没出现过，现在出现了，就给它置为1，下次再遇见就能够直接返回false了。
                col[j][curNumber] = 1;
                box[j/3 + (i/3)*3][curNumber] = 1;
            }
        }
        return true;
    }


    List<List<Integer>> result2 = new ArrayList<>();


    public List<List<Integer>> permuteUnique(int[] nums) {

        if(nums.length==0) return result2;
        Arrays.sort(nums);
        backTrackPermuteUnique(nums, new boolean[nums.length], new LinkedList<>());

        return result2;
    }


    private void backTrackPermuteUnique(int[] nums,boolean[] visited,LinkedList<Integer> list){
        if(nums.length==list.size()){
            result2.add(new ArrayList<>(list));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if(visited[i]) continue;
            if(i>0&&visited[i-1]&&nums[i-1]==nums[i]) break;
            list.add(nums[i]);
            visited[i] = true;
            backTrackPermuteUnique(nums,visited,list);
            list.removeLast();
            visited[i] = false;
        }
    }


    public double myPow(double x, int n) {
        //以防负数转正数是越界
        long N = n;
        return n>=0?quickMyPow(x,N):1.0/quickMyPow(x,-N);

    }

    private double quickMyPow(double x,long N){
        double factor = x;
        double ans = 1;

        while (N>0){
            if(N%2==1){
                ans *= factor;
            }
            factor *= factor;
            N = N/2;
        }
        return ans;
    }



    private boolean[][] marked ;

    private int[][] direction = {{-1, 0}, {0, -1}, {0, 1}, {1, 0}};

    private int m;

    private int n;


    public boolean exist(char[][] board, String word) {
        m = board.length;
        if(m==0) return false;
        n = board[0].length;
        marked = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if(dfsExist(i,j,0,board,word)){
                    return true;
                }
            }
        }
        return false;

    }

    private boolean dfsExist(int i,int j, int start,char[][] board, String word){
        if(start==word.length()-1){
            return board[i][j]==word.charAt(start);
        }
        if(board[i][j]==word.charAt(start)){
            marked[i][j] = true;
            for (int k = 0; k < 4; k++) {
                int x = direction[k][0]+i;
                int y = direction[k][1]+j;
                if(inArea(x,y)&&!marked[x][y]){
                    if(dfsExist(x,y,start+1,board,word)){
                        return true;
                    }
                }
            }
            marked[i][j] = false;
        }
        return false;

    }

    private boolean inArea(int x,int y) {
        return x>=0&&x<m&&y>=0&&y<n;
    }





    public int largestRectangleArea(int[] heights) {
        Stack < Integer > stack = new Stack < > ();
        stack.push(-1);
        int maxarea = 0;
        for (int i = 0; i < heights.length; ++i) {
            while (stack.peek() != -1 && heights[stack.peek()] >= heights[i])
                maxarea = Math.max(maxarea, heights[stack.pop()] * (i - stack.peek() - 1));
            stack.push(i);
        }
        while (stack.peek() != -1)
            maxarea = Math.max(maxarea, heights[stack.pop()] * (heights.length - stack.peek() -1));
        return maxarea;
    }



    public List<List<String>> groupAnagrams(String[] strs) {
        int[] count = new int[26];
        HashMap<String,List<String>> map = new HashMap<>();
        for (String str : strs) {
            Arrays.fill(count,0);
            for(char c:str.toCharArray()){
                count[c-'a']++;
            }
            StringBuilder stringBuilder = new StringBuilder();
            for (int i : count) {
                stringBuilder.append(i+"#");
            }
            String key = stringBuilder.toString();
            if(!map.containsKey(key)){
                map.put(key,new ArrayList<>());
            }
            map.get(key).add(str);
        }
        return new ArrayList<>(map.values());
    }





        int N = n * n;

        int [][] rows = new int[N][N + 1];
        int [][] columns = new int[N][N + 1];
        int [][] boxes = new int[N][N + 1];

        char[][] board;

        boolean sudokuSolved = false;

        public boolean couldPlace(int d, int row, int col) {
            int idx = (row / n ) * n + col / n;
            return rows[row][d] + columns[col][d] + boxes[idx][d] == 0;
        }

        public void placeNumber(int d, int row, int col) {

            int idx = (row / n ) * n + col / n;

            rows[row][d]++;
            columns[col][d]++;
            boxes[idx][d]++;
            board[row][col] = (char)(d + '0');
        }

        public void removeNumber(int d, int row, int col) {
            int idx = (row / n ) * n + col / n;
            rows[row][d]--;
            columns[col][d]--;
            boxes[idx][d]--;
            board[row][col] = '.';
        }

        public void placeNextNumbers(int row, int col) {
            if ((col == N - 1) && (row == N - 1)) {
                sudokuSolved = true;
            }
            else {
                if (col == N - 1) backtrack(row + 1, 0);
                else backtrack(row, col + 1);
            }
        }

        public void backtrack(int row, int col) {

            if (board[row][col] == '.') {
                // iterate over all numbers from 1 to 9
                for (int d = 1; d < 10; d++) {
                    if (couldPlace(d, row, col)) {
                        placeNumber(d, row, col);
                        placeNextNumbers(row, col);
                        // if sudoku is solved, there is no need to backtrack
                        // since the single unique solution is promised
                        if (!sudokuSolved) removeNumber(d, row, col);
                    }
                }
            }
            else placeNextNumbers(row, col);
        }

        public void solveSudoku(char[][] board) {
            this.board = board;

            // init rows, columns and boxes
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    char num = board[i][j];
                    if (num != '.') {
                        int d = Character.getNumericValue(num);
                        placeNumber(d, i, j);
                    }
                }
            }
            backtrack(0, 0);
        }


/*
    public static void main(String[] args) {


            List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 202; i++) {
            list.add(i);
        }
        int size = list.size();


        for(int i = 0;i<10;i++){
            int begin = i*size/10;
            int end = (i+1)*size/10;
            if(end>size){
                end = size;
            }


            print(list.subList(begin, end));

        }
    }
*/


    public static void print(List<Integer>list){
            StringBuilder sb  = new StringBuilder();
        for (Integer integer : list) {
            sb.append(integer+",");
        }
        System.out.println(sb.toString());
    }




    public static int max(int n) {
        String s = String.valueOf(n);
        int length = s.length();
        int index = 0;
        int result = 0;
        for (int i = 0; i <length-1 ; i++) {
            if(s.charAt(i+1)-s.charAt(i)>0){
                index = i;
                break;
            }
        }
        System.out.println(index);

        for (int i = 0; i <=length-1 ; i++) {
            if(i!=index){
                result = result*10;
                result += s.charAt(i)-'0';
            }

        }
        return result;
    }






    public static int[] merge2Arr(int[] arr1,int[] arr2){
        int m = arr1.length;
        int n = arr2.length;
        int[] res = new int[m+n];
        int i=0,j=0,index=0;
        while (i<m&&j<n){
            if(arr1[i]>arr2[j]){
                res[index++]=arr2[j++];
            }else {
                res[index++]=arr1[i++];
            }
        }
        while (i<m){
            res[index++]=arr1[i++];
        }
        while (j<n){
            res[index++]=arr2[j++];
        }

        return res;
    }


    /*(String[] args) {

        int[] arr1 = {1,4,6,8};
        int[] arr2 = {2,7,8};
        System.out.println(findUnsortedSubarray(arr1));
        public static void main
    }*/

    public static int findUnsortedSubarray(int[] nums) {
        int firstDesc = Integer.MAX_VALUE;
        for(int i =1;i<nums.length;i++){
            if(nums[i]<nums[i-1]){
                firstDesc = nums[i];
                break;
            }
        }

        int lastInc = Integer.MIN_VALUE;
        for(int i =nums.length-1;i>0;i--){
            if(nums[i]<nums[i-1]){
                lastInc = nums[i-1];
                break;
            }
        }
        System.out.println(firstDesc+","+lastInc);

        int beginIndex = 0, lastIndex = nums.length-1;
        for(int i =0;i<nums.length;i++){
            if(nums[i]>firstDesc){
                beginIndex = i;
                break;
            }
        }

        for(int i =nums.length-1;i>=0;i--){
            if(nums[i]<lastInc){
                lastIndex = i;
                break;
            }
        }
        return lastIndex-beginIndex+1;

    }

    public ListNode hasCycle(ListNode head) {
        ListNode slow = head;
        ListNode first = head;
        while (slow!=null&&first!=null&&first.next!=null){
            slow = slow.next;
            first = first.next.next;
            if(slow==first){
                break;
            }
        }
        if(first==null||first.next==null){
            return null;
        }
        while (head!=slow){
            head = head.next;
            slow = slow.next;
        }
        return head;

    }


    public int[] countBits(int num) {
        int[] ans = new int[n+1];
        int b= 1,i=0;
        while (b<=num){
            while (i<b&&i+b<=num){
                ans[i+b] = ans[i]+1;
                i++;
            }
            i=0;
            b = b<<1;
        }
        return ans;

    }

    public int countSubstrings(String s) {
        int len = s.length();
        int ans = 0;
        for (int i = 0; i < 2 * len; i++) {
            int left = i/2;
            int right = (i+1)/2;
            while (left>=0&&right<len&&s.charAt(left)==s.charAt(right)){
                ans++;
                left--;
                right++;
            }
        }
        return ans;

    }

    public static void main(String[] args) {


        StringBuilder sb = new StringBuilder();
        try {
            Scanner in=new Scanner(new File("/Users/leiyuchao/Desktop/外仓供应商.txt"));
            while(in.hasNext()){
                String str=in.nextLine();
                if(str.length()>0){
                    sb.append(str);
                }
                System.out.println(str);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(sb.toString());
    }
}



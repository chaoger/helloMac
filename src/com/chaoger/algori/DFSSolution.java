package com.chaoger.algori;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * dfs 7题
 */
public class DFSSolution {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    /**
     * 验证二叉搜索树
     */

    /**
     * 二叉树的最大深度:给定一个二叉树，找出其最大深度。
     * 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数
     * @param root
     * @return
     */
    public int maxDepth(TreeNode root) {
        if(root==null){
            return 0;
        }else{
            return Math.max(maxDepth(root.left),maxDepth(root.right))+1;
        }
    }


    /**
     * 从前序与中序遍历序列构造二叉树
     */


    /**
     * 二叉树中的最大路径和
     */



    /**
     * 打家劫舍 III:在上次打劫完一条街道之后和一圈房屋后，小偷又发现了一个新的可行窃的地区。
     * 这个地区只有一个入口，我们称之为“根”。 除了“根”之外，每栋房子有且只有一个“父“房子与之相连。
     * 一番侦察之后，聪明的小偷意识到“这个地方的所有房屋的排列类似于一棵二叉树”。
     * 如果两个直接相连的房子在同一天晚上被打劫，房屋将自动报警。
     * 计算在不触动警报的情况下，小偷一晚能够盗取的最高金额。
     * @param root
     * @return
     */
    public int rob(TreeNode root) {
        int[] res = dfsRob(root);
        return Math.max(res[0],res[1]);

    }

    private int[] dfsRob(TreeNode root){
        if(root==null){
            return new int[]{0,0};
        }
        //left[0] 偷、left[1] 不偷
        int[] left = dfsRob(root.left);
        int[] right = dfsRob(root.right);

        int robValue = root.val + left[1]+right[1];
        int skipValue = Math.max(left[0],left[1])+Math.max(right[0],right[1]);
        return new int[]{robValue,skipValue};

    }



    /**
     * 二叉树展开为链表:给定一个二叉树，原地将它展开为一个单链表。
     *
     * @param root
     */
    public void flatten(TreeNode root) {
        //解决方案也可以采用先序遍历的方式、不过时间复杂度为O(n)
        while (root!=null){
            while (root.left!=null){
                TreeNode cur = root.left;
                TreeNode pre = cur;
                while (pre.right!=null){
                    pre = pre.right;
                }
                pre.right = root.right;
                root.left = null;
                root.right = cur;
            }
            root = root.right;
        }
    }



    /**
     * 字符串解码：给定一个经过编码的字符串，返回它解码后的字符串。
     * 编码规则为: k[encoded_string]，表示其中方括号内部的 encoded_string 正好重复 k 次。注意 k 保证为正整数。
     * 你可以认为输入字符串总是有效的；输入字符串中没有额外的空格，且输入的方括号总是符合格式要求的。
     * 此外，你可以认为原始数据不包含数字，所有的数字只表示重复的次数 k ，例如不会出现像 3a 或 2[4] 的输入。
     * @param s
     * @return
     */
    public static String decodeString(String s) {
        int index = 0;
        LinkedList<String> stk = new LinkedList<String>();
        while (index<s.length()){
            char ch = s.charAt(index);
            if(Character.isDigit(ch)){
                StringBuilder sb = new StringBuilder();
                while (Character.isDigit(ch)){
                    sb.append(ch);
                    ch = s.charAt(++index);
                }
                stk.addLast(sb.toString());
            }else if(ch=='['||Character.isLetter(ch)){
                stk.addLast(String.valueOf(ch));
                index++;
            }else {
                List<String> list = new ArrayList<>();
                while (!"[".equals(stk.peekLast())){
                    list.add(stk.removeLast());
                }
                Collections.reverse(list);
                stk.removeLast();
                int times = Integer.valueOf(stk.removeLast());
                StringBuilder sb = new StringBuilder();
                for (String s1 : list) {
                    sb.append(s1);
                }
                String curString = sb.toString();
                for (int i = 1; i < times; i++) {
                    sb.append(curString);
                }
                stk.addLast(sb.toString());
                index++;
            }
        }

        StringBuilder result = new StringBuilder();
        for (String s1 : stk) {
            result.append(s1);
        }
        return result.toString();

    }
}

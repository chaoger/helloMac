package com.chaoger.algori;

public class DFSSolution {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }


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
}

package com.chaoger.algori;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BTreeSolution {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    //实现 Trie (前缀树)



    /**
     * 翻转二叉树
     * @param root
     * @return
     */
    public TreeNode invertTree(TreeNode root) {
        if(root==null){
            return root;
        }
        TreeNode left = invertTree(root.left);
        TreeNode right = invertTree(root.right);
        root.left = right;
        root.right = left;
        return root;


    }


    /**
     * 二叉树的最近公共祖先:给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null || root == p || root == q) return root;
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if(left == null) return right;
        if(right == null) return left;
        return root;
    }



    /**
     * 二叉树的序列化与反序列化：请设计一个算法来实现二叉树的序列化与反序列化。
     * 这里不限定你的序列 / 反序列化算法执行逻辑，你只需要保证一个二叉树可以
     * 被序列化为一个字符串并且将这个字符串反序列化为原始的树结构。
     * @param root
     * @return
     */
    public String rserialize(TreeNode root, String str) {
        if (root == null) {
            str += "None,";
        } else {
            str += str.valueOf(root.val) + ",";
            str = rserialize(root.left, str);
            str = rserialize(root.right, str);
        }
        return str;
    }

    public String serialize(TreeNode root) {
        return rserialize(root, "");
    }

    public TreeNode rdeserialize(List<String> l) {
        if (l.get(0).equals("None")) {
            l.remove(0);
            return null;
        }

        TreeNode root = new TreeNode(Integer.valueOf(l.get(0)));
        l.remove(0);
        root.left = rdeserialize(l);
        root.right = rdeserialize(l);

        return root;
    }

    public TreeNode deserialize(String data) {
        String[] data_array = data.split(",");
        List<String> data_list = new LinkedList<String>(Arrays.asList(data_array));
        return rdeserialize(data_list);
    }





    /**
     * 把二叉搜索树转换为累加树
     * @return
     */
    int sum = 0;
    public TreeNode convertBST(TreeNode root) {
        if (root == null) {
            return null;
        }
        convertBST(root.right);
        sum += root.val;
        root.val = sum;
        convertBST(root.left);
        return root;
    }





    /**
     * 二叉树的直径
     * @return
     */
    int ans;
    public int diameterOfBinaryTree(TreeNode root) {
        ans = 1;
        deep(root);
        return ans-1;

    }

    private int deep(TreeNode root){
        if(root==null){
            return 0;
        }
        int L = deep(root.left);
        int R = deep(root.right);

        ans = Math.max(ans,L+R+1);
        return Math.max(L,R)+1;
    }




    /**
     * 合并二叉树
     * @param t1
     * @param t2
     * @return
     */
    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        if(t1==null) return t2;
        if(t2==null) return t1;
        Queue<TreeNode> queue = new LinkedList<>();
        Queue<TreeNode> queue1 = new LinkedList<>();
        Queue<TreeNode> queue2 = new LinkedList<>();

        TreeNode root = new  TreeNode(t1.val+t2.val);
        queue.offer(root);
        queue1.offer(t1);
        queue2.offer(t2);
        while (!queue1.isEmpty()&&!queue2.isEmpty()){
            TreeNode node = queue.poll();
            TreeNode node1 = queue1.poll();
            TreeNode node2 = queue2.poll();
            TreeNode left1 = node1.left;
            TreeNode right1 = node1.right;
            TreeNode left2 = node2.left;
            TreeNode right2 = node2.right;
            if(left1!=null||left2!=null){
                if(left1!=null&&left2!=null){
                    node.left = new TreeNode(left1.val+left2.val);
                    queue.offer(node.left);
                    queue1.offer(left1);
                    queue2.offer(left2);
                }else if(left1!=null){
                    node.left = left1;
                }else {
                    node.left = left2;
                }
            }

            if(right1!=null||right2!=null){
                if(right1!=null&&right2!=null){
                    node.right = new TreeNode(right1.val+right2.val);
                    queue.offer(node.right);
                    queue1.offer(right1);
                    queue2.offer(right2);
                }else if(right1!=null){
                    node.right = right1;
                }else {
                    node.right = right2;
                }
            }
        }

        return root;

    }


    //路径总和 III

}

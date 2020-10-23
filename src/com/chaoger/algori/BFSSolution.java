package com.chaoger.algori;

import java.util.*;

/**
 * bfs 5题
 */
public class BFSSolution {


    public class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
    }


    /**
     * 二叉树的层序遍历:给你一个二叉树，请你返回其按
     * 层序遍历 得到的节点值。 （即逐层地，从左到右访问所有节点）。
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if(root==null) return res;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()){
            int size = queue.size();
            List<Integer> level = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                level.add(node.val);
                if(node.left!=null){
                    queue.offer(node.left);
                }
                if(node.right!=null){
                    queue.offer(node.right);
                }
            }
            res.add(level);

        }
        return res;

    }



    /**
     * 对称二叉树:给定一个二叉树，检查它是否是镜像对称的。
     * @param root
     * @return
     */
    public boolean isSymmetric(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<String> level = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (node != null) {
                    queue.offer(node.left);
                    queue.offer(node.right);

                    level.add(String.valueOf(node.val));
                } else {
                    level.add("#");
                }
            }
            for (int i = 0; i < level.size()/2; i++) {
                if(!level.get(i).equals(level.get(level.size()-1-i))){
                    return false;
                }
            }
        }
        return true;

    }


    /**
     * 岛屿数量:给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
     * 岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。
     * 此外，你可以假设该网格的四条边均被水包围。
     * @param grid
     * @return
     */
    public int numIslands(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int res = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if(grid[i][j]=='1'){
                    res++;
                    Queue<Integer> neighbors = new LinkedList<>();
                    neighbors.offer(i*n+j);
                    grid[i][j] = '0';
                    while (!neighbors.isEmpty()){
                        Integer poll = neighbors.poll();
                        int row = poll/n;
                        int col = poll%n;
                        if(row-1>=0&&grid[row-1][col]=='1'){
                            neighbors.offer((row-1)*n+col);
                            grid[row-1][col] = '0';
                        }
                        if(row+1<m&&grid[row+1][col]=='1'){
                            neighbors.offer((row+1)*n+col);
                            grid[row+1][col] = '0';

                        }
                        if(col-1>=0&&grid[row][col-1]=='1'){
                            neighbors.offer(row*n+col-1);
                            grid[row][col-1] = '0';

                        }
                        if(col+1<n&&grid[row][col+1]=='1'){
                            neighbors.offer(row*n+col+1);
                            grid[row][col+1] = '0';

                        }
                    }
                }
            }
        }

        return res;
    }



    /**
     * 课程表:你这个学期必须选修 numCourse 门课程，记为 0 到 numCourse-1 。
     * 在选修某些课程之前需要一些先修课程。 例如，想要学习课程 0 ，你需要先完成课程 1 ，我们用一个匹配来表示他们：[0,1]
     * 给定课程总量以及它们的先决条件，请你判断是否可能完成所有课程的学习？
     * @param numCourses
     * @param prerequisites
     * @return
     */
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        //度数、依赖的前置条件
        //度数、依赖的前置条件
        int[] degrees = new int[numCourses];
        int visited = 0;
        //课程的依赖关系map
        Map<Integer,List<Integer>> courseMap = new HashMap<>();
        for (int i = 0; i < numCourses; i++) {
            courseMap.put(i,new ArrayList<>());
        }
        for (int[] arr : prerequisites) {
            courseMap.get(arr[1]).add(arr[0]);
            degrees[arr[0]]++;
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if(degrees[i]==0){
                queue.offer(i);
                visited++;
            }
        }
        while (!queue.isEmpty()){
            Integer course = queue.poll();
            for (Integer i : courseMap.get(course)) {
                degrees[i]--;
                if(degrees[i]==0){
                    queue.offer(i);
                    visited++;
                }
            }
        }
        return visited == numCourses;
    }

    /**
     * 删除无效括号
     * @return
     */

}

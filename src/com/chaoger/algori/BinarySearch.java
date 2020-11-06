package com.chaoger.algori;

/**
 * 二分查找
 */
public class BinarySearch {

    /**
     * 搜索二维矩阵 II:编写一个高效的算法来搜索 m x n 矩阵 matrix 中的一个目标值 target。该矩阵具有以下特性：
     * 每行的元素从左到右升序排列。
     * 每列的元素从上到下升序排列。
     * @param matrix
     * @param target
     * @return
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length;
        if(m==0) return false;
        int n = matrix[0].length;
        int i = 0,j=n-1;
        while (i<m&&j>=0){
            if(matrix[i][j] == target){
                return true;
            }else if(matrix[i][j]>target){
                j--;
            }else {
                i++;
            }
        }
        return false;

    }
}

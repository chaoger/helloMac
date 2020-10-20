package com.chaoger.algori;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * 贪心
 */
public class GreedySolution {


    //会议室ii



    //根据身高重建队列
    public int[][] reconstructQueue(int[][] people) {
        //身高倒序、数量顺序排列
        Arrays.sort(people, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0]==o2[0]?o1[1]-o2[1]:o2[0]-o1[0];
            }
        });
        List<int[]> list = new ArrayList<>();
        for (int[] p : people) {
            list.add(p[1],p);
        }
        int n = people.length;
        return list.toArray(new int[n][2]);
    }



}

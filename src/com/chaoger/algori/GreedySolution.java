package com.chaoger.algori;

import java.util.*;

/**
 * 贪心
 */
public class GreedySolution {


    /**
     * 会议室ii：给定一个会议时间安排的数组，每个会议时间都会包括开始和结束的时间
     * [[s1,e1],[s2,e2],...] (si < ei)，为避免会议冲突，
     * 同时要考虑充分利用会议室资源，请你计算至少需要多少间会议室，才能满足这些会议安排。
     *
     * 示例 1:
     *
     * 输入: [[0, 30],[5, 10],[15, 20]]
     * 输出: 2
     * 示例 2:

     * 输入: [[7,10],[2,4]]
     * 输出: 1
     * @param intervals
     * @return
     */
    public int minMeetingRooms(int[][] intervals) {
        if(intervals == null || intervals.length == 0){
            return 0;
        }
        Arrays.sort(intervals,(o1,o2)->o1[0]-o2[0]);
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        queue.offer(intervals[0][1]);
        for(int i = 1;i < intervals.length;i++){
            if(intervals[i][0]>=queue.peek()){
                queue.poll();
            }
            queue.offer(intervals[i][1]);
        }
        return queue.size();
    }


    //

    /**
     * 根据身高重建队列：假设有打乱顺序的一群人站成一个队列。 每个人由一个整数对(h, k)表示，
     * 其中h是这个人的身高，k是排在这个人前面且身高大于或等于h的人数。 编写一个算法来重建这个队列。
     *
     * @param people
     * @return
     */
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

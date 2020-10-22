package com.chaoger.algori;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 分治
 */
public class DivideSolution {


    class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
            next = null;
        }
    }


    /**
     * 合并K个升序链表：给你一个链表数组，每个链表都已经按升序排列。
     *
     * 请你将所有链表合并到一个升序链表中，返回合并后的链表。
     * @param lists
     * @return
     */
    public ListNode mergeKLists(ListNode[] lists) {
        if(lists.length==0){
            return null;
        }

        return mergeLists(lists,0,lists.length-1);

    }

    public ListNode mergeLists(ListNode[] lists,int begin,int end) {
        if(begin==end){
            return lists[begin];
        }
        int mid = begin+(end-begin)/2;
        ListNode l1 = mergeLists(lists,begin,mid);
        ListNode l2 = mergeLists(lists,mid+1,end);
        return merge2List(l1,l2);

    }

    public ListNode merge2List(ListNode list1,ListNode list2) {
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        while (list1!=null&&list2!=null){
            if(list1.val>list2.val){
                cur.next = list2;
                list2 = list2.next;
            }else {
                cur.next = list1;
                list1 = list1.next;
            }
            cur = cur.next;
        }
        cur.next = list1==null?list2:list1;
        return dummy.next;

    }




    /**
     * 数组中的第K个最大元素：在未排序的数组中找到第 k 个最大的元素。
     * 请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
     * @param nums
     * @param k
     * @return
     */
    public int findKthLargest(int[] nums, int k) {
        Queue<Integer> queue = new PriorityQueue<>();
        for(int num : nums){
            queue.add(num);
            //当queue的大小大于k，每次弹出堆顶的最小元素；
            if(queue.size() > k) queue.poll();
        }
        return queue.peek();
    }


    //// TODO: 2020-10-22 快排序

}

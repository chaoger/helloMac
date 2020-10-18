package com.chaoger.algori;

/**
 * 双指针
 */
public class DoublePointSolution {

    class ListNode {
      int val;
      ListNode next;
      ListNode(int x) {
          val = x;
          next = null;
      }
    }

    //

    /**
     * 环形链表:给定一个链表，判断链表是否有环
     * @param head
     * @return
     */
    public boolean hasCycle(ListNode head) {
        ListNode slow = head;
        ListNode first = head;
        while (first!=null&&first.next!=null){
            slow = slow.next;
            first = first.next.next;

            if(slow==first){
                return true;
            }
        }
        return false;
    }




    /**
     * 环形链表ii:给定一个链表，返回链表开始入环的第一个节点
     * @param head
     * @return
     */
    public ListNode detectCycle(ListNode head) {
        ListNode slow = head;
        ListNode first = head;
        while (first!=null&&first.next!=null){
            slow = slow.next;
            first = first.next.next;

            if(slow==first){
                break;
            }
        }
        if(first==null||first.next==null){
            return null;
        }
        slow = head;
        while (slow!=first){
            slow = slow.next;
            first = first.next;
        }

        return slow;

    }



    //回文链表

}

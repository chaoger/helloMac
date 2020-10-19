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





    /**
     * 回文链表:请判断一个链表是否为回文链表。
     * @param head
     * @return
     */
    public  boolean isPalindrome(ListNode head) {
        ListNode dummy = new ListNode(-1);
        dummy.next  = head;
        ListNode slow = dummy;
        ListNode first = dummy;
        while (first!=null&&first.next!=null){
            slow = slow.next;
            first = first.next.next;
        }
        ListNode head2 = reverse(slow.next);
        slow.next = null;
        while (head!=null&&head2!=null){
            if(head.val!=head2.val){
                return false;
            }
            head = head.next;
            head2 = head2.next;
        }
        return true;
    }

    private ListNode reverse(ListNode head){
        ListNode pre = null;
        while (head!=null){
            ListNode next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }

}

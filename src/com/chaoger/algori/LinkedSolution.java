package com.chaoger.algori;

import java.util.List;

public class LinkedSolution {

    class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    /**
     * 两数相加:给出两个 非空 的链表用来表示两个非负的整数。
     * 其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
     * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
     * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
     * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
     * 输出：7 -> 0 -> 8
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode p = dummy;
        int sum = 0,carry = 0,s1=0,s2=0;
        while (l1!=null||l2!=null){

            if(l1!=null){
                s1 =  l1.val;
                l1 = l1.next;
            }

            if(l2!=null){
                s2 =  l2.val;
                l2 = l2.next;
            }
            sum = s1+s2+carry;
            carry = sum/10;
            p.next = new ListNode(sum%10);
            p = p.next;

        }

        if(carry>0){
            p.next = new ListNode(carry);
        }
        return dummy.next;


    }



    /**
     *  删除链表的倒数第N个节点:给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。
     * @param head
     * @param n
     * @return
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        int len = 0;
        if(head ==null) return head;
        ListNode p = head;
        while (p!=null){
            p = p.next;
            len++;
        }
        if(len<n) {
            return head;
        }else if(len==n){
            return head.next;
        }else {
            p = head;
            for (int i = 0; i < len-1-n; i++) {
                p = p.next;
            }
            p.next = p.next.next;
            return head;
        }
    }



    /**
     * 合并两个有序链表:将两个升序链表合并为一个新的 升序 链表并返回。
     * 新链表是通过拼接给定的两个链表的所有节点组成的。
     * @param l1
     * @param l2
     * @return
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        while (l1!=null&&l2!=null){
            if(l1.val<=l2.val){
                cur.next = l1;
                l1 = l1.next;
            }else {
                cur.next = l2;
                l2 = l2.next;
            }
            cur = cur.next;
        }

        cur.next = l1==null?l2:l1;
        return dummy.next;
    }



    /**
     * 排序链表:给你链表的头结点 head ，请将其按 升序 排列并返回 排序后的链表 。
     * @param head
     * @return
     */
    public ListNode sortList(ListNode head) {

        if (head == null || head.next == null)
            return head;
        ListNode fast = head.next, slow = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode tmp = slow.next;
        slow.next = null;
        ListNode left = sortList(head);
        ListNode right = sortList(tmp);
        ListNode h = new ListNode(0);
        ListNode res = h;
        while (left != null && right != null) {
            if (left.val < right.val) {
                h.next = left;
                left = left.next;
            } else {
                h.next = right;
                right = right.next;
            }
            h = h.next;
        }
        h.next = left != null ? left : right;
        return res.next;

    }




    /**
     * 相交链表:编写一个程序，找到两个单链表相交的起始节点。
     * @param headA
     * @param headB
     * @return
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        int lenA = 0;
        int lenB = 0;
        ListNode cur = headA;
        while (cur!=null){
            cur = cur.next;
            lenA++;
        }
        cur = headB;
        while (cur!=null){
            cur = cur.next;
            lenB++;
        }

        if(lenA>lenB){
            for (int i = 0; i < lenA-lenB; i++) {
                headA = headA.next;
            }
        }else {
            for (int i = 0; i < lenB-lenA; i++) {
                headB = headB.next;
            }
        }

        while (headA!=headB){
            headA = headA.next;
            headB = headB.next;
        }

        return headA;
    }



    /**
     * 反转链表:反转一个单链表。
     * @param head
     * @return
     */
    public ListNode reverseList(ListNode head) {
        if(head==null){
            return head;
        }
        ListNode pre = null;
        ListNode cur = head;
        ListNode next ;
        while (cur!=null){
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;

    }
}

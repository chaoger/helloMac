package com.chaoger.struct.linked;




class ListNode {
      int val;
      ListNode next;
      ListNode(int x) { val = x; }
  }
class Main {
    /*
    *单向链表 反转
     */
    public ListNode reverseList(ListNode head) {
        ListNode pre = null;
        ListNode next = null;
        while(head != null){
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;

        }
        return pre;
    }

    public ListNode reverseBetween(ListNode head, int m, int n) {
        if(head.next==null || m == n) return head;
        ListNode root = head;
        for (int i = 0; i < m-2; i++) {
            head = head.next;
        }
        ListNode cutPre = head;
        ListNode cutCur = head.next;
        head = head.next;
        ListNode pre = head;
        head = head.next;
        ListNode next = head.next;
        for(int i=0;i<n-m;i++){
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        cutPre.next = pre;
        cutCur.next = next;
        return  root;

    }


}

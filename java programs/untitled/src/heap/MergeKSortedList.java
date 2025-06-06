package heap;

import java.util.List;
import java.util.PriorityQueue;

public class MergeKSortedList {
    // divide and conquer
    public ListNode mergeKLists(ListNode[] lists) {
        if(lists.length == 0) return null;
        return helper(lists, 0, lists.length-1);
    }

    private ListNode helper(ListNode[] lists, int s, int e) {
        if(e-s <= 0) return lists[s];
        int mid = s + (e-s)/2;
        ListNode l1 = helper(lists, s, mid);
        ListNode l2 = helper(lists, mid+1, e);
        ListNode head = null;
        ListNode cur = null;
        while(l1 != null || l2 != null){
            if(l1!=null && l2!=null){
                if(l1.val<= l2.val){
                    if(cur == null) head = l1;
                    else cur.next = l1;
                    cur = l1;
                    l1 = l1.next;
                }
                else{
                    if(cur == null) {head = l2;}
                    else cur.next = l2;
                    cur = l2;
                    l2 = l2.next;
                }
            }
            else if(l1 != null){
                if(cur == null) {head = l1;}
                else{
                    cur.next = l1;
                }
                break;
            }
            else if(l2 != null){
                if(cur == null) {head = l2;}
                else cur.next = l2;
                break;
            }
            else{
                break;
            }
        }
        return head;
    }


//    // heap
//    public ListNode mergeKLists(ListNode[] lists) {
//        PriorityQueue<ListNode> pq = new PriorityQueue<>(
//                (p,q)-> p.val - q.val
//        );
//        for(ListNode node : lists){
//            pq.offer(node);
//        }
//        ListNode head = null;
//        ListNode cur = null;
//        ListNode pre = null;
//        while(!pq.isEmpty()){
//            ListNode node = pq.poll();
//            if(pre == null) head = node;
//            pre = node;
//            if(node.next != null) pq.offer(node.next);
//        }
//        return head;
//    }
}

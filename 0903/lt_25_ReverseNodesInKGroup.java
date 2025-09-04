class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
}

class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prevGroup = dummy;

        while (true) {
            ListNode kth = prevGroup;
            for (int i = 0; i < k && kth != null; i++) kth = kth.next;
            if (kth == null) break;

            ListNode groupNext = kth.next;
            ListNode prev = groupNext, curr = prevGroup.next;
            while (curr != groupNext) {
                ListNode tmp = curr.next;
                curr.next = prev;
                prev = curr;
                curr = tmp;
            }
            ListNode tmp = prevGroup.next;
            prevGroup.next = kth;
            prevGroup = tmp;
        }
        return dummy.next;
    }
}

/*
解題思路：
1. 每 k 個節點反轉一次。
2. 使用 dummy 節點簡化邊界操作。
3. 使用三指針方法反轉節點。
時間：O(n)，空間：O(1)
*/

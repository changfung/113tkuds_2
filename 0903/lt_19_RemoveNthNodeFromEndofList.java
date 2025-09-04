// Definition for singly-linked list.
class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        // 建立 dummy node，指向 head
        // 這樣即使刪除的是頭節點，也能統一處理
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode fast = dummy;
        ListNode slow = dummy;

        // fast 先前進 n+1 步，確保 slow 停在待刪節點的前一個位置
        for (int i = 0; i <= n; i++) {
            fast = fast.next;
        }

        // fast 與 slow 同步前進，直到 fast 到達尾端
        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }

        // 刪除 slow.next 指向的節點
        slow.next = slow.next.next;

        // 返回刪除後的鏈表頭
        return dummy.next;
    }
}

/*
解題思路：
1. 題目要求刪除「倒數第 n 個節點」。
2. 使用 two pointers（快慢指針）技巧：
   - 建立一個 dummy node 指向 head，避免刪除頭節點時的特殊情況。
   - 先讓 fast 指針前進 n+1 步，保持 fast 與 slow 之間的間距為 n。
   - 然後 fast 和 slow 一起往前走，直到 fast 指向 null。
   - 此時 slow 剛好在「要刪除節點的前一個」位置。
3. 執行刪除：slow.next = slow.next.next。
4. 返回 dummy.next 作為新頭節點。

時間複雜度：O(L)，其中 L 為鏈表長度，僅需一次遍歷。  
空間複雜度：O(1)，使用常數額外空間。
*/
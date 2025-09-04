import java.util.*;

public class LC19_RemoveNth_Node_Clinic {
    // 定義單向鏈結節點
    static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) { this.val = val; }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;

        // 讀取節點值
        for (int i = 0; i < n; i++) {
            curr.next = new ListNode(sc.nextInt());
            curr = curr.next;
        }

        int k = sc.nextInt();

        // 刪除倒數第 k 節點
        ListNode newHead = removeNthFromEnd(dummy.next, k);

        // 輸出刪除後序列
        curr = newHead;
        while (curr != null) {
            System.out.print(curr.val);
            if (curr.next != null) System.out.print(" ");
            curr = curr.next;
        }
        System.out.println();

        sc.close();
    }

    /**
     * 刪除倒數第 k 節點
     */
    public static ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode fast = dummy;
        ListNode slow = dummy;

        // fast 先走 n 步
        for (int i = 0; i < n; i++) {
            fast = fast.next;
        }

        // fast 和 slow 同步移動到 fast 到尾
        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }

        // slow.next 為待刪節點
        slow.next = slow.next.next;

        return dummy.next;
    }
}

/*
解題思路：

1. 問題要求：
   - 給一個單向鏈結 head，刪除倒數第 k 節點。
   - 必須維持其餘順序，並一趟掃描完成。

2. 方法：雙指標（快慢指標）
   - 使用 dummy 節點指向 head，方便刪除頭節點。
   - fast 先走 k 步。
   - slow 從 dummy 同步移動，直到 fast 到尾。
   - slow.next 為倒數第 k 節點 → 刪除。
   - 回傳 dummy.next 作新 head。

3. 範例：
   n=5, nodes=[1,2,3,4,5], k=2
   - fast 先走 2 → fast=3
   - 同步移動：
     slow=0, fast=3 → slow=1, fast=4
     slow=2, fast=5
     fast.next=null → slow.next=4 → 刪除
   → 結果 1 2 3 5

4. 複雜度：
   - 時間：O(n)，一次遍歷鏈結
   - 空間：O(1)，僅使用指標

5. 邊界情況：
   - n=1,k=1 → 刪除後空
   - k=n → 刪除頭節點
   - k=1 → 刪除尾節點
   - 節點值重複 → 不影響指標操作
*/

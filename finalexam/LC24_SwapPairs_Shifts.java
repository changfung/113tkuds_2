import java.util.*;

public class LC24_SwapPairs_Shifts {
    // 定義單向鏈結節點
    static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) { this.val = val; }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String line = sc.nextLine().trim();
        String[] tokens = line.isEmpty() ? new String[0] : line.split("\\s+");

        // 建立鏈結
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;
        for (String t : tokens) {
            curr.next = new ListNode(Integer.parseInt(t));
            curr = curr.next;
        }
        ListNode head = dummy.next;

        // 交換相鄰節點
        ListNode swapped = swapPairs(head);

        // 輸出結果
        curr = swapped;
        while (curr != null) {
            System.out.print(curr.val);
            if (curr.next != null) System.out.print(" ");
            curr = curr.next;
        }
        System.out.println();

        sc.close();
    }

    /**
     * 兩兩交換鏈結節點
     */
    public static ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy;

        while (prev.next != null && prev.next.next != null) {
            ListNode a = prev.next;
            ListNode b = a.next;

            // 交換 a,b
            prev.next = b;
            a.next = b.next;
            b.next = a;

            // prev 移到下一對前
            prev = a;
        }

        return dummy.next;
    }
}

/*
解題思路：

1. 問題要求：
   - 將單向鏈結中相鄰兩節點成對交換位置 (1↔2, 3↔4 …)
   - 長度為奇數時最後一個節點保留

2. 方法：
   - 使用 dummy 節點指向 head，方便交換頭節點
   - prev 指向即將交換對的前一節點
   - 每次交換 a,b：
     prev.next = b
     a.next = b.next
     b.next = a
   - prev 移到 a (下一對前一節點)

3. 範例：
   輸入：1 2 3 4
   - dummy→1→2→3→4
   - prev=dummy, a=1, b=2 → 交換 → dummy→2→1→3→4
   - prev=a=1 → a=3,b=4 → 交換 → dummy→2→1→4→3
   - prev=a=3 → 下一對不存在 → 停止
   - 輸出 2 1 4 3

4. 複雜度：
   - 時間：O(n)，每節點訪問一次
   - 空間：O(1)，僅使用指標

5. 邊界情況：
   - 空鏈結 → 不變
   - 單節點 → 不變
   - 偶數長度 → 全部交換
   - 奇數長度 → 最後一個保留
*/

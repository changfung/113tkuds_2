import java.util.*;

public class LC25_ReverseKGroup_Shifts {
    // 定義單向鏈結節點
    static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) { this.val = val; }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int k = sc.nextInt();
        sc.nextLine(); // 吃掉換行

        String line = sc.nextLine().trim();
        String[] tokens = line.isEmpty() ? new String[0] : line.split("\\s+");

        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;
        for (String t : tokens) {
            curr.next = new ListNode(Integer.parseInt(t));
            curr = curr.next;
        }

        ListNode head = dummy.next;
        ListNode reversed = reverseKGroup(head, k);

        curr = reversed;
        while (curr != null) {
            System.out.print(curr.val);
            if (curr.next != null) System.out.print(" ");
            curr = curr.next;
        }
        System.out.println();

        sc.close();
    }

    /**
     * 每 k 個節點反轉一次
     */
    public static ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prevGroupEnd = dummy;

        while (true) {
            ListNode kth = prevGroupEnd;
            // 檢查是否有 k 個節點
            for (int i = 0; i < k; i++) {
                kth = kth.next;
                if (kth == null) return dummy.next; // 不足 k → 保留
            }

            ListNode groupStart = prevGroupEnd.next;
            ListNode nextGroupStart = kth.next;

            // 反轉區段
            ListNode prev = nextGroupStart;
            ListNode curr = groupStart;
            while (curr != nextGroupStart) {
                ListNode temp = curr.next;
                curr.next = prev;
                prev = curr;
                curr = temp;
            }

            // 連接前一區段
            prevGroupEnd.next = kth;
            prevGroupEnd = groupStart;
        }
    }
}

/*
解題思路：

1. 問題要求：
   - 將單向鏈結每 k 個節點反轉一次
   - 尾部不足 k 節點保持原順序

2. 方法：
   - 使用 dummy 節點指向 head，方便操作頭節點
   - prevGroupEnd 指向上一組尾節點
   - 每次檢查是否有 k 個節點：
       - 若不足 k → 返回
       - 若足夠 → 反轉該區段
   - 反轉區段方法：
       - prev 指向 nextGroupStart
       - curr 遍歷 groupStart → nextGroupStart
       - 逐步修改 curr.next 指向 prev
   - 反轉後，將 prevGroupEnd.next 連接到新的區段頭
   - 更新 prevGroupEnd 到區段尾

3. 範例：
   k=2, input: 1 2 3 4 5
   - 第一組 1 2 → 2 1
   - 第二組 3 4 → 4 3
   - 第三組 5 → 不足 k → 保留
   → output: 2 1 4 3 5

4. 複雜度：
   - 時間 O(n)，每節點遍歷一次
   - 空間 O(1)，原地反轉

5. 邊界情況：
   - k=1 → 不反轉
   - 長度 < k → 不反轉
   - 長度 = k → 整段反轉
   - 長度 = m*k + r（r<k） → 尾巴保持原樣
*/

import java.util.*;

public class LC23_MergeKLists_Hospitals {
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

        List<ListNode> lists = new ArrayList<>();

        // 讀入每條鏈結
        for (int i = 0; i < k; i++) {
            String line = sc.nextLine().trim();
            if (line.equals("-1")) {
                lists.add(null); // 空串列
                continue;
            }

            String[] tokens = line.split("\\s+");
            ListNode dummy = new ListNode(0);
            ListNode curr = dummy;
            for (String t : tokens) {
                int val = Integer.parseInt(t);
                if (val == -1) break;
                curr.next = new ListNode(val);
                curr = curr.next;
            }
            lists.add(dummy.next);
        }

        ListNode merged = mergeKLists(lists);

        // 輸出合併後序列
        ListNode curr = merged;
        while (curr != null) {
            System.out.print(curr.val);
            if (curr.next != null) System.out.print(" ");
            curr = curr.next;
        }
        System.out.println();

        sc.close();
    }

    /**
     * 合併 k 條已排序鏈結
     */
    public static ListNode mergeKLists(List<ListNode> lists) {
        PriorityQueue<ListNode> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a.val));
        // 將所有非空頭節點加入堆
        for (ListNode node : lists) {
            if (node != null) pq.offer(node);
        }

        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;

        while (!pq.isEmpty()) {
            ListNode node = pq.poll();
            tail.next = node;
            tail = tail.next;

            if (node.next != null) pq.offer(node.next);
        }

        return dummy.next;
    }
}

/*
解題思路：

1. 問題要求：
   - 給 k 條已排序單向鏈結 lists。
   - 將所有串列合併成一條升序鏈結，效率必須優於兩兩合併 O(kN)。

2. 方法：最小堆 (PriorityQueue)
   - 初始將每條非空鏈結的頭節點放入堆，堆根為當前最小節點。
   - 每次從堆彈出最小節點：
     - 接到合併結果尾端
     - 如果該節點有 next → 放入堆
   - 重複直到堆空

3. 範例：
   k=3
   1 4 5 -1 → 1→4→5
   1 3 4 -1 → 1→3→4
   2 6 -1 → 2→6

   - 初始化堆：1,1,2 → 彈出 1 → 接上 → 放 4
   - 堆：1,2,4 → 彈出 1 → 放 3
   - 堆：2,3,4 → 彈出 2 → 放 6
   - 依序操作直到堆空 → 合併結果 1 1 2 3 4 4 5 6

4. 複雜度：
   - 時間：O(N log k)，N=總節點數
   - 空間：O(k)，堆大小最多 k

5. 邊界情況：
   - k=0 → 空輸出
   - 所有串列空 → 空輸出
   - 單條串列 → 直接返回
   - 串列長度差極大 → 堆自動處理
*/

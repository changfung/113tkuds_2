import java.util.*;

public class LC21_MergeTwoLists_Clinics {
    // 定義單向鏈結節點
    static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) { this.val = val; }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();

        // 建立第一條鏈結
        ListNode dummy1 = new ListNode(0);
        ListNode curr = dummy1;
        for (int i = 0; i < n; i++) {
            curr.next = new ListNode(sc.nextInt());
            curr = curr.next;
        }
        ListNode l1 = dummy1.next;

        // 建立第二條鏈結
        ListNode dummy2 = new ListNode(0);
        curr = dummy2;
        for (int i = 0; i < m; i++) {
            curr.next = new ListNode(sc.nextInt());
            curr = curr.next;
        }
        ListNode l2 = dummy2.next;

        // 合併兩條已排序鏈結
        ListNode merged = mergeTwoLists(l1, l2);

        // 輸出合併後序列
        curr = merged;
        while (curr != null) {
            System.out.print(curr.val);
            if (curr.next != null) System.out.print(" ");
            curr = curr.next;
        }
        System.out.println();

        sc.close();
    }

    /**
     * 合併兩條已排序鏈結
     */
    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;

        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                tail.next = l1;
                l1 = l1.next;
            } else {
                tail.next = l2;
                l2 = l2.next;
            }
            tail = tail.next;
        }

        // 將剩餘節點接上
        if (l1 != null) tail.next = l1;
        if (l2 != null) tail.next = l2;

        return dummy.next;
    }
}

/*
解題思路：

1. 問題要求：
   - 給兩條已排序單向鏈結 l1, l2。
   - 合併成一條排序鏈結，維持升序。

2. 方法：
   - 使用 dummy 節點建立新鏈結，tail 指向當前尾部。
   - 雙指針：
     - 比較 l1.val 和 l2.val，小者接到 tail，指標前進。
     - tail 前進到新節點。
   - 遍歷完一條鏈結後，將另一條剩餘節點直接接上。

3. 範例：
   l1: 1 2 4
   l2: 1 3 4
   - tail=0(dummy)
   - 比較 1,1 → 接 l1 → tail=1 → l1=2
   - 比較 2,1 → 接 l2 → tail=1 → l2=3
   - 比較 2,3 → 接 l1 → tail=2 → l1=4
   - 比較 4,3 → 接 l2 → tail=3 → l2=4
   - 比較 4,4 → 接 l1 → tail=4 → l1=null
   - 接上 l2=4 → tail=4
   → 合併結果：1 1 2 3 4 4

4. 複雜度：
   - 時間：O(n+m)，遍歷兩條鏈結一次
   - 空間：O(1)，使用指標操作

5. 邊界情況：
   - 一條鏈結為空 → 直接返回另一條
   - 全部元素相等 → 正確排序
   - 長度差極大 → 依舊正確
*/

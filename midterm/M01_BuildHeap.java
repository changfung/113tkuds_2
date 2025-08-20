import java.util.*;

public class M01_BuildHeap {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String type = sc.next();   // "max" 或 "min"
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        buildHeap(arr, type);

        // 輸出 heap 陣列
        for (int i = 0; i < n; i++) {
            System.out.print(arr[i]);
            if (i < n - 1) System.out.print(" ");
        }
    }

    /**
     * 自底向上建堆
     */
    static void buildHeap(int[] arr, String type) {
        int n = arr.length;
        // 從最後一個非葉節點開始 (n/2 - 1)
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapifyDown(arr, n, i, type);
        }
    }

    /**
     * Heapify-Down（調整子樹）
     */
    static void heapifyDown(int[] arr, int n, int i, String type) {
        int extreme = i; // 對 max-heap → 最大值索引；對 min-heap → 最小值索引
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (type.equals("max")) {
            if (left < n && arr[left] > arr[extreme]) extreme = left;
            if (right < n && arr[right] > arr[extreme]) extreme = right;
        } else { // min-heap
            if (left < n && arr[left] < arr[extreme]) extreme = left;
            if (right < n && arr[right] < arr[extreme]) extreme = right;
        }

        if (extreme != i) {
            swap(arr, i, extreme);
            heapifyDown(arr, n, extreme, type);
        }
    }

    static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}

/*
 * Time Complexity: O(n)
 * 說明：
 * 1. HeapifyDown 單次最壞需要 O(log n)，但不是每個節點都跑到葉子。
 * 2. 高度為 h 的節點數量 ≈ n / 2^(h+1)，每層成本 ≈ (n / 2^(h+1)) * h。
 * 3. 累加所有層得總時間 ≤ 2n = O(n)。
 */


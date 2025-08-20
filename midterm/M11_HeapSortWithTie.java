import java.util.*;

public class M11_HeapSortWithTie {
    static class Item {
        int score;
        int idx; // 保留輸入順序
        Item(int s, int i) { score = s; idx = i; }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        Item[] arr = new Item[n];
        for (int i = 0; i < n; i++) {
            int s = sc.nextInt();
            arr[i] = new Item(s, i);
        }

        heapSort(arr);

        for (int i = 0; i < n; i++) {
            System.out.print(arr[i].score + (i == n-1 ? "\n" : " "));
        }
    }

    /** Heap Sort */
    static void heapSort(Item[] arr) {
        int n = arr.length;

        // Build Max-Heap
        for (int i = n/2 - 1; i >= 0; i--) {
            heapify(arr, n, i);
        }

        // 逐步取出最大元素到末尾
        for (int i = n - 1; i > 0; i--) {
            swap(arr, 0, i);
            heapify(arr, i, 0); // heap size = i
        }
    }

    static void heapify(Item[] arr, int size, int i) {
        int largest = i;
        int l = 2*i + 1;
        int r = 2*i + 2;

        if (l < size && compare(arr[l], arr[largest]) > 0) largest = l;
        if (r < size && compare(arr[r], arr[largest]) > 0) largest = r;

        if (largest != i) {
            swap(arr, i, largest);
            heapify(arr, size, largest);
        }
    }

    /** 比較函數：先分數大，再索引小 */
    static int compare(Item a, Item b) {
        if (a.score != b.score) return a.score - b.score;
        return b.idx - a.idx; // Max-Heap 需要反向比較索引
    }

    static void swap(Item[] arr, int i, int j) {
        Item tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}

/*
 * Time Complexity: O(n log n)
 * 說明：
 * 1. 建堆 O(n)，每次 heapify 最多 O(log n)。
 * 2. 提取 n 個元素，每次 heapify O(log n) → O(n log n)。
 * 3. 總體時間複雜度 O(n log n)，空間 O(1)（原地排序）。
 */

import java.util.*;

public class KthSmallestElement {

    // 方法 1：使用大小為 K 的 Max Heap（適合找第 K 小）
    public static int kthSmallestUsingMaxHeap(int[] arr, int k) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

        for (int num : arr) {
            maxHeap.offer(num);
            if (maxHeap.size() > k) {
                maxHeap.poll(); // 移除最大的
            }
        }

        return maxHeap.peek();
    }

    // 方法 2：使用 Min Heap 並提取 K 次
    public static int kthSmallestUsingMinHeap(int[] arr, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (int num : arr) {
            minHeap.offer(num);
        }

        int result = -1;
        for (int i = 0; i < k; i++) {
            result = minHeap.poll();
        }

        return result;
    }

    // 測試主程式
    public static void main(String[] args) {
        test(new int[]{7, 10, 4, 3, 20, 15}, 3, 7);
        test(new int[]{1}, 1, 1);
        test(new int[]{3, 1, 4, 1, 5, 9, 2, 6}, 4, 3);
    }

    private static void test(int[] arr, int k, int expected) {
        System.out.println("陣列: " + Arrays.toString(arr) + ", K = " + k);
        
        int result1 = kthSmallestUsingMaxHeap(arr, k);
        System.out.println("方法1（Max Heap）結果: " + result1 + (result1 == expected ? " ✅" : " ❌"));

        int result2 = kthSmallestUsingMinHeap(arr, k);
        System.out.println("方法2（Min Heap）結果: " + result2 + (result2 == expected ? " ✅" : " ❌"));

        System.out.println("------");
    }
}

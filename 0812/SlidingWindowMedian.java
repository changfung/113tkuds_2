import java.util.*;

public class SlidingWindowMedian {
    // 小的一半（最大頂端）
    private PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
    // 大的一半（最小頂端）
    private PriorityQueue<Integer> minHeap = new PriorityQueue<>();

    public double[] medianSlidingWindow(int[] nums, int k) {
        List<Double> result = new ArrayList<>();

        for (int i = 0; i < nums.length; i++) {
            // 新增元素
            addNum(nums[i]);

            // 移除過期元素
            if (i >= k) {
                removeNum(nums[i - k]);
            }

            // 當 window 大小到達 k，計算中位數
            if (i >= k - 1) {
                result.add(getMedian());
            }
        }

        // 轉成 double[]
        double[] output = new double[result.size()];
        for (int i = 0; i < result.size(); i++) {
            output[i] = result.get(i);
        }
        return output;
    }

    private void addNum(int num) {
        if (maxHeap.isEmpty() || num <= maxHeap.peek()) {
            maxHeap.offer(num);
        } else {
            minHeap.offer(num);
        }
        balanceHeaps();
    }

    private void removeNum(int num) {
        // 延遲移除技巧：直接從對應 heap 移除
        if (num <= maxHeap.peek()) {
            maxHeap.remove(num);
        } else {
            minHeap.remove(num);
        }
        balanceHeaps();
    }

    private void balanceHeaps() {
        // 保證 maxHeap.size() == minHeap.size() 或 +1
        if (maxHeap.size() > minHeap.size() + 1) {
            minHeap.offer(maxHeap.poll());
        } else if (minHeap.size() > maxHeap.size()) {
            maxHeap.offer(minHeap.poll());
        }
    }

    private double getMedian() {
        if (maxHeap.size() == minHeap.size()) {
            return ((double) maxHeap.peek() + minHeap.peek()) / 2;
        }
        return (double) maxHeap.peek();
    }

    // 測試主程式
    public static void main(String[] args) {
        SlidingWindowMedian solver = new SlidingWindowMedian();

        test(solver, new int[]{1,3,-1,-3,5,3,6,7}, 3, new double[]{1,-1,-1,3,5,6});
        test(solver, new int[]{1,2,3,4}, 2, new double[]{1.5,2.5,3.5});
    }

    private static void test(SlidingWindowMedian solver, int[] nums, int k, double[] expected) {
        double[] result = solver.medianSlidingWindow(nums, k);
        System.out.println("輸入: " + Arrays.toString(nums) + ", K=" + k);
        System.out.println("輸出: " + Arrays.toString(result));
        System.out.println("預期: " + Arrays.toString(expected));
        System.out.println(Arrays.equals(result, expected) ? "✅ 通過" : "❌ 錯誤");
        System.out.println("------");
    }
}


import java.util.*;

public class MovingAverageStream {

    private int size;
    private double sum;
    private Queue<Integer> window;

    // 用來找中位數
    private PriorityQueue<Integer> maxHeap; // 小的一半 (最大頂)
    private PriorityQueue<Integer> minHeap; // 大的一半 (最小頂)

    // 用來找 min 和 max
    private TreeMap<Integer, Integer> multiset;

    public MovingAverageStream(int size) {
        this.size = size;
        this.sum = 0;
        this.window = new LinkedList<>();

        this.maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        this.minHeap = new PriorityQueue<>();

        this.multiset = new TreeMap<>();
    }

    public double next(int val) {
        window.offer(val);
        sum += val;
        multiset.put(val, multiset.getOrDefault(val, 0) + 1);
        addToHeaps(val);

        if (window.size() > size) {
            int removed = window.poll();
            sum -= removed;
            removeFromHeaps(removed);
            removeFromMultiset(removed);
        }

        return sum / window.size();
    }

    public double getMedian() {
        if (window.isEmpty()) return 0;

        if (maxHeap.size() == minHeap.size()) {
            return ((double) maxHeap.peek() + minHeap.peek()) / 2;
        } else {
            return maxHeap.peek();
        }
    }

    public int getMin() {
        return multiset.firstKey();
    }

    public int getMax() {
        return multiset.lastKey();
    }

    // ====== Helper Methods ======

    private void addToHeaps(int num) {
        if (maxHeap.isEmpty() || num <= maxHeap.peek()) {
            maxHeap.offer(num);
        } else {
            minHeap.offer(num);
        }
        balanceHeaps();
    }

    private void removeFromHeaps(int num) {
        // Java PriorityQueue 沒有高效 remove，會退化成 O(n)
        if (!maxHeap.remove(num)) {
            minHeap.remove(num);
        }
        balanceHeaps();
    }

    private void balanceHeaps() {
        while (maxHeap.size() > minHeap.size() + 1) {
            minHeap.offer(maxHeap.poll());
        }
        while (minHeap.size() > maxHeap.size()) {
            maxHeap.offer(minHeap.poll());
        }
    }

    private void removeFromMultiset(int num) {
        if (multiset.get(num) == 1) {
            multiset.remove(num);
        } else {
            multiset.put(num, multiset.get(num) - 1);
        }
    }

    // ====== 測試入口 ======

    public static void main(String[] args) {
        MovingAverageStream ma = new MovingAverageStream(3);
        System.out.println("ma.next(1) = " + ma.next(1));     // 1.0
        System.out.println("ma.next(10) = " + ma.next(10));   // 5.5
        System.out.println("ma.next(3) = " + ma.next(3));     // 4.666...
        System.out.println("ma.next(5) = " + ma.next(5));     // 6.0
        System.out.println("ma.getMedian() = " + ma.getMedian()); // 5.0
        System.out.println("ma.getMin() = " + ma.getMin());   // 3
        System.out.println("ma.getMax() = " + ma.getMax());   // 10
    }
}



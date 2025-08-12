import java.util.ArrayList;

public class BasicMinHeapPractice {
    private ArrayList<Integer> heap;

    public BasicMinHeapPractice() {
        heap = new ArrayList<>();
    }

    // 插入元素
    public void insert(int val) {
        heap.add(val);
        heapifyUp(heap.size() - 1);
    }

    // 取出最小元素並移除
    public int extractMin() {
        if (isEmpty()) throw new IllegalStateException("Heap is empty.");

        int min = heap.get(0);
        int last = heap.remove(heap.size() - 1);
        if (!isEmpty()) {
            heap.set(0, last);
            heapifyDown(0);
        }
        return min;
    }

    // 取得最小元素但不移除
    public int getMin() {
        if (isEmpty()) throw new IllegalStateException("Heap is empty.");
        return heap.get(0);
    }

    // 取得 heap 的大小
    public int size() {
        return heap.size();
    }

    // 檢查 heap 是否為空
    public boolean isEmpty() {
        return heap.isEmpty();
    }

    // 往上調整
    private void heapifyUp(int index) {
        while (index > 0) {
            int parent = (index - 1) / 2;
            if (heap.get(index) < heap.get(parent)) {
                swap(index, parent);
                index = parent;
            } else {
                break;
            }
        }
    }

    // 往下調整
    private void heapifyDown(int index) {
        int size = heap.size();
        while (index < size) {
            int left = 2 * index + 1;
            int right = 2 * index + 2;
            int smallest = index;

            if (left < size && heap.get(left) < heap.get(smallest)) {
                smallest = left;
            }

            if (right < size && heap.get(right) < heap.get(smallest)) {
                smallest = right;
            }

            if (smallest != index) {
                swap(index, smallest);
                index = smallest;
            } else {
                break;
            }
        }
    }

    // 交換元素
    private void swap(int i, int j) {
        int temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }

    // 測試程式
    public static void main(String[] args) {
        BasicMinHeapPractice minHeap = new BasicMinHeapPractice();
        int[] inputs = {15, 10, 20, 8, 25, 5};

        for (int val : inputs) {
            minHeap.insert(val);
        }

        System.out.println("Extract Min 順序：");
        while (!minHeap.isEmpty()) {
            System.out.print(minHeap.extractMin() + " ");
        }
        // 預期輸出：5 8 10 15 20 25
    }
}


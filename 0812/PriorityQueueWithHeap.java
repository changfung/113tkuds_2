import java.util.*;

public class PriorityQueueWithHeap {
    private static class Task {
        String name;
        int priority;
        long timestamp; // 為了區分相同優先級的任務，較早加入的優先

        Task(String name, int priority, long timestamp) {
            this.name = name;
            this.priority = priority;
            this.timestamp = timestamp;
        }

        @Override
        public String toString() {
            return "[" + name + ", 優先級: " + priority + "]";
        }
    }

    private List<Task> heap;
    private Map<String, Integer> indexMap; // name -> index
    private long timestampCounter;

    public PriorityQueueWithHeap() {
        heap = new ArrayList<>();
        indexMap = new HashMap<>();
        timestampCounter = 0;
    }

    // 添加任務
    public void addTask(String name, int priority) {
        if (indexMap.containsKey(name)) {
            System.out.println("任務已存在，請使用 changePriority 進行修改。");
            return;
        }
        Task newTask = new Task(name, priority, timestampCounter++);
        heap.add(newTask);
        int index = heap.size() - 1;
        indexMap.put(name, index);
        heapifyUp(index);
    }

    // 執行下一個任務（優先級最高）
    public Task executeNext() {
        if (heap.isEmpty()) return null;

        Task top = heap.get(0);
        Task last = heap.remove(heap.size() - 1);
        indexMap.remove(top.name);

        if (!heap.isEmpty()) {
            heap.set(0, last);
            indexMap.put(last.name, 0);
            heapifyDown(0);
        }
        return top;
    }

    // 查看下一個任務
    public Task peek() {
        return heap.isEmpty() ? null : heap.get(0);
    }

    // 修改任務優先級
    public void changePriority(String name, int newPriority) {
        Integer index = indexMap.get(name);
        if (index == null) {
            System.out.println("找不到該任務：" + name);
            return;
        }

        Task task = heap.get(index);
        int oldPriority = task.priority;
        task.priority = newPriority;

        // 根據優先級變化進行調整
        if (newPriority > oldPriority) {
            heapifyUp(index);
        } else {
            heapifyDown(index);
        }
    }

    // 上浮
    private void heapifyUp(int index) {
        while (index > 0) {
            int parent = (index - 1) / 2;
            if (compare(heap.get(index), heap.get(parent)) > 0) {
                swap(index, parent);
                index = parent;
            } else {
                break;
            }
        }
    }

    // 下沉
    private void heapifyDown(int index) {
        int size = heap.size();
        while (index < size) {
            int left = 2 * index + 1;
            int right = 2 * index + 2;
            int largest = index;

            if (left < size && compare(heap.get(left), heap.get(largest)) > 0) {
                largest = left;
            }

            if (right < size && compare(heap.get(right), heap.get(largest)) > 0) {
                largest = right;
            }

            if (largest != index) {
                swap(index, largest);
                index = largest;
            } else {
                break;
            }
        }
    }

    // 比較兩個任務，根據 priority 與 timestamp
    private int compare(Task a, Task b) {
        if (a.priority != b.priority) {
            return a.priority - b.priority;
        }
        // 如果優先級相同，則較早加入的優先
        return (int) (b.timestamp - a.timestamp); // 越小的 timestamp 優先
    }

    // 交換元素並更新映射
    private void swap(int i, int j) {
        Task temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
        indexMap.put(heap.get(i).name, i);
        indexMap.put(heap.get(j).name, j);
    }

    // 佇列大小
    public int size() {
        return heap.size();
    }

    // 是否為空
    public boolean isEmpty() {
        return heap.isEmpty();
    }

    // 測試用主程式
    public static void main(String[] args) {
        PriorityQueueWithHeap queue = new PriorityQueueWithHeap();

        queue.addTask("備份", 1);
        queue.addTask("緊急修復", 5);
        queue.addTask("更新", 3);

        System.out.println("下一個任務：" + queue.peek()); // 緊急修復

        System.out.println("執行任務：" + queue.executeNext()); // 緊急修復
        System.out.println("執行任務：" + queue.executeNext()); // 更新
        System.out.println("執行任務：" + queue.executeNext()); // 備份

        queue.addTask("維護", 2);
        queue.addTask("檢查", 4);
        queue.changePriority("維護", 6); // 提升維護優先級

        System.out.println("下一個任務：" + queue.peek()); // 維護
    }
}


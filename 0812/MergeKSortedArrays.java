import java.util.*;

public class MergeKSortedArrays {

    // 儲存 Heap 中的元素：值、來源陣列索引、來源陣列內的索引
    private static class HeapNode {
        int value;
        int arrayIndex;
        int elementIndex;

        public HeapNode(int value, int arrayIndex, int elementIndex) {
            this.value = value;
            this.arrayIndex = arrayIndex;
            this.elementIndex = elementIndex;
        }
    }

    public static List<Integer> mergeKSortedArrays(int[][] arrays) {
        List<Integer> result = new ArrayList<>();

        // 使用 Min Heap 儲存當前每個陣列的最小值
        PriorityQueue<HeapNode> minHeap = new PriorityQueue<>(
            Comparator.comparingInt(node -> node.value)
        );

        // 將每個陣列的第一個元素放入 Heap
        for (int i = 0; i < arrays.length; i++) {
            if (arrays[i].length > 0) {
                minHeap.offer(new HeapNode(arrays[i][0], i, 0));
            }
        }

        // 逐步合併
        while (!minHeap.isEmpty()) {
            HeapNode node = minHeap.poll();
            result.add(node.value);

            // 如果該節點的陣列還有下一個元素，加入 Heap
            int nextIndex = node.elementIndex + 1;
            if (nextIndex < arrays[node.arrayIndex].length) {
                int nextVal = arrays[node.arrayIndex][nextIndex];
                minHeap.offer(new HeapNode(nextVal, node.arrayIndex, nextIndex));
            }
        }

        return result;
    }

    // 測試主程式
    public static void main(String[] args) {
        test(new int[][]{{1, 4, 5}, {1, 3, 4}, {2, 6}}, Arrays.asList(1, 1, 2, 3, 4, 4, 5, 6));
        test(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}}, Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));
        test(new int[][]{{1}, {0}}, Arrays.asList(0, 1));
    }

    private static void test(int[][] input, List<Integer> expected) {
        List<Integer> result = mergeKSortedArrays(input);
        System.out.println("輸出: " + result);
        System.out.println("預期: " + expected);
        System.out.println(result.equals(expected) ? "✅ 通過" : "❌ 錯誤");
        System.out.println("------");
    }
}


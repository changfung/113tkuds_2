import java.util.*;

public class M12_MergeKTimeTables {
    static class Entry {
        int time;
        int listIdx;
        int elemIdx;
        Entry(int t, int l, int e) { time = t; listIdx = l; elemIdx = e; }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int K = sc.nextInt();
        List<int[]> lists = new ArrayList<>();

        for (int i = 0; i < K; i++) {
            int len = sc.nextInt();
            int[] arr = new int[len];
            for (int j = 0; j < len; j++) arr[j] = sc.nextInt();
            lists.add(arr);
        }

        List<Integer> merged = mergeKLists(lists);

        for (int i = 0; i < merged.size(); i++) {
            System.out.print(merged.get(i) + (i == merged.size()-1 ? "\n" : " "));
        }
    }

    static List<Integer> mergeKLists(List<int[]> lists) {
        List<Integer> res = new ArrayList<>();
        PriorityQueue<Entry> pq = new PriorityQueue<>(Comparator.comparingInt(e -> e.time));

        // 初始化：每條列表的第一個元素進入堆
        for (int i = 0; i < lists.size(); i++) {
            if (lists.get(i).length > 0)
                pq.offer(new Entry(lists.get(i)[0], i, 0));
        }

        while (!pq.isEmpty()) {
            Entry cur = pq.poll();
            res.add(cur.time);

            int nextIdx = cur.elemIdx + 1;
            if (nextIdx < lists.get(cur.listIdx).length) {
                pq.offer(new Entry(lists.get(cur.listIdx)[nextIdx], cur.listIdx, nextIdx));
            }
        }

        return res;
    }
}

/*
 * Time Complexity: O(N log K)
 * 說明：
 * 1. N = 總元素數，每個元素進出堆一次 → O(log K)。
 * 2. 總時間複雜度 O(N log K)，空間 O(K) 佇列 + O(N) 結果。
 */


import java.util.*;

public class M03_TopKConvenience {
    static class Item {
        String name;
        int qty;
        int idx; // 保留輸入順序（若要作為穩定排序依據）

        Item(String name, int qty, int idx) {
            this.name = name;
            this.qty = qty;
            this.idx = idx;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int K = sc.nextInt();

        List<Item> items = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String name = sc.next();
            int qty = sc.nextInt();
            items.add(new Item(name, qty, i));
        }

        // Min-Heap: 最小在上 → 當超過 K 時彈出最小
        PriorityQueue<Item> heap = new PriorityQueue<>(new Comparator<Item>() {
            public int compare(Item a, Item b) {
                if (a.qty != b.qty) return a.qty - b.qty; // 按銷量升序
                return a.idx - b.idx; // 若相同，依輸入順序
            }
        });

        for (Item it : items) {
            heap.offer(it);
            if (heap.size() > K) {
                heap.poll();
            }
        }

        // 取出後放到陣列再排序（高到低）
        List<Item> result = new ArrayList<>(heap);
        result.sort(new Comparator<Item>() {
            public int compare(Item a, Item b) {
                if (b.qty != a.qty) return b.qty - a.qty; // 銷量降序
                return a.idx - b.idx; // 相同則輸入先後
            }
        });

        // 輸出
        for (Item it : result) {
            System.out.println(it.name + " " + it.qty);
        }
    }
}

/*
 * Time Complexity: O(n log K)
 * 說明：
 * 1. 每筆資料最多進出 heap 一次，單次操作 O(log K)。
 * 2. 總共 n 筆 → O(n log K)。
 * 3. K 遠小於 n 時，效能優於排序全陣列 O(n log n)。
 */


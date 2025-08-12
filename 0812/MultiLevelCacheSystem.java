import java.util.*;

public class MultiLevelCacheSystem<K, V> {

    // 層級結構資料 (容量、成本)
    private static class CacheLevel<K, V> {
        int capacity;
        int cost;
        Map<K, CacheEntry<K, V>> map;
        PriorityQueue<CacheEntry<K, V>> heap; // 儲存優先順序，頻率越高越靠前

        CacheLevel(int capacity, int cost) {
            this.capacity = capacity;
            this.cost = cost;
            this.map = new HashMap<>();
            // 優先佇列依存取頻率(desc)、時間戳記(asc)
            this.heap = new PriorityQueue<>((a, b) -> {
                if (b.freq != a.freq) return b.freq - a.freq;
                return Long.compare(a.timestamp, b.timestamp);
            });
        }
    }

    // 每筆資料
    private static class CacheEntry<K, V> {
        K key;
        V value;
        int freq;
        long timestamp;
        int level;  // 目前所在層級

        CacheEntry(K key, V value, int level) {
            this.key = key;
            this.value = value;
            this.freq = 1;
            this.timestamp = System.nanoTime();
            this.level = level;
        }
    }

    private CacheLevel<K, V>[] levels;

    @SuppressWarnings("unchecked")
    public MultiLevelCacheSystem() {
        // 建立三層快取
        levels = new CacheLevel[3];
        levels[0] = new CacheLevel<>(2, 1);   // L1
        levels[1] = new CacheLevel<>(5, 3);   // L2
        levels[2] = new CacheLevel<>(10, 10); // L3
    }

    public V get(K key) {
        for (int i = 0; i < levels.length; i++) {
            CacheLevel<K, V> level = levels[i];
            if (level.map.containsKey(key)) {
                CacheEntry<K, V> entry = level.map.get(key);
                // 更新頻率和時間戳記
                level.heap.remove(entry);
                entry.freq++;
                entry.timestamp = System.nanoTime();
                level.heap.offer(entry);

                // 嘗試升級
                promoteIfNeeded(entry);

                return entry.value;
            }
        }
        return null; // 不存在
    }

    public void put(K key, V value) {
        // 先判斷是否已存在，若存在就更新
        for (int i = 0; i < levels.length; i++) {
            CacheLevel<K, V> level = levels[i];
            if (level.map.containsKey(key)) {
                CacheEntry<K, V> entry = level.map.get(key);
                level.heap.remove(entry);
                entry.value = value;
                entry.freq++;
                entry.timestamp = System.nanoTime();
                level.heap.offer(entry);

                promoteIfNeeded(entry);
                return;
            }
        }

        // 新增至最底層 L3
        CacheEntry<K, V> newEntry = new CacheEntry<>(key, value, 2);
        addToLevel(newEntry, 2);
    }

    // 把 entry 加入指定層級，若超容量則降級或淘汰
    private void addToLevel(CacheEntry<K, V> entry, int levelIdx) {
        CacheLevel<K, V> level = levels[levelIdx];
        if (level.map.size() >= level.capacity) {
            // 容量滿，找優先權最低的項目降級或移除
            CacheEntry<K, V> toDemote = level.heap.poll();
            if (toDemote != null) {
                level.map.remove(toDemote.key);

                // 如果不是最底層，降級到下一層
                if (levelIdx < levels.length - 1) {
                    toDemote.level = levelIdx + 1;
                    addToLevel(toDemote, levelIdx + 1);
                }
            }
        }
        // 加入此層
        level.map.put(entry.key, entry);
        level.heap.offer(entry);
        entry.level = levelIdx;
    }

    // 頻繁訪問的項目升級層級
    private void promoteIfNeeded(CacheEntry<K, V> entry) {
        int currentLevel = entry.level;
        if (currentLevel == 0) return; // 已在最高層

        CacheLevel<K, V> current = levels[currentLevel];
        CacheLevel<K, V> upper = levels[currentLevel - 1];

        // 判斷是否升級：當前頻率 - 上層最低頻率 > 門檻
        if (upper.map.size() < upper.capacity) {
            // 上層有空間，直接升級
            current.map.remove(entry.key);
            current.heap.remove(entry);
            addToLevel(entry, currentLevel - 1);
        } else {
            CacheEntry<K, V> lowestUpper = upper.heap.peek();
            if (lowestUpper != null && entry.freq > lowestUpper.freq) {
                // 與上層最低頻率交換
                current.map.remove(entry.key);
                current.heap.remove(entry);

                upper.heap.poll();
                upper.map.remove(lowestUpper.key);

                // 降級最低的上層元素
                lowestUpper.level = currentLevel;
                addToLevel(lowestUpper, currentLevel);

                // 升級該元素
                addToLevel(entry, currentLevel - 1);
            }
        }
    }

    // 用於檢視目前各層快取內容（除錯用）
    public void printStatus() {
        for (int i = 0; i < levels.length; i++) {
            System.out.print("L" + (i+1) + ": ");
            List<String> keys = new ArrayList<>(levels[i].map.keySet().size());
            for (CacheEntry<K, V> entry : levels[i].heap) {
                keys.add(entry.key + "(f" + entry.freq + ")");
            }
            System.out.println(keys);
        }
    }

    // ==== 測試 ====

    public static void main(String[] args) {
        MultiLevelCacheSystem<Integer, String> cache = new MultiLevelCacheSystem<>();

        cache.put(1, "A");
        cache.put(2, "B");
        cache.put(3, "C");
        cache.printStatus(); 
        // L1: [2,3] (因為容量2，頻率相對較高)
        // L2: [1]
        // L3: []

        cache.get(1);
        cache.get(1);
        cache.get(2);
        cache.printStatus(); 
        // 1頻率升高應升級到L1
        // L1: [1,2]
        // L2: [3]
        // L3: []

        cache.put(4, "D");
        cache.put(5, "E");
        cache.put(6, "F");
        cache.printStatus(); 
        // 根據頻率與容量調整位置
    }
}


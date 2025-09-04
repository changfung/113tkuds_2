import java.util.*;

public class LC01_TwoSum_THSRHoliday {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 讀取 n 與 target
        int n = sc.nextInt();
        int target = sc.nextInt();

        // 讀取每個班次的可釋出座位數
        int[] seats = new int[n];
        for (int i = 0; i < n; i++) {
            seats[i] = sc.nextInt();
        }

        // 呼叫解法
        int[] result = findTwoSum(seats, target);

        // 輸出結果
        System.out.println(result[0] + " " + result[1]);

        sc.close();
    }

    /**
     * 找出兩個索引 i, j 使得 seats[i] + seats[j] = target
     * 若無解，回傳 {-1, -1}
     */
    public static int[] findTwoSum(int[] seats, int target) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < seats.length; i++) {
            int need = target - seats[i]; // 還需要的數
            if (map.containsKey(need)) {
                // 找到一組解：map.get(need), i
                return new int[]{map.get(need), i};
            }
            // 尚未找到 → 把 seats[i] 放進 map，代表「未來有人需要 target - seats[i]」
            map.put(seats[i], i);
        }

        // 若完全沒找到
        return new int[]{-1, -1};
    }
}

/*
解題思路：

1. 問題要求：
   - 給一個陣列 seats[]，找出兩個不同索引 i, j 使得 seats[i] + seats[j] == target。
   - 若無法組合出 target，輸出 -1 -1。

2. 方法：
   - 使用 HashMap 來記錄「已看過的座位數」與「其索引」。
   - 遍歷陣列時，對於當前數 seats[i]，計算 need = target - seats[i]。
     - 若 map 中存在 need，表示之前有一個數字剛好可以與 seats[i] 相加得到 target，直接輸出。
     - 否則，將 seats[i] 放入 map，等待後續比對。

3. 範例：
   輸入：
   n=4, target=55
   seats = [20, 15, 35, 40]

   遍歷過程：
   i=0 → seats[0]=20，需要 35 → map={} → 放入 (20,0)
   i=1 → seats[1]=15，需要 40 → map={20:0} → 放入 (15,1)
   i=2 → seats[2]=35，需要 20 → map 已有 20 → 回傳 {0,2}

   輸出：0 2

4. 複雜度：
   - 時間：O(n)，每個元素最多查詢與插入一次 HashMap。
   - 空間：O(n)，用來儲存已經遍歷過的數。

5. 邊界情況：
   - n=2 且剛好相加成功 → 能正確輸出。
   - 無任何符合 → 輸出 -1 -1。
   - 陣列含兩個 target/2 且 target 為偶數 → 能正確輸出。
   - 大量數據但答案在最後 → HashMap 可處理完整掃描。
*/


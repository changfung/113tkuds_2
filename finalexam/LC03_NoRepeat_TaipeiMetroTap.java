import java.util.*;

public class LC03_NoRepeat_TaipeiMetroTap {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 讀取輸入字串
        String s = sc.nextLine();

        // 呼叫解法
        int result = lengthOfLongestSubstring(s);

        // 輸出最長長度
        System.out.println(result);

        sc.close();
    }

    /**
     * 找出字串中最長的「無重複字元子字串」長度
     */
    public static int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> lastIndex = new HashMap<>(); // 紀錄字元上次出現位置
        int left = 0;  // 視窗左界
        int maxLen = 0;

        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);

            // 如果字元重複，更新左界
            if (lastIndex.containsKey(c) && lastIndex.get(c) >= left) {
                left = lastIndex.get(c) + 1;
            }

            // 更新字元最後出現位置
            lastIndex.put(c, right);

            // 更新答案
            maxLen = Math.max(maxLen, right - left + 1);
        }

        return maxLen;
    }
}

/*
解題思路：

1. 問題要求：
   - 給一個字串 s，找出最長的不含重複字元的子字串長度。
   - 這代表在某段區間內，所有乘客卡片 ID 都是唯一的。

2. 方法：
   - 使用「滑動視窗」(two pointers) 技巧。
   - 設定兩個指標 left, right：
     - right：逐步向右擴展。
     - left：當遇到重複字元時，移動到「重複字元上次出現位置 +1」。
   - 使用 HashMap<Character, Integer> 記錄每個字元上次出現的位置。

3. 範例：
   輸入 s = "abcabcbb"
   - right=0: 'a' → 窗口 [0,0], 長度=1
   - right=1: 'b' → 窗口 [0,1], 長度=2
   - right=2: 'c' → 窗口 [0,2], 長度=3
   - right=3: 'a' (重複) → left 跳到 1 → 窗口 [1,3], 長度=3
   - right=4: 'b' (重複) → left 跳到 2 → 窗口 [2,4], 長度=3
   → 最終答案 = 3

   輸入 s = "bbbbb"
   - 每次都重複，最大長度=1

   輸入 s = "abba"
   - "ab" 長度=2
   - 遇到第二個 'b' → left 移到 2
   - 最長長度=2

4. 複雜度：
   - 時間：O(n)，n 為字串長度，每個字元最多被訪問兩次（right 擴展一次、left 移動一次）。
   - 空間：O(k)，k 為字元種類數（ASCII → 最多 128）。

5. 邊界情況：
   - s = "" → 輸出 0。
   - s = "aaaa" → 輸出 1。
   - s 全部不重複 → 輸出 |s|。
   - s = "abba" → 輸出 2。
*/

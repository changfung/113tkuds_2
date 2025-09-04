import java.util.*;

public class LC17_PhoneCombos_CSShift {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 讀取輸入數字字串
        String digits = sc.nextLine();

        List<String> result = letterCombinations(digits);

        // 輸出每一組組合
        for (String comb : result) {
            System.out.println(comb);
        }

        sc.close();
    }

    // 電話鍵盤映射：2~9 對應字母
    private static final String[] KEYS = {
        "",     // 0
        "",     // 1
        "abc",  // 2
        "def",  // 3
        "ghi",  // 4
        "jkl",  // 5
        "mno",  // 6
        "pqrs", // 7
        "tuv",  // 8
        "wxyz"  // 9
    };

    /**
     * 回傳所有數字字串的字母組合
     */
    public static List<String> letterCombinations(String digits) {
        List<String> res = new ArrayList<>();
        if (digits == null || digits.length() == 0) return res;

        backtrack(digits, 0, new StringBuilder(), res);
        return res;
    }

    /**
     * 回溯函數
     */
    private static void backtrack(String digits, int index, StringBuilder path, List<String> res) {
        if (index == digits.length()) {
            res.add(path.toString());
            return;
        }

        String letters = KEYS[digits.charAt(index) - '0'];
        for (char c : letters.toCharArray()) {
            path.append(c);
            backtrack(digits, index + 1, path, res);
            path.deleteCharAt(path.length() - 1); // 回溯
        }
    }
}

/*
解題思路：

1. 問題要求：
   - 給一個數字字串 digits（2~9），生成所有可能的電話鍵盤字母組合。
   - 每個數字對應多個字母，組合順序按照鍵盤定義。

2. 方法：回溯（DFS）
   - 對每個位置 index 的數字：
     - 遍歷其對應字母
     - 將字母加入 path，遞迴到下一個 index
     - 回溯刪除最後一個字母
   - 當 index == digits.length() → path 完成 → 加入結果列表

3. 範例：
   digits = "23"
   - 2 → a,b,c
   - 3 → d,e,f
   - 組合：
     ad, ae, af, bd, be, bf, cd, ce, cf

4. 複雜度：
   - 時間：O(3^m * 4^n)，m=數字對應 3 個字母數量，n=對應 4 個字母（7,9）
   - 空間：O(depth)，最大遞迴深度 = digits.length()

5. 邊界情況：
   - digits="" → 輸出空列表
   - 單一數字 → 直接輸出對應字母
   - 包含 7 或 9 → 4 個字母，回溯自動展開
*/

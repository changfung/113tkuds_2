import java.util.*;

public class LC28_StrStr_NoticeSearch {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String haystack = sc.nextLine();
        String needle = sc.nextLine();

        int index = strStr(haystack, needle);
        System.out.println(index);

        sc.close();
    }

    /**
     * 暴力搜尋子字串首次出現位置
     */
    public static int strStr(String haystack, String needle) {
        if (needle.length() == 0) return 0;
        int n = haystack.length();
        int m = needle.length();

        if (m > n) return -1;

        for (int i = 0; i <= n - m; i++) {
            int j = 0;
            while (j < m && haystack.charAt(i + j) == needle.charAt(j)) {
                j++;
            }
            if (j == m) return i;
        }
        return -1;
    }
}

/*
解題思路：

1. 問題要求：
   - 找出子字串 needle 在主字串 haystack 中首次出現的位置
   - 若不存在 → 返回 -1
   - 若 needle 為空 → 返回 0

2. 方法：
   暴力法：
   - 遍歷每個可能起點 i (0 到 n-m)
   - 從 i 開始逐個比較 haystack 與 needle
   - 若完全匹配 → 返回 i
   - 遍歷完成仍未找到 → 返回 -1

   高效法（可選）：
   - KMP 算法：
       - 預處理 needle 的失敗函數
       - 遍歷 haystack 時避免回溯重複比對
       - 時間 O(n+m)

3. 範例：
   haystack="hello", needle="ll"
   - i=0 → "he" != "ll"
   - i=1 → "el" != "ll"
   - i=2 → "ll" == "ll" → 返回 2

4. 複雜度：
   - 暴力 O(n*m)
   - KMP O(n+m)

5. 邊界情況：
   - needle 空 → 回 0
   - needle 長於 haystack → -1
   - haystack 與 needle 相同 → 0
   - 重複前綴 → KMP 更高效
*/

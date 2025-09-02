class Solution {
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) return "";

        String prefix = strs[0]; // 先假設第一個字串是共同前綴

        for (int i = 1; i < strs.length; i++) {
            // 當前字串不以 prefix 開頭，縮短 prefix
            while (!strs[i].startsWith(prefix)) {
                prefix = prefix.substring(0, prefix.length() - 1);
                if (prefix.isEmpty()) return "";
            }
        }
        return prefix;
    }
}

/*
解題思路：
1. 從第一個字串開始，假設它是最長前綴。
2. 逐一與其他字串比較，若不符合則縮短前綴。
3. 時間複雜度 O(n * m)，n 為字串數量，m 為最短字串長度。
4. 空間複雜度 O(1)。
*/


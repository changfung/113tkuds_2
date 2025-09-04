class Solution {
    public int strStr(String haystack, String needle) {
        if (needle.isEmpty()) return 0;
        int hLen = haystack.length(), nLen = needle.length();
        for (int i = 0; i <= hLen - nLen; i++) {
            if (haystack.substring(i, i + nLen).equals(needle)) return i;
        }
        return -1;
    }
}

/*
解題思路：
1. 字串匹配，檢查 haystack 中每個可能的起始位置。
2. 若子字串等於 needle，返回起始索引。
3. 若找不到，返回 -1。
時間：O((h-n+1)*n)，空間：O(1) 除了 substring 生成
*/

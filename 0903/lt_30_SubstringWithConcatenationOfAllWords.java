import java.util.*;

class Solution {
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> res = new ArrayList<>();
        if (s.length() == 0 || words.length == 0) return res;

        int wordLen = words[0].length();
        int totalWords = words.length;
        int totalLen = wordLen * totalWords;

        Map<String, Integer> wordCount = new HashMap<>();
        for (String w : words) wordCount.put(w, wordCount.getOrDefault(w, 0) + 1);

        // 對每個偏移量進行滑動窗口
        for (int i = 0; i < wordLen; i++) {
            int left = i, count = 0;
            Map<String, Integer> seen = new HashMap<>();
            for (int j = i; j <= s.length() - wordLen; j += wordLen) {
                String sub = s.substring(j, j + wordLen);
                if (wordCount.containsKey(sub)) {
                    seen.put(sub, seen.getOrDefault(sub, 0) + 1);
                    count++;

                    // 如果某單詞出現次數超過限制，左移窗口
                    while (seen.get(sub) > wordCount.get(sub)) {
                        String leftWord = s.substring(left, left + wordLen);
                        seen.put(leftWord, seen.get(leftWord) - 1);
                        left += wordLen;
                        count--;
                    }

                    if (count == totalWords) {
                        res.add(left);
                        // 左移窗口繼續尋找下一個
                        String leftWord = s.substring(left, left + wordLen);
                        seen.put(leftWord, seen.get(leftWord) - 1);
                        left += wordLen;
                        count--;
                    }
                } else {
                    // 遇到不在 words 的字串，重置窗口
                    seen.clear();
                    count = 0;
                    left = j + wordLen;
                }
            }
        }

        return res;
    }
}

/*
解題思路：
1. 由於所有 word 長度相同，我們對每個可能的偏移量 i (0..wordLen-1) 使用滑動窗口。
2. 窗口每次移動 wordLen，統計窗口內每個單詞出現次數。
3. 若某單詞出現次數超過 words 的限制 → 左邊界右移，保持合法。
4. 當窗口內單詞數量等於 totalWords → 找到一個有效起點。
5. 遇到非 words 的單詞 → 重置窗口。
時間複雜度：O(wordLen * n/wordLen) = O(n)，比原本暴力遍歷每個起點快得多。
空間複雜度：O(M)，M = words.length
*/

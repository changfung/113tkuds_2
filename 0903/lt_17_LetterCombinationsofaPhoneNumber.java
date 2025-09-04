import java.util.*;

class Solution {
    private static final Map<Character, String> phoneMap = new HashMap<>();
    static {
        phoneMap.put('2', "abc");
        phoneMap.put('3', "def");
        phoneMap.put('4', "ghi");
        phoneMap.put('5', "jkl");
        phoneMap.put('6', "mno");
        phoneMap.put('7', "pqrs");
        phoneMap.put('8', "tuv");
        phoneMap.put('9', "wxyz");
    }

    public List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();
        if (digits == null || digits.length() == 0) return result;
        backtrack(result, new StringBuilder(), digits, 0);
        return result;
    }

    private void backtrack(List<String> result, StringBuilder current, String digits, int index) {
        // 當前組合長度 == 輸入字串長度，完成一個組合
        if (index == digits.length()) {
            result.add(current.toString());
            return;
        }

        // 取得當前數字對應的字母
        char digit = digits.charAt(index);
        String letters = phoneMap.get(digit);

        // 遍歷所有可能的字母，繼續回溯
        for (char c : letters.toCharArray()) {
            current.append(c);
            backtrack(result, current, digits, index + 1);
            current.deleteCharAt(current.length() - 1); // 回溯，移除最後一個字母
        }
    }
}

/*
解題思路：
1. 使用 HashMap 對應數字與字母。
2. 利用回溯 (Backtracking)，逐位展開所有可能字母組合。
3. 每當 index == digits.length()，代表完成一個組合，加入結果。
4. 時間複雜度 O(3^n * 4^m)，n 為輸入中有 3 個字母的數字數量，m 為有 4 個字母的數字數量。
5. 空間複雜度 O(n)，遞迴深度最多為 digits 長度。
*/


import java.util.*;

class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        backtrack(result, "", 0, 0, n);
        return result;
    }

    private void backtrack(List<String> result, String curr, int open, int close, int max) {
        if (curr.length() == max * 2) {
            result.add(curr);
            return;
        }
        if (open < max) backtrack(result, curr + "(", open + 1, close, max);
        if (close < open) backtrack(result, curr + ")", open, close + 1, max);
    }
}

/*
解題思路：
1. 回溯法生成所有合法括號組合。
2. open < n → 可以加 '('
3. close < open → 可以加 ')'
時間：O(4^n / sqrt(n))，空間：O(n)
*/

import java.util.*;

class Solution {
    public int longestValidParentheses(String s) {
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);
        int maxLen = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else {
                stack.pop();
                if (stack.isEmpty()) stack.push(i);
                else maxLen = Math.max(maxLen, i - stack.peek());
            }
        }
        return maxLen;
    }
}

/*
解題思路：
1. 使用 stack 保存最後一個未匹配的 "(" 的位置。
2. 遇到 ")" 就彈出，如果空了就記錄當前位置，否則用 i - stack.peek() 更新最長長度。
3. stack 中始終保存著可能成為合法括號子串的起點索引。
*/


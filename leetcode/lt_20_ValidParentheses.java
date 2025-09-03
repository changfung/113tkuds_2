import java.util.*;

class Solution {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (c == '(') stack.push(')');
            else if (c == '{') stack.push('}');
            else if (c == '[') stack.push(']');
            else if (stack.isEmpty() || stack.pop() != c) return false;
        }
        return stack.isEmpty();
    }
}

/*
解題思路：
1. 使用 Stack 存開啟括號對應的閉合括號。
2. 遍歷字串：
   - 開括號 → 推入對應閉括號
   - 關括號 → 檢查是否與 Stack 頂端匹配
3. 最後 Stack 空表示所有括號匹配。
時間：O(n)，空間：O(n)
*/

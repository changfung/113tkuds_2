import java.util.*;

public class LC20_ValidParentheses_AlertFormat {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 讀取輸入字串
        String s = sc.nextLine();

        // 呼叫檢查函數
        boolean result = isValid(s);

        // 輸出結果
        System.out.println(result);

        sc.close();
    }

    /**
     * 判斷括號字串是否有效
     */
    public static boolean isValid(String s) {
        // 建立閉括號 → 開括號對應表
        Map<Character, Character> pairs = new HashMap<>();
        pairs.put(')', '(');
        pairs.put(']', '[');
        pairs.put('}', '{');

        Stack<Character> stack = new Stack<>();

        for (char c : s.toCharArray()) {
            // 遇到開括號 → push
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            } 
            // 遇到閉括號 → 檢查棧頂
            else if (pairs.containsKey(c)) {
                if (stack.isEmpty() || stack.peek() != pairs.get(c)) {
                    return false; // 提早返回錯誤
                }
                stack.pop();
            }
        }

        // 最後棧必須為空才合法
        return stack.isEmpty();
    }
}

/*
解題思路：

1. 問題要求：
   - 給一段字串 s，只含有 "()", "[]", "{}" 三種括號。
   - 驗證是否「完全正確巢狀」：
     - 每個開括號都有對應閉括號。
     - 順序正確，不允許交錯。
     - 空字串也算合法。

2. 方法：
   - 使用 Stack 模擬括號匹配。
   - 遍歷字串：
     - 若遇到開括號 ( [ { → push 進棧。
     - 若遇到閉括號 ) ] } → 檢查棧頂：
       - 若棧為空，或棧頂不是對應開括號 → 回傳 false。
       - 否則 pop 出棧頂，繼續檢查。
   - 遍歷結束後，棧必須為空 → true；否則 false。

3. 範例：
   輸入："{[()()]}"
   過程：依序 push '{', '[', '(' → 遇到 ')' 檢查匹配 '(' → pop
          最後棧清空 → true。

   輸入："([)]"
   過程：push '(' → push '[' → 遇到 ')' 但棧頂是 '[' → false。

4. 複雜度：
   - 時間：O(n)，每個字元最多進棧/出棧一次。
   - 空間：O(n)，最壞情況全是開括號。

5. 邊界情況：
   - 空字串 "" → true。
   - 單一字元 "(" → false。
   - 以閉括號開頭 ")" → false（提早返回）。
   - 巢狀混合 "{()()}" → true。
*/

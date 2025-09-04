import java.util.*;

public class LC32_LongestValidParen_Metro {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 讀取輸入字串
        String s = sc.nextLine();

        // 呼叫解法
        int result = longestValidParentheses(s);

        // 輸出結果
        System.out.println(result);

        sc.close();
    }

    /**
     * 找出字串中最長合法括號片段長度
     */
    public static int longestValidParentheses(String s) {
        Stack<Integer> stack = new Stack<>();
        stack.push(-1); // 初始化基準索引
        int maxLen = 0;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c == '(') {
                // 遇到 '(' → push 索引
                stack.push(i);
            } else { // c == ')'
                // 遇到 ')' → pop
                stack.pop();
                if (stack.isEmpty()) {
                    // 空棧 → 置新基準索引
                    stack.push(i);
                } else {
                    // 計算合法長度
                    maxLen = Math.max(maxLen, i - stack.peek());
                }
            }
        }

        return maxLen;
    }
}

/*
解題思路：

1. 問題要求：
   - 給一個只含 '(' 或 ')' 的字串 s。
   - 找出最長子字串，使得該段括號完全配對、合法。
   - 空字串 → 長度 0。

2. 方法：索引堆疊法
   - 使用 Stack<Integer> 存放「括號索引」。
   - 初始化：push(-1) 作為基準索引。
   - 遍歷字串：
     - 遇到 '(' → push 索引。
     - 遇到 ')' → pop
       - 若棧空 → push 當前索引作為新基準。
       - 否則 → 計算長度 = i - stack.peek()，更新 maxLen。

3. 範例：
   輸入：")()(()"
   - i=0: ')' → pop(-1) → 空 → push 0 → stack=[0]
   - i=1: '(' → push 1 → stack=[0,1]
   - i=2: ')' → pop → stack=[0] → 長度=2-0=2 → maxLen=2
   - i=3: '(' → push 3 → stack=[0,3]
   - i=4: '(' → push 4 → stack=[0,3,4]
   - i=5: ')' → pop → stack=[0,3] → 長度=5-3=2 → maxLen=2
   → 最長合法片段長度 = 4

4. 複雜度：
   - 時間：O(n)，每個索引最多進出棧一次。
   - 空間：O(n)，最壞情況全為 '('。

5. 邊界情況：
   - 空字串 → 0。
   - 全為 '(' 或 ')' → 0。
   - 完全配對 "()()" → 4。
   - 孤立 ')' → 重置基準索引。
*/

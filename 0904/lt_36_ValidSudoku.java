import java.util.*;

class Solution {
    public boolean isValidSudoku(char[][] board) {
        HashSet<String> seen = new HashSet<>();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                char c = board[i][j];
                if (c == '.') continue;
                if (!seen.add(c + " in row " + i) ||
                    !seen.add(c + " in col " + j) ||
                    !seen.add(c + " in block " + i / 3 + "-" + j / 3))
                    return false;
            }
        }
        return true;
    }
}

/*
解題思路：
1. 用 HashSet 保存每個數字出現的位置：
   - 在 row
   - 在 col
   - 在 3x3 區塊
2. 如果有任何重複，立即返回 false。
3. 遍歷結束後返回 true。
*/

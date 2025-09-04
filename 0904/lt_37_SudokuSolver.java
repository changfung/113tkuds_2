class Solution {
    public void solveSudoku(char[][] board) {
        backtrack(board);
    }

    private boolean backtrack(char[][] board) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == '.') {
                    for (char c = '1'; c <= '9'; c++) {
                        if (isValid(board, i, j, c)) {
                            board[i][j] = c;
                            if (backtrack(board)) return true;
                            board[i][j] = '.';
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isValid(char[][] board, int row, int col, char c) {
        for (int i = 0; i < 9; i++) {
            if (board[row][i] == c || board[i][col] == c) return false;
            if (board[3 * (row/3) + i/3][3 * (col/3) + i%3] == c) return false;
        }
        return true;
    }
}

/*
解題思路：
1. 使用回溯 (backtracking)。
2. 找到一個空格，嘗試填入 1-9。
3. 驗證是否合法，若合法就遞歸繼續。
4. 若之後出現矛盾就回溯，恢復 '.'。
5. 當所有空格都填滿時，即為解。
*/

import java.util.Scanner;

public class TicTacToeBoard {
    private static final char EMPTY = '.';
    private static final char PLAYER_X = 'X';
    private static final char PLAYER_O = 'O';
    private static final int SIZE = 3;

    private char[][] board;
    private char currentPlayer;

    public TicTacToeBoard() {
        board = new char[SIZE][SIZE];
        currentPlayer = PLAYER_X;
        initializeBoard();
    }

    // 初始化棋盤
    private void initializeBoard() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                board[i][j] = EMPTY;
            }
        }
    }

    // 顯示棋盤
    public void printBoard() {
        System.out.println("當前棋盤：");
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                System.out.print(" " + board[i][j]);
            }
            System.out.println();
        }
    }

    // 放置棋子
    public boolean placeMark(int row, int col) {
        if (row < 0 || row >= SIZE || col < 0 || col >= SIZE) {
            System.out.println("座標無效，請重新輸入！");
            return false;
        }

        if (board[row][col] != EMPTY) {
            System.out.println("該位置已被佔用！");
            return false;
        }

        board[row][col] = currentPlayer;
        return true;
    }

    // 檢查勝利條件
    public boolean checkWin() {
        // 檢查行與列
        for (int i = 0; i < SIZE; i++) {
            if ((board[i][0] == currentPlayer &&
                 board[i][1] == currentPlayer &&
                 board[i][2] == currentPlayer) ||
                (board[0][i] == currentPlayer &&
                 board[1][i] == currentPlayer &&
                 board[2][i] == currentPlayer)) {
                return true;
            }
        }

        // 檢查對角線
        if ((board[0][0] == currentPlayer &&
             board[1][1] == currentPlayer &&
             board[2][2] == currentPlayer) ||
            (board[0][2] == currentPlayer &&
             board[1][1] == currentPlayer &&
             board[2][0] == currentPlayer)) {
            return true;
        }

        return false;
    }

    // 檢查是否平手（無空格）
    public boolean isBoardFull() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (board[i][j] == EMPTY) return false;
            }
        }
        return true;
    }

    // 換玩家
    public void switchPlayer() {
        currentPlayer = (currentPlayer == PLAYER_X) ? PLAYER_O : PLAYER_X;
    }

    public char getCurrentPlayer() {
        return currentPlayer;
    }

    // 主流程：簡單的 CLI 對戰
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TicTacToeBoard game = new TicTacToeBoard();

        System.out.println("井字遊戲開始！玩家 X 先手。");

        while (true) {
            game.printBoard();
            System.out.println("玩家 " + game.getCurrentPlayer() + " 請輸入行與列（0~2）：");
            int row = scanner.nextInt();
            int col = scanner.nextInt();

            if (!game.placeMark(row, col)) {
                continue; // 無效位置，重試
            }

            if (game.checkWin()) {
                game.printBoard();
                System.out.println("玩家 " + game.getCurrentPlayer() + " 獲勝！");
                break;
            }

            if (game.isBoardFull()) {
                game.printBoard();
                System.out.println("平手！");
                break;
            }

            game.switchPlayer();
        }

        scanner.close();
    }
}


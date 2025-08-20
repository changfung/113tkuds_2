import java.util.*;

public class M10_RBPropertiesCheck {
    static class Node {
        int val;
        char color; // 'B' or 'R'
        Node(int v, char c) { val = v; color = c; }
    }

    static Node[] tree; // 用陣列存樹（層序）
    static int n;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        tree = new Node[n];
        for (int i = 0; i < n; i++) {
            int val = sc.nextInt();
            char c = sc.next().charAt(0);
            if (val == -1) {
                tree[i] = new Node(-1, 'B'); // 空節點當黑
            } else {
                tree[i] = new Node(val, c);
            }
        }

        // 1) 根節點必須為黑
        if (tree.length > 0 && tree[0].val != -1 && tree[0].color != 'B') {
            System.out.println("RootNotBlack");
            return;
        }

        // 2+3) DFS 檢查
        String result = check(0, 'B'); // 父色假設為黑
        if (result != null) {
            System.out.println(result);
        } else {
            System.out.println("RB Valid");
        }
    }

    /**
     * DFS 檢查紅黑樹性質
     * 回傳：若正常 → 黑高度 (int) 的字串形式；若錯誤 → 錯誤訊息
     */
    static String check(int idx, char parentColor) {
        if (idx >= n || tree[idx].val == -1) {
            return "0"; // NIL 節點黑高度 = 0
        }

        Node cur = tree[idx];

        // 性質 2: 不能紅紅相鄰
        if (cur.color == 'R' && parentColor == 'R') {
            return "RedRedViolation at index " + idx;
        }

        // 遞迴檢查左右
        String left = check(2 * idx + 1, cur.color);
        if (!isNumeric(left)) return left; // 錯誤訊息傳上來
        String right = check(2 * idx + 2, cur.color);
        if (!isNumeric(right)) return right;

        int leftBH = Integer.parseInt(left);
        int rightBH = Integer.parseInt(right);

        if (leftBH != rightBH) {
            return "BlackHeightMismatch";
        }

        // 計算黑高度
        int bh = leftBH + (cur.color == 'B' ? 1 : 0);
        return String.valueOf(bh);
    }

    static boolean isNumeric(String s) {
        if (s == null) return false;
        for (char c : s.toCharArray()) {
            if (!Character.isDigit(c) && c != '-') return false;
        }
        return true;
    }
}

/*
 * Time Complexity: O(n)
 * 說明：
 * 1. 每個節點最多訪問一次 → O(n)。
 * 2. 遞迴檢查同時計算黑高度，無需額外遍歷。
 * 3. 空間複雜度 O(h)，h = 樹高 ≤ n。
 */


import java.util.*;

public class M09_AVLValidate {
    static class Node {
        int val;
        Node left, right;
        Node(int v) { this.val = v; }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = sc.nextInt();

        Node root = buildTree(arr);

        if (!isBST(root, Long.MIN_VALUE, Long.MAX_VALUE)) {
            System.out.println("Invalid BST");
        } else if (!isAVL(root).valid) {
            System.out.println("Invalid AVL");
        } else {
            System.out.println("Valid");
        }
    }

    /** 由層序陣列建樹（-1 表 null） */
    static Node buildTree(int[] arr) {
        if (arr.length == 0 || arr[0] == -1) return null;

        Node root = new Node(arr[0]);
        Queue<Node> q = new LinkedList<>();
        q.offer(root);

        int i = 1;
        while (!q.isEmpty() && i < arr.length) {
            Node cur = q.poll();
            if (i < arr.length && arr[i] != -1) {
                cur.left = new Node(arr[i]);
                q.offer(cur.left);
            }
            i++;
            if (i < arr.length && arr[i] != -1) {
                cur.right = new Node(arr[i]);
                q.offer(cur.right);
            }
            i++;
        }
        return root;
    }

    /** 檢查是否為 BST（帶 min/max 上下界） */
    static boolean isBST(Node root, long min, long max) {
        if (root == null) return true;
        if (root.val <= min || root.val >= max) return false;
        return isBST(root.left, min, root.val) && isBST(root.right, root.val, max);
    }

    /** 用於檢查 AVL 的回傳結構 */
    static class AVLInfo {
        boolean valid;
        int height;
        AVLInfo(boolean v, int h) { valid = v; height = h; }
    }

    /** 後序檢查 AVL（回傳高度與是否合法） */
    static AVLInfo isAVL(Node root) {
        if (root == null) return new AVLInfo(true, 0);

        AVLInfo left = isAVL(root.left);
        AVLInfo right = isAVL(root.right);

        if (!left.valid || !right.valid) return new AVLInfo(false, 0);

        if (Math.abs(left.height - right.height) > 1) return new AVLInfo(false, 0);

        return new AVLInfo(true, Math.max(left.height, right.height) + 1);
    }
}

/*
 * Time Complexity: O(n)
 * 說明：
 * 1. 建樹：O(n)。
 * 2. 檢查 BST：每節點一次，O(n)。
 * 3. 檢查 AVL：每節點一次（後序計算高度），O(n)。
 * 4. 總時間複雜度：O(n)，空間複雜度 O(h)（遞迴深度）。
 */


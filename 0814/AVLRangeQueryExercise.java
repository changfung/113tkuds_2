import java.util.ArrayList;
import java.util.List;

public class AVLRangeQueryExercise {

    static class Node {
        int key;
        Node left, right;
        int height;

        Node(int key) {
            this.key = key;
            this.height = 1;
        }
    }

    Node root;

    // === 工具方法 ===
    int height(Node node) {
        return node == null ? 0 : node.height;
    }

    int getBalance(Node node) {
        return node == null ? 0 : height(node.left) - height(node.right);
    }

    void updateHeight(Node node) {
        if (node != null)
            node.height = 1 + Math.max(height(node.left), height(node.right));
    }

    Node rightRotate(Node y) {
        Node x = y.left;
        Node T2 = x.right;

        x.right = y;
        y.left = T2;

        updateHeight(y);
        updateHeight(x);

        return x;
    }

    Node leftRotate(Node x) {
        Node y = x.right;
        Node T2 = y.left;

        y.left = x;
        x.right = T2;

        updateHeight(x);
        updateHeight(y);

        return y;
    }

    // === 插入節點 ===
    Node insert(Node node, int key) {
        if (node == null)
            return new Node(key);

        if (key < node.key)
            node.left = insert(node.left, key);
        else if (key > node.key)
            node.right = insert(node.right, key);
        else
            return node;

        updateHeight(node);

        int balance = getBalance(node);

        // LL
        if (balance > 1 && key < node.left.key)
            return rightRotate(node);
        // RR
        if (balance < -1 && key > node.right.key)
            return leftRotate(node);
        // LR
        if (balance > 1 && key > node.left.key) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }
        // RL
        if (balance < -1 && key < node.right.key) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    public void insert(int key) {
        root = insert(root, key);
    }

    // === 範圍查詢 ===
    public List<Integer> rangeQuery(int min, int max) {
        List<Integer> result = new ArrayList<>();
        rangeQuery(root, min, max, result);
        return result;
    }

    private void rangeQuery(Node node, int min, int max, List<Integer> result) {
        if (node == null) return;

        // 左子樹有可能有符合條件的值
        if (min < node.key) {
            rangeQuery(node.left, min, max, result);
        }

        // 當前節點在範圍內
        if (min <= node.key && node.key <= max) {
            result.add(node.key);
        }

        // 右子樹可能有符合條件的值
        if (max > node.key) {
            rangeQuery(node.right, min, max, result);
        }
    }

    // === 印出中序走訪（測試用）===
    public void printInOrder() {
        printInOrder(root);
        System.out.println();
    }

    private void printInOrder(Node node) {
        if (node != null) {
            printInOrder(node.left);
            System.out.print(node.key + " ");
            printInOrder(node.right);
        }
    }

    // === 測試主程式 ===
    public static void main(String[] args) {
        AVLRangeQueryExercise tree = new AVLRangeQueryExercise();
        int[] keys = {20, 10, 30, 5, 15, 25, 35, 3, 8};

        for (int key : keys)
            tree.insert(key);

        System.out.println("中序走訪：");
        tree.printInOrder();

        System.out.println("\n範圍查詢 [10, 30]：");
        List<Integer> result1 = tree.rangeQuery(10, 30);
        System.out.println(result1); // [10, 15, 20, 25, 30]

        System.out.println("\n範圍查詢 [6, 14]：");
        List<Integer> result2 = tree.rangeQuery(6, 14);
        System.out.println(result2); // [8, 10]

        System.out.println("\n範圍查詢 [0, 100]：");
        List<Integer> result3 = tree.rangeQuery(0, 100);
        System.out.println(result3); // 全部
    }
}


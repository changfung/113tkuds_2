public class AVLDeleteExercise {

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

    // ===== 基本工具方法 =====
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

    // ===== 插入方法（用於建立測試樹）=====
    Node insert(Node node, int key) {
        if (node == null)
            return new Node(key);

        if (key < node.key)
            node.left = insert(node.left, key);
        else if (key > node.key)
            node.right = insert(node.right, key);
        else
            return node; // 不插入重複值

        updateHeight(node);
        return rebalance(node, key);
    }

    Node rebalance(Node node, int keyOrDeletedKey) {
        int balance = getBalance(node);

        // LL
        if (balance > 1 && getBalance(node.left) >= 0)
            return rightRotate(node);

        // LR
        if (balance > 1 && getBalance(node.left) < 0) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        // RR
        if (balance < -1 && getBalance(node.right) <= 0)
            return leftRotate(node);

        // RL
        if (balance < -1 && getBalance(node.right) > 0) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    public void insert(int key) {
        root = insert(root, key);
    }

    // ===== 刪除節點 =====
    Node delete(Node node, int key) {
        if (node == null)
            return null;

        if (key < node.key)
            node.left = delete(node.left, key);
        else if (key > node.key)
            node.right = delete(node.right, key);
        else {
            // 找到要刪除的節點

            // 1. 無子節點 or 單子節點
            if (node.left == null || node.right == null) {
                Node temp = (node.left != null) ? node.left : node.right;
                if (temp == null)
                    return null; // 直接刪除葉子節點
                else
                    return temp; // 單子節點取代自己
            }

            // 2. 兩個子節點 → 使用中序後繼替代
            Node successor = minValueNode(node.right);
            node.key = successor.key;
            node.right = delete(node.right, successor.key);
        }

        // 更新高度與重平衡
        updateHeight(node);
        return rebalance(node, key);
    }

    Node minValueNode(Node node) {
        Node current = node;
        while (current.left != null)
            current = current.left;
        return current;
    }

    public void delete(int key) {
        root = delete(root, key);
    }

    // ===== 中序走訪列印 =====
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

    // ===== 驗證 AVL 合法性 =====
    public boolean isValidAVL() {
        return isValidAVL(root);
    }

    private boolean isValidAVL(Node node) {
        if (node == null) return true;

        int balance = getBalance(node);
        if (balance > 1 || balance < -1)
            return false;

        return isValidAVL(node.left) && isValidAVL(node.right);
    }

    // ===== 測試主程式 =====
    public static void main(String[] args) {
        AVLDeleteExercise tree = new AVLDeleteExercise();

        int[] keys = {20, 10, 30, 5, 15, 25, 35};
        for (int key : keys)
            tree.insert(key);

        System.out.println("初始樹（中序）:");
        tree.printInOrder();

        System.out.println("\n刪除葉子節點 5:");
        tree.delete(5);
        tree.printInOrder();

        System.out.println("\n刪除單子節點 30:");
        tree.delete(30);
        tree.printInOrder();

        System.out.println("\n刪除有兩個子節點的節點 20:");
        tree.delete(20);
        tree.printInOrder();

        System.out.println("\nAVL 樹是否合法？" + tree.isValidAVL());
    }
}


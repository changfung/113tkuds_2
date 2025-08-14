public class AVLBasicExercise {

    // 節點類別
    static class Node {
        int key;
        Node left, right;
        int height;

        Node(int key) {
            this.key = key;
            this.height = 1; // 新節點的初始高度為 1
        }
    }

    Node root;

    // 取得節點高度
    int height(Node node) {
        return node == null ? 0 : node.height;
    }

    // 取得平衡因子
    int getBalance(Node node) {
        return node == null ? 0 : height(node.left) - height(node.right);
    }

    // 右旋
    Node rightRotate(Node y) {
        Node x = y.left;
        Node T2 = x.right;

        // 執行旋轉
        x.right = y;
        y.left = T2;

        // 更新高度
        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;

        // 回傳新的根節點
        return x;
    }

    // 左旋
    Node leftRotate(Node x) {
        Node y = x.right;
        Node T2 = y.left;

        // 執行旋轉
        y.left = x;
        x.right = T2;

        // 更新高度
        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;

        // 回傳新的根節點
        return y;
    }

    // 插入節點
    Node insert(Node node, int key) {
        // 標準 BST 插入
        if (node == null)
            return new Node(key);

        if (key < node.key)
            node.left = insert(node.left, key);
        else if (key > node.key)
            node.right = insert(node.right, key);
        else // 重複鍵值不插入
            return node;

        // 更新高度
        node.height = 1 + Math.max(height(node.left), height(node.right));

        // 計算平衡因子
        int balance = getBalance(node);

        // 執行對應的旋轉
        // LL Case
        if (balance > 1 && key < node.left.key)
            return rightRotate(node);

        // RR Case
        if (balance < -1 && key > node.right.key)
            return leftRotate(node);

        // LR Case
        if (balance > 1 && key > node.left.key) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        // RL Case
        if (balance < -1 && key < node.right.key) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node; // 不需要旋轉，回傳節點
    }

    // 包裝插入函數
    public void insert(int key) {
        root = insert(root, key);
    }

    // 搜尋節點
    public boolean search(int key) {
        return search(root, key);
    }

    private boolean search(Node node, int key) {
        if (node == null)
            return false;
        if (key == node.key)
            return true;
        if (key < node.key)
            return search(node.left, key);
        else
            return search(node.right, key);
    }

    // 計算整棵樹高度
    public int height() {
        return height(root);
    }

    // 驗證是否為合法的 AVL 樹
    public boolean isValidAVL() {
        return isValidAVL(root);
    }

    private boolean isValidAVL(Node node) {
        if (node == null)
            return true;

        int balance = getBalance(node);
        if (balance > 1 || balance < -1)
            return false;

        return isValidAVL(node.left) && isValidAVL(node.right);
    }

    // 主函數測試
    public static void main(String[] args) {
        AVLBasicExercise tree = new AVLBasicExercise();

        int[] keys = {10, 20, 30, 40, 50, 25};
        for (int key : keys)
            tree.insert(key);

        System.out.println("搜尋 30: " + tree.search(30)); // true
        System.out.println("搜尋 100: " + tree.search(100)); // false
        System.out.println("樹高: " + tree.height()); // 樹高取決於平衡狀態
        System.out.println("是合法 AVL 樹: " + tree.isValidAVL()); // true
    }
}



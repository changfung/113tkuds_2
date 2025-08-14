import java.util.*;

public class PersistentAVLExercise {

    static class Node {
        final int key;
        final Node left, right;
        final int height;

        Node(int key, Node left, Node right) {
            this.key = key;
            this.left = left;
            this.right = right;
            this.height = 1 + Math.max(height(left), height(right));
        }

        static int height(Node node) {
            return node == null ? 0 : node.height;
        }

        int balanceFactor() {
            return height(left) - height(right);
        }
    }

    // 所有版本的根節點
    List<Node> versions = new ArrayList<>();

    public PersistentAVLExercise() {
        versions.add(null); // 初始版本 0 為空
    }

    // 插入並創建新版本
    public void insert(int version, int key) {
        Node oldRoot = versions.get(version);
        Node newRoot = insertRecursive(oldRoot, key);
        versions.add(newRoot); // 新版本
    }

    // 不可變插入實作（回傳新根）
    private Node insertRecursive(Node node, int key) {
        if (node == null) return new Node(key, null, null);

        if (key < node.key) {
            Node newLeft = insertRecursive(node.left, key);
            return rebalance(new Node(node.key, newLeft, node.right));
        } else if (key > node.key) {
            Node newRight = insertRecursive(node.right, key);
            return rebalance(new Node(node.key, node.left, newRight));
        } else {
            return node; // 不插入重複
        }
    }

    // 旋轉與平衡
    private Node rebalance(Node node) {
        int balance = node.balanceFactor();

        if (balance > 1) {
            if (node.left != null && node.left.balanceFactor() >= 0) {
                return rotateRight(node); // LL
            } else {
                return rotateLeftRight(node); // LR
            }
        } else if (balance < -1) {
            if (node.right != null && node.right.balanceFactor() <= 0) {
                return rotateLeft(node); // RR
            } else {
                return rotateRightLeft(node); // RL
            }
        }

        return node; // 已平衡
    }

    private Node rotateRight(Node y) {
        Node x = y.left;
        Node T2 = x.right;
        return new Node(x.key, x.left, new Node(y.key, T2, y.right));
    }

    private Node rotateLeft(Node x) {
        Node y = x.right;
        Node T2 = y.left;
        return new Node(y.key, new Node(x.key, x.left, T2), y.right);
    }

    private Node rotateLeftRight(Node node) {
        Node newLeft = rotateLeft(node.left);
        return rotateRight(new Node(node.key, newLeft, node.right));
    }

    private Node rotateRightLeft(Node node) {
        Node newRight = rotateRight(node.right);
        return rotateLeft(new Node(node.key, node.left, newRight));
    }

    // 查詢特定版本是否含有某值
    public boolean contains(int version, int key) {
        Node root = versions.get(version);
        return containsRecursive(root, key);
    }

    private boolean containsRecursive(Node node, int key) {
        if (node == null) return false;
        if (key < node.key) return containsRecursive(node.left, key);
        if (key > node.key) return containsRecursive(node.right, key);
        return true;
    }

    // 列印某版本的中序走訪
    public void printInOrder(int version) {
        Node root = versions.get(version);
        inOrder(root);
        System.out.println();
    }

    private void inOrder(Node node) {
        if (node != null) {
            inOrder(node.left);
            System.out.print(node.key + " ");
            inOrder(node.right);
        }
    }

    public int versionCount() {
        return versions.size();
    }

    // ====== 測試主程式 ======
    public static void main(String[] args) {
        PersistentAVLExercise tree = new PersistentAVLExercise();

        tree.insert(0, 10);  // 版本 1
        tree.insert(1, 20);  // 版本 2
        tree.insert(2, 5);   // 版本 3
        tree.insert(3, 15);  // 版本 4

        System.out.println("版本總數：" + tree.versionCount()); // 5（包含版本 0）

        for (int v = 0; v < tree.versionCount(); v++) {
            System.out.print("版本 " + v + " 中序：");
            tree.printInOrder(v);
        }

        System.out.println("版本 2 是否包含 10？" + tree.contains(2, 10)); // true
        System.out.println("版本 2 是否包含 15？" + tree.contains(2, 15)); // false
    }
}


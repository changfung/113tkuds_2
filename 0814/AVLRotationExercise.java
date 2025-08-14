public class AVLRotationExercise {

    static class Node {
        int key;
        Node left, right;
        int height;

        Node(int key) {
            this.key = key;
            this.height = 1; // 新節點高度預設為 1
        }
    }

    // 取得節點高度
    static int height(Node node) {
        return node == null ? 0 : node.height;
    }

    // 更新節點高度
    static void updateHeight(Node node) {
        if (node != null)
            node.height = 1 + Math.max(height(node.left), height(node.right));
    }

    // 右旋轉 (Right Rotation)
    static Node rightRotate(Node y) {
        Node x = y.left;
        Node T2 = x.right;

        // 執行旋轉
        x.right = y;
        y.left = T2;

        // 更新高度
        updateHeight(y);
        updateHeight(x);

        return x; // 新根節點
    }

    // 左旋轉 (Left Rotation)
    static Node leftRotate(Node x) {
        Node y = x.right;
        Node T2 = y.left;

        // 執行旋轉
        y.left = x;
        x.right = T2;

        // 更新高度
        updateHeight(x);
        updateHeight(y);

        return y; // 新根節點
    }

    // 左右旋轉 (Left-Right Rotation)
    static Node leftRightRotate(Node node) {
        node.left = leftRotate(node.left);
        return rightRotate(node);
    }

    // 右左旋轉 (Right-Left Rotation)
    static Node rightLeftRotate(Node node) {
        node.right = rightRotate(node.right);
        return leftRotate(node);
    }

    // 印出中序走訪
    static void printInOrder(Node root) {
        if (root != null) {
            printInOrder(root.left);
            System.out.print(root.key + " ");
            printInOrder(root.right);
        }
    }

    // 印出樹高度與根節點
    static void printTreeInfo(String label, Node root) {
        System.out.println("[" + label + "]");
        System.out.println("中序走訪：");
        printInOrder(root);
        System.out.println("\n根節點：" + root.key);
        System.out.println("高度：" + height(root));
        System.out.println("----------");
    }

    public static void main(String[] args) {
        // RR Case: 插入順序 10, 20, 30 -> 需左旋
        Node rr = new Node(10);
        rr.right = new Node(20);
        rr.right.right = new Node(30);
        updateHeight(rr.right.right);
        updateHeight(rr.right);
        updateHeight(rr);
        rr = leftRotate(rr);
        printTreeInfo("RR Case (Left Rotation)", rr);

        // LL Case: 插入順序 30, 20, 10 -> 需右旋
        Node ll = new Node(30);
        ll.left = new Node(20);
        ll.left.left = new Node(10);
        updateHeight(ll.left.left);
        updateHeight(ll.left);
        updateHeight(ll);
        ll = rightRotate(ll);
        printTreeInfo("LL Case (Right Rotation)", ll);

        // LR Case: 插入順序 30, 10, 20 -> 需左右旋
        Node lr = new Node(30);
        lr.left = new Node(10);
        lr.left.right = new Node(20);
        updateHeight(lr.left.right);
        updateHeight(lr.left);
        updateHeight(lr);
        lr = leftRightRotate(lr);
        printTreeInfo("LR Case (Left-Right Rotation)", lr);

        // RL Case: 插入順序 10, 30, 20 -> 需右左旋
        Node rl = new Node(10);
        rl.right = new Node(30);
        rl.right.left = new Node(20);
        updateHeight(rl.right.left);
        updateHeight(rl.right);
        updateHeight(rl);
        rl = rightLeftRotate(rl);
        printTreeInfo("RL Case (Right-Left Rotation)", rl);
    }
}


class AVLNode {
    int data;
    AVLNode left, right;
    int height;

    public AVLNode(int data) {
        this.data = data;
        this.height = 1;
    }

    public int getBalance() {
        int leftHeight = (left != null) ? left.height : 0;
        int rightHeight = (right != null) ? right.height : 0;
        return leftHeight - rightHeight;
    }

    public void updateHeight() {
        int leftHeight = (left != null) ? left.height : 0;
        int rightHeight = (right != null) ? right.height : 0;
        this.height = Math.max(leftHeight, rightHeight) + 1;
    }
}

public class AVLRotations {

    // 右旋操作
    public static AVLNode rightRotate(AVLNode y) {
        AVLNode x = y.left;
        AVLNode T2 = x.right;

        x.right = y;
        y.left = T2;

        y.updateHeight();
        x.updateHeight();

        return x;
    }

    // 左旋操作
    public static AVLNode leftRotate(AVLNode x) {
        AVLNode y = x.right;
        AVLNode T2 = y.left;

        y.left = x;
        x.right = T2;

        x.updateHeight();
        y.updateHeight();

        return y;
    }

    // 測試用 main 方法
    public static void main(String[] args) {
        // 模擬 Left-Left 不平衡情況（需右旋）
        AVLNode root = new AVLNode(30);
        root.left = new AVLNode(20);
        root.left.left = new AVLNode(10);

        // 更新高度
        root.left.left.updateHeight();
        root.left.updateHeight();
        root.updateHeight();

        System.out.println("旋轉前:");
        System.out.println("根節點: " + root.data);
        System.out.println("平衡因子: " + root.getBalance());

        // 執行右旋
        root = rightRotate(root);

        System.out.println("\n右旋後:");
        System.out.println("新根節點: " + root.data);
        System.out.println("左子節點: " + (root.left != null ? root.left.data : "null"));
        System.out.println("右子節點: " + (root.right != null ? root.right.data : "null"));
        System.out.println("平衡因子: " + root.getBalance());
    }
}


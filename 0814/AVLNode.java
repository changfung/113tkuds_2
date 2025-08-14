public class AVLNode {
    int data;
    AVLNode left, right;
    int height;

    // 建構子
    public AVLNode(int data) {
        this.data = data;
        this.height = 1; // 單一節點高度為 1
    }

    // 計算平衡因子 = 左子樹高度 - 右子樹高度
    public int getBalance() {
        int leftHeight = (left != null) ? left.height : 0;
        int rightHeight = (right != null) ? right.height : 0;
        return leftHeight - rightHeight;
    }

    // 更新當前節點的高度
    public void updateHeight() {
        int leftHeight = (left != null) ? left.height : 0;
        int rightHeight = (right != null) ? right.height : 0;
        this.height = Math.max(leftHeight, rightHeight) + 1;
    }

    // 設定左節點並更新高度
    public void setLeft(AVLNode node) {
        this.left = node;
        updateHeight();
    }

    // 設定右節點並更新高度
    public void setRight(AVLNode node) {
        this.right = node;
        updateHeight();
    }

    // 主程式：示範建立一個簡單的 AVL 節點樹
    public static void main(String[] args) {
        AVLNode root = new AVLNode(10);
        AVLNode leftChild = new AVLNode(5);
        AVLNode rightChild = new AVLNode(15);

        // 設定左右子節點
        root.setLeft(leftChild);
        root.setRight(rightChild);

        // 印出資訊
        System.out.println("根節點資料: " + root.data);
        System.out.println("高度: " + root.height);
        System.out.println("平衡因子: " + root.getBalance());

        System.out.println("左子節點資料: " + root.left.data);
        System.out.println("右子節點資料: " + root.right.data);
    }
}



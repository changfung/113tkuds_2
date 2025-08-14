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

class AVLRotations {
    public static AVLNode rightRotate(AVLNode y) {
        AVLNode x = y.left;
        AVLNode T2 = x.right;

        x.right = y;
        y.left = T2;

        y.updateHeight();
        x.updateHeight();

        return x;
    }

    public static AVLNode leftRotate(AVLNode x) {
        AVLNode y = x.right;
        AVLNode T2 = y.left;

        y.left = x;
        x.right = T2;

        x.updateHeight();
        y.updateHeight();

        return y;
    }
}

public class AVLTree {
    private AVLNode root;

    private int getHeight(AVLNode node) {
        return (node != null) ? node.height : 0;
    }

    public void insert(int data) {
        root = insertNode(root, data);
    }

    private AVLNode insertNode(AVLNode node, int data) {
        if (node == null) return new AVLNode(data);

        if (data < node.data) {
            node.left = insertNode(node.left, data);
        } else if (data > node.data) {
            node.right = insertNode(node.right, data);
        } else {
            return node; // 重複值不插入
        }

        node.updateHeight();
        int balance = node.getBalance();

        // Left Left
        if (balance > 1 && data < node.left.data) {
            return AVLRotations.rightRotate(node);
        }

        // Right Right
        if (balance < -1 && data > node.right.data) {
            return AVLRotations.leftRotate(node);
        }

        // Left Right
        if (balance > 1 && data > node.left.data) {
            node.left = AVLRotations.leftRotate(node.left);
            return AVLRotations.rightRotate(node);
        }

        // Right Left
        if (balance < -1 && data < node.right.data) {
            node.right = AVLRotations.rightRotate(node.right);
            return AVLRotations.leftRotate(node);
        }

        return node;
    }

    public boolean search(int data) {
        return searchNode(root, data);
    }

    private boolean searchNode(AVLNode node, int data) {
        if (node == null) return false;
        if (data == node.data) return true;
        if (data < node.data) return searchNode(node.left, data);
        return searchNode(node.right, data);
    }

    private AVLNode findMin(AVLNode node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    public void delete(int data) {
        root = deleteNode(root, data);
    }

    private AVLNode deleteNode(AVLNode node, int data) {
        if (node == null) return null;

        if (data < node.data) {
            node.left = deleteNode(node.left, data);
        } else if (data > node.data) {
            node.right = deleteNode(node.right, data);
        } else {
            // 刪除邏輯
            if (node.left == null || node.right == null) {
                AVLNode temp = (node.left != null) ? node.left : node.right;
                if (temp == null) {
                    node = null;
                } else {
                    node = temp;
                }
            } else {
                AVLNode temp = findMin(node.right);
                node.data = temp.data;
                node.right = deleteNode(node.right, temp.data);
            }
        }

        if (node == null) return null;

        node.updateHeight();
        int balance = node.getBalance();

        // Left Left
        if (balance > 1 && node.left.getBalance() >= 0) {
            return AVLRotations.rightRotate(node);
        }

        // Left Right
        if (balance > 1 && node.left.getBalance() < 0) {
            node.left = AVLRotations.leftRotate(node.left);
            return AVLRotations.rightRotate(node);
        }

        // Right Right
        if (balance < -1 && node.right.getBalance() <= 0) {
            return AVLRotations.leftRotate(node);
        }

        // Right Left
        if (balance < -1 && node.right.getBalance() > 0) {
            node.right = AVLRotations.rightRotate(node.right);
            return AVLRotations.leftRotate(node);
        }

        return node;
    }

    public boolean isValidAVL() {
        return checkAVL(root) != -1;
    }

    private int checkAVL(AVLNode node) {
        if (node == null) return 0;

        int leftHeight = checkAVL(node.left);
        int rightHeight = checkAVL(node.right);

        if (leftHeight == -1 || rightHeight == -1) return -1;
        if (Math.abs(leftHeight - rightHeight) > 1) return -1;

        return Math.max(leftHeight, rightHeight) + 1;
    }

    public void printTree() {
        printInOrder(root);
        System.out.println();
    }

    private void printInOrder(AVLNode node) {
        if (node != null) {
            printInOrder(node.left);
            System.out.print(node.data + "(" + node.getBalance() + ") ");
            printInOrder(node.right);
        }
    }
    public static void main(String[] args) {
        AVLTree tree = new AVLTree();

        // 插入資料
        int[] values = {30, 20, 40, 10, 25, 35, 50, 5, 15};
        for (int val : values) {
            tree.insert(val);
        }

        System.out.println("插入後 AVL 樹（中序列印 + 平衡因子）:");
        tree.printTree();

        // 測試搜尋
        System.out.println("搜尋 25 是否存在: " + tree.search(25));
        System.out.println("搜尋 100 是否存在: " + tree.search(100));

        // 刪除節點
        tree.delete(20);
        System.out.println("刪除 20 後的 AVL 樹:");
        tree.printTree();

        // 驗證是否為合法 AVL
        System.out.println("是否為合法 AVL 樹: " + tree.isValidAVL());
    }
}



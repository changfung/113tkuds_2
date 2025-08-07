
public class BSTConversionAndBalance {

    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int v) { val = v; }
    }

    // Doubly linked list node (使用TreeNode本身作雙向節點，left=prev, right=next)
    static class DoublyListNode {
        int val;
        DoublyListNode prev, next;
        DoublyListNode(int v) { val = v; }
    }

    public static void main(String[] args) {
        /*
          範例BST:
                5
               / \
              3   8
             / \   \
            2   4   10
        */
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3);
        root.right = new TreeNode(8);
        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(4);
        root.right.right = new TreeNode(10);

        // 1. BST轉雙向鏈結串列
        TreeNode head = bstToDoublyLinkedList(root);
        System.out.print("BST轉雙向鏈結串列(從最小開始): ");
        printDoublyLinkedList(head);

        // 2. 排序陣列轉平衡BST
        int[] sortedArr = {1,2,3,4,5,6,7};
        TreeNode balancedRoot = sortedArrayToBST(sortedArr);
        System.out.print("\n排序陣列轉平衡BST前序: ");
        printPreorder(balancedRoot);

        // 3. 檢查BST是否平衡並計算平衡因子
        boolean balanced = isBalanced(root);
        System.out.println("\nBST是否平衡: " + balanced);
        System.out.println("各節點平衡因子 (節點值=高度差):");
        printBalanceFactors(root);

        // 4. 將BST改為Greater Tree (每個節點加上所有大於等於該節點的節點值)
        convertBSTtoGreaterTree(root);
        System.out.print("Greater Tree 前序: ");
        printPreorder(root);
    }

    // 1. BST轉雙向鏈結串列(中序遍歷連接)
    static TreeNode prev = null;
    static TreeNode head = null;
    public static TreeNode bstToDoublyLinkedList(TreeNode root) {
        prev = null;
        head = null;
        inorderDoublyList(root);
        return head;
    }

    private static void inorderDoublyList(TreeNode node) {
        if (node == null) return;

        inorderDoublyList(node.left);

        if (prev == null) {
            head = node;
        } else {
            prev.right = node;
            node.left = prev;
        }
        prev = node;

        inorderDoublyList(node.right);
    }

    private static void printDoublyLinkedList(TreeNode head) {
        TreeNode curr = head;
        while (curr != null) {
            System.out.print(curr.val);
            if (curr.right != null) System.out.print(" <-> ");
            curr = curr.right;
        }
    }

    // 2. 排序陣列轉平衡BST
    public static TreeNode sortedArrayToBST(int[] nums) {
        return sortedArrayToBSTHelper(nums, 0, nums.length -1);
    }

    private static TreeNode sortedArrayToBSTHelper(int[] nums, int left, int right) {
        if (left > right) return null;
        int mid = left + (right - left)/2;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = sortedArrayToBSTHelper(nums, left, mid -1);
        root.right = sortedArrayToBSTHelper(nums, mid +1, right);
        return root;
    }

    private static void printPreorder(TreeNode root) {
        if (root == null) return;
        System.out.print(root.val + " ");
        printPreorder(root.left);
        printPreorder(root.right);
    }

    // 3. 判斷是否為平衡BST，並列印各節點平衡因子 (左子樹高-右子樹高)
    public static boolean isBalanced(TreeNode root) {
        return checkHeight(root) != -1;
    }

    private static int checkHeight(TreeNode node) {
        if (node == null) return 0;
        int leftH = checkHeight(node.left);
        if (leftH == -1) return -1;
        int rightH = checkHeight(node.right);
        if (rightH == -1) return -1;

        if (Math.abs(leftH - rightH) > 1) return -1;
        return Math.max(leftH, rightH) + 1;
    }

    public static void printBalanceFactors(TreeNode node) {
        if (node == null) return;
        int leftH = height(node.left);
        int rightH = height(node.right);
        int balanceFactor = leftH - rightH;
        System.out.println("節點 " + node.val + " 平衡因子: " + balanceFactor);
        printBalanceFactors(node.left);
        printBalanceFactors(node.right);
    }

    private static int height(TreeNode node) {
        if (node == null) return 0;
        return 1 + Math.max(height(node.left), height(node.right));
    }

    // 4. 將BST轉換為Greater Tree
    static int sumSoFar = 0;
    public static void convertBSTtoGreaterTree(TreeNode root) {
        sumSoFar = 0;
        reverseInorder(root);
    }

    private static void reverseInorder(TreeNode node) {
        if (node == null) return;
        reverseInorder(node.right);
        sumSoFar += node.val;
        node.val = sumSoFar;
        reverseInorder(node.left);
    }
}


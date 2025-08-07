import java.util.*;

public class BinaryTreeBasicOperations {

    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) {
            this.val = val;
        }
    }

    public static void main(String[] args) {
        /*
               10
              /  \
             5    15
            / \     \
           3   7     20
        */
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(5);
        root.right = new TreeNode(15);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(7);
        root.right.right = new TreeNode(20);

        // 1. 計算總和與平均
        int sum = sumTree(root);
        int count = countNodes(root);
        double avg = count == 0 ? 0 : (double) sum / count;
        System.out.println("節點總和: " + sum);
        System.out.println("節點平均值: " + avg);

        // 2. 最大值與最小值
        int maxVal = findMax(root);
        int minVal = findMin(root);
        System.out.println("最大值節點: " + maxVal);
        System.out.println("最小值節點: " + minVal);

        // 3. 樹的寬度
        int width = treeWidth(root);
        System.out.println("樹的最大寬度: " + width);

        // 4. 判斷是否為完全二元樹
        boolean isComplete = isCompleteBinaryTree(root);
        System.out.println("是否為完全二元樹: " + (isComplete ? "是" : "否"));
    }

    // 計算節點總和（遞迴）
    public static int sumTree(TreeNode node) {
        if (node == null) return 0;
        return node.val + sumTree(node.left) + sumTree(node.right);
    }

    // 計算節點數量
    public static int countNodes(TreeNode node) {
        if (node == null) return 0;
        return 1 + countNodes(node.left) + countNodes(node.right);
    }

    // 找最大值（遞迴）
    public static int findMax(TreeNode node) {
        if (node == null) return Integer.MIN_VALUE;
        int leftMax = findMax(node.left);
        int rightMax = findMax(node.right);
        return Math.max(node.val, Math.max(leftMax, rightMax));
    }

    // 找最小值（遞迴）
    public static int findMin(TreeNode node) {
        if (node == null) return Integer.MAX_VALUE;
        int leftMin = findMin(node.left);
        int rightMin = findMin(node.right);
        return Math.min(node.val, Math.min(leftMin, rightMin));
    }

    // 計算樹的最大寬度（利用 BFS 層序遍歷）
    public static int treeWidth(TreeNode root) {
        if (root == null) return 0;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int maxWidth = 0;

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            maxWidth = Math.max(maxWidth, levelSize);

            for (int i = 0; i < levelSize; i++) {
                TreeNode node = queue.poll();
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
        }
        return maxWidth;
    }

    // 判斷是否為完全二元樹（利用 BFS）
    public static boolean isCompleteBinaryTree(TreeNode root) {
        if (root == null) return true;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        boolean mustBeLeaf = false;

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();

            if (node.left != null) {
                if (mustBeLeaf) return false;
                queue.offer(node.left);
            } else {
                // 左子節點為空後，接下來所有節點必須是葉節點
                mustBeLeaf = true;
            }

            if (node.right != null) {
                if (mustBeLeaf) return false;
                queue.offer(node.right);
            } else {
                // 右子節點為空後，接下來所有節點必須是葉節點
                mustBeLeaf = true;
            }
        }
        return true;
    }
}


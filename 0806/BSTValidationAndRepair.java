import java.util.*;

public class BSTValidationAndRepair {

    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int v) { val = v; }
    }

    public static void main(String[] args) {
        /*
          測試樹 (故意破壞BST)
               10
              /  \
             5    8    <--- 節點8錯誤，應該比10大
            / \   
           2   20  <--- 節點20錯誤，應該比5小

         修復後:
               10
              /  \
             5    20
            / \   
           2   8  
        */
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(5);
        root.right = new TreeNode(8);
        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(20);

        System.out.println("是否為有效BST: " + isValidBST(root));

        List<TreeNode> invalidNodes = findInvalidNodes(root);
        System.out.print("不符合BST的節點值: ");
        for (TreeNode n : invalidNodes) System.out.print(n.val + " ");
        System.out.println();

        repairBST(root);
        System.out.println("修復後是否為有效BST: " + isValidBST(root));

        int removeCount = minRemovalsToMakeBST(root);
        System.out.println("最少需移除節點數使樹成為BST: " + removeCount);
    }

    // 1. 驗證是否為有效 BST（利用中序遍歷遞增特性）
    public static boolean isValidBST(TreeNode root) {
        return isValidBSTHelper(root, null, null);
    }

    private static boolean isValidBSTHelper(TreeNode node, Integer low, Integer high) {
        if (node == null) return true;
        if ((low != null && node.val <= low) || (high != null && node.val >= high)) return false;
        return isValidBSTHelper(node.left, low, node.val) && isValidBSTHelper(node.right, node.val, high);
    }

    // 2. 找出不符合 BST 規則的節點（中序遍歷中非遞增的節點）
    public static List<TreeNode> findInvalidNodes(TreeNode root) {
        List<TreeNode> invalids = new ArrayList<>();
        TreeNode[] prev = new TreeNode[1]; // 用陣列包裝以模擬引用
        inorderFindInvalid(root, prev, invalids);
        return invalids;
    }

    private static void inorderFindInvalid(TreeNode node, TreeNode[] prev, List<TreeNode> invalids) {
        if (node == null) return;
        inorderFindInvalid(node.left, prev, invalids);
        if (prev[0] != null && node.val <= prev[0].val) {
            invalids.add(prev[0]);
            invalids.add(node);
        }
        prev[0] = node;
        inorderFindInvalid(node.right, prev, invalids);
    }

    // 3. 修復有兩個錯誤節點的BST（LeetCode經典題）
    public static void repairBST(TreeNode root) {
        TreeNode[] first = new TreeNode[1];
        TreeNode[] second = new TreeNode[1];
        TreeNode[] prev = new TreeNode[1];
        recover(root, prev, first, second);
        if (first[0] != null && second[0] != null) {
            int tmp = first[0].val;
            first[0].val = second[0].val;
            second[0].val = tmp;
        }
    }

    private static void recover(TreeNode node, TreeNode[] prev, TreeNode[] first, TreeNode[] second) {
        if (node == null) return;
        recover(node.left, prev, first, second);

        if (prev[0] != null && node.val < prev[0].val) {
            if (first[0] == null) first[0] = prev[0];
            second[0] = node;
        }
        prev[0] = node;

        recover(node.right, prev, first, second);
    }

    // 4. 計算最少需移除節點數讓樹成為有效 BST
    // 等同於最大 BST 子樹大小 = n - minRemove
    public static int minRemovalsToMakeBST(TreeNode root) {
        int totalNodes = countNodes(root);
        int maxBST = largestBSTSubtree(root).maxSize;
        return totalNodes - maxBST;
    }

    private static int countNodes(TreeNode node) {
        if (node == null) return 0;
        return 1 + countNodes(node.left) + countNodes(node.right);
    }

    // 結構體儲存子樹資訊
    static class Info {
        boolean isBST;
        int minVal, maxVal, maxSize;
        Info(boolean isBST, int minVal, int maxVal, int maxSize) {
            this.isBST = isBST;
            this.minVal = minVal;
            this.maxVal = maxVal;
            this.maxSize = maxSize;
        }
    }

    // 找最大 BST 子樹 (自底向上遞迴)
    private static Info largestBSTSubtree(TreeNode node) {
        if (node == null) return new Info(true, Integer.MAX_VALUE, Integer.MIN_VALUE, 0);

        Info left = largestBSTSubtree(node.left);
        Info right = largestBSTSubtree(node.right);

        if (left.isBST && right.isBST && node.val > left.maxVal && node.val < right.minVal) {
            int size = left.maxSize + right.maxSize + 1;
            int minVal = Math.min(node.val, left.minVal);
            int maxVal = Math.max(node.val, right.maxVal);
            return new Info(true, minVal, maxVal, size);
        } else {
            return new Info(false, 0, 0, Math.max(left.maxSize, right.maxSize));
        }
    }
}


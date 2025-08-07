import java.util.*;

public class BSTRangeQuerySystem {

    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    TreeNode root;

    // 插入節點 (BST 標準插入)
    public void insert(int val) {
        root = insert(root, val);
    }

    private TreeNode insert(TreeNode node, int val) {
        if (node == null) return new TreeNode(val);
        if (val < node.val) node.left = insert(node.left, val);
        else node.right = insert(node.right, val);
        return node;
    }

    // 1. 範圍查詢：找出 [min, max] 範圍的節點值，回傳排序 List
    public List<Integer> rangeQuery(int min, int max) {
        List<Integer> result = new ArrayList<>();
        rangeQuery(root, min, max, result);
        return result;
    }

    private void rangeQuery(TreeNode node, int min, int max, List<Integer> result) {
        if (node == null) return;

        if (node.val > min) rangeQuery(node.left, min, max, result);
        if (node.val >= min && node.val <= max) result.add(node.val);
        if (node.val < max) rangeQuery(node.right, min, max, result);
    }

    // 2. 範圍計數：計算在範圍內節點數量
    public int rangeCount(int min, int max) {
        return rangeCount(root, min, max);
    }

    private int rangeCount(TreeNode node, int min, int max) {
        if (node == null) return 0;

        if (node.val < min) {
            return rangeCount(node.right, min, max);
        } else if (node.val > max) {
            return rangeCount(node.left, min, max);
        } else {
            return 1 + rangeCount(node.left, min, max) + rangeCount(node.right, min, max);
        }
    }

    // 3. 範圍總和：計算範圍內節點值總和
    public int rangeSum(int min, int max) {
        return rangeSum(root, min, max);
    }

    private int rangeSum(TreeNode node, int min, int max) {
        if (node == null) return 0;

        if (node.val < min) {
            return rangeSum(node.right, min, max);
        } else if (node.val > max) {
            return rangeSum(node.left, min, max);
        } else {
            return node.val + rangeSum(node.left, min, max) + rangeSum(node.right, min, max);
        }
    }

    // 4. 最接近查詢：找出最接近 target 的節點值
    public int closestValue(int target) {
        return closestValue(root, target, root.val);
    }

    private int closestValue(TreeNode node, int target, int closest) {
        if (node == null) return closest;

        if (Math.abs(node.val - target) < Math.abs(closest - target)) {
            closest = node.val;
        }

        if (target < node.val) {
            return closestValue(node.left, target, closest);
        } else if (target > node.val) {
            return closestValue(node.right, target, closest);
        } else {
            return node.val;
        }
    }

    // 測試
    public static void main(String[] args) {
        BSTRangeQuerySystem bst = new BSTRangeQuerySystem();

        int[] values = {15, 10, 20, 8, 12, 17, 25, 19};
        for (int v : values) {
            bst.insert(v);
        }

        int min = 10, max = 20;

        System.out.println("範圍查詢 [" + min + ", " + max + "]: " + bst.rangeQuery(min, max));
        System.out.println("範圍計數 [" + min + ", " + max + "]: " + bst.rangeCount(min, max));
        System.out.println("範圍總和 [" + min + ", " + max + "]: " + bst.rangeSum(min, max));

        int target = 16;
        System.out.println("最接近 " + target + " 的節點值: " + bst.closestValue(target));
    }
}


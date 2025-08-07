import java.util.*;

public class BSTKthElement {

    static class TreeNode {
        int val;
        TreeNode left, right;
        int count; // 子樹節點數(含自己)

        TreeNode(int val) {
            this.val = val;
            this.count = 1;
        }
    }

    TreeNode root;

    // 更新節點 count
    private int size(TreeNode node) {
        return node == null ? 0 : node.count;
    }

    // 插入節點 (維護 count)
    public void insert(int val) {
        root = insert(root, val);
    }

    private TreeNode insert(TreeNode node, int val) {
        if (node == null) return new TreeNode(val);
        if (val < node.val) node.left = insert(node.left, val);
        else node.right = insert(node.right, val);
        node.count = 1 + size(node.left) + size(node.right);
        return node;
    }

    // 刪除節點 (維護 count)
    public void delete(int val) {
        root = delete(root, val);
    }

    private TreeNode delete(TreeNode node, int val) {
        if (node == null) return null;

        if (val < node.val) {
            node.left = delete(node.left, val);
        } else if (val > node.val) {
            node.right = delete(node.right, val);
        } else { // 找到節點
            if (node.left == null) return node.right;
            if (node.right == null) return node.left;

            // 找右子樹最小節點替代
            TreeNode minNode = findMin(node.right);
            node.val = minNode.val;
            node.right = delete(node.right, minNode.val);
        }
        node.count = 1 + size(node.left) + size(node.right);
        return node;
    }

    private TreeNode findMin(TreeNode node) {
        while (node.left != null) node = node.left;
        return node;
    }

    // 找第 k 小元素 (k 從 1 開始)
    public int kthSmallest(int k) {
        if (k <= 0 || k > size(root)) throw new IllegalArgumentException("k 超出範圍");
        return kthSmallest(root, k);
    }

    private int kthSmallest(TreeNode node, int k) {
        int leftSize = size(node.left);
        if (k == leftSize + 1) return node.val;
        else if (k <= leftSize) return kthSmallest(node.left, k);
        else return kthSmallest(node.right, k - leftSize - 1);
    }

    // 找第 k 大元素，等同於找第 (n - k + 1) 小元素
    public int kthLargest(int k) {
        int n = size(root);
        if (k <= 0 || k > n) throw new IllegalArgumentException("k 超出範圍");
        return kthSmallest(root, n - k + 1);
    }

    // 找第 k 小到第 j 小之間的元素 (包含 k 和 j)
    public List<Integer> kthRange(int k, int j) {
        int n = size(root);
        if (k <= 0 || j > n || k > j) throw new IllegalArgumentException("範圍錯誤");
        List<Integer> result = new ArrayList<>();
        kthRange(root, k, j, result, new int[]{0});
        return result;
    }

    // 輔助：中序遞迴，計數已訪問節點
    private void kthRange(TreeNode node, int k, int j, List<Integer> result, int[] count) {
        if (node == null) return;
        kthRange(node.left, k, j, result, count);
        count[0]++;
        if (count[0] >= k && count[0] <= j) {
            result.add(node.val);
        }
        if (count[0] > j) return;
        kthRange(node.right, k, j, result, count);
    }

    // 範例測試
    public static void main(String[] args) {
        BSTKthElement bst = new BSTKthElement();

        int[] vals = {20, 8, 22, 4, 12, 10, 14};
        for (int v : vals) bst.insert(v);

        System.out.println("第 3 小元素: " + bst.kthSmallest(3)); // 10
        System.out.println("第 2 大元素: " + bst.kthLargest(2)); // 20

        System.out.println("第 2 小到第 5 小元素: " + bst.kthRange(2, 5)); // [8,10,12,14]

        // 測試刪除
        bst.delete(10);
        System.out.println("刪除 10 後，第 3 小元素: " + bst.kthSmallest(3)); // 12
    }
}


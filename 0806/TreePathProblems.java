import java.util.*;

public class TreePathProblems {

    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    public static void main(String[] args) {
        /*
            範例樹:
                  10
                 /  \
                5    12
               / \     \
              4   7     15
        */
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(5);
        root.right = new TreeNode(12);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(7);
        root.right.right = new TreeNode(15);

        System.out.println("所有根到葉路徑:");
        List<List<Integer>> allPaths = allRootToLeafPaths(root);
        for (List<Integer> path : allPaths) {
            System.out.println(path);
        }

        int targetSum = 22;
        System.out.println("\n是否存在根到葉路徑和為 " + targetSum + ": " + hasPathSum(root, targetSum));

        List<Integer> maxSumPath = maxRootToLeafPath(root);
        System.out.println("\n根到葉路徑中和最大的路徑: " + maxSumPath + ", 和 = " + sumList(maxSumPath));

        System.out.println("\n任意兩節點間的最大路徑和: " + maxPathSum(root));
    }

    // 1. 找出所有根到葉節點的路徑
    public static List<List<Integer>> allRootToLeafPaths(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        dfsPaths(root, new ArrayList<>(), res);
        return res;
    }

    private static void dfsPaths(TreeNode node, List<Integer> path, List<List<Integer>> res) {
        if (node == null) return;
        path.add(node.val);
        if (node.left == null && node.right == null) {
            res.add(new ArrayList<>(path));
        } else {
            dfsPaths(node.left, path, res);
            dfsPaths(node.right, path, res);
        }
        path.remove(path.size() - 1);
    }

    // 2. 判斷是否存在根到葉路徑和為目標值
    public static boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) return false;
        if (root.left == null && root.right == null) return root.val == targetSum;
        return hasPathSum(root.left, targetSum - root.val) || hasPathSum(root.right, targetSum - root.val);
    }

    // 3. 找出和最大的根到葉路徑
    public static List<Integer> maxRootToLeafPath(TreeNode root) {
        if (root == null) return new ArrayList<>();
        if (root.left == null && root.right == null) {
            return new ArrayList<>(List.of(root.val));
        }

        List<Integer> leftPath = maxRootToLeafPath(root.left);
        List<Integer> rightPath = maxRootToLeafPath(root.right);

        int leftSum = sumList(leftPath);
        int rightSum = sumList(rightPath);

        if (leftSum > rightSum) {
            leftPath.add(0, root.val);
            return leftPath;
        } else {
            rightPath.add(0, root.val);
            return rightPath;
        }
    }

    private static int sumList(List<Integer> list) {
        int sum = 0;
        for (int v : list) sum += v;
        return sum;
    }

    // 4. 計算任意兩節點間的最大路徑和（樹的直徑改為最大和）
    static int maxPathSumGlobal = Integer.MIN_VALUE;

    public static int maxPathSum(TreeNode root) {
        maxPathSumGlobal = Integer.MIN_VALUE;
        maxPathDown(root);
        return maxPathSumGlobal;
    }

    // 返回以node為起點往下一條路徑的最大和 (可只選一支路徑或不選)
    private static int maxPathDown(TreeNode node) {
        if (node == null) return 0;

        int left = Math.max(0, maxPathDown(node.left));  // 不取負數
        int right = Math.max(0, maxPathDown(node.right));

        // 可能最大路徑通過node，左右子樹和node.val相加
        int sumWithNode = left + right + node.val;
        maxPathSumGlobal = Math.max(maxPathSumGlobal, sumWithNode);

        // 回傳單一路徑最大和（只能選一邊往下）
        return node.val + Math.max(left, right);
    }
}


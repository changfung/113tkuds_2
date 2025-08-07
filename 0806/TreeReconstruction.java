import java.util.*;

public class TreeReconstruction {

    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    public static void main(String[] args) {
        /*
            範例樹：
                 1
                / \
               2   3
              / \   \
             4   5   6

            前序: [1,2,4,5,3,6]
            中序: [4,2,5,1,3,6]
            後序: [4,5,2,6,3,1]
            層序: [1,2,3,4,5,6]
        */
        int[] preorder = {1, 2, 4, 5, 3, 6};
        int[] inorder = {4, 2, 5, 1, 3, 6};
        int[] postorder = {4, 5, 2, 6, 3, 1};
        int[] levelorder = {1, 2, 3, 4, 5, 6};

        TreeNode rootFromPreIn = buildTreeFromPreIn(preorder, inorder);
        System.out.println("前序+中序重建前序:");
        printPreorder(rootFromPreIn);

        TreeNode rootFromPostIn = buildTreeFromPostIn(postorder, inorder);
        System.out.println("\n後序+中序重建後序:");
        printPostorder(rootFromPostIn);

        TreeNode rootFromLevel = buildCompleteTreeFromLevel(levelorder);
        System.out.println("\n層序重建前序:");
        printPreorder(rootFromLevel);

        System.out.println("\n前序+中序重建 vs 原前序相同？ " +
            Arrays.equals(preorder, treeToPreorderArray(rootFromPreIn, preorder.length)));

        System.out.println("後序+中序重建 vs 原後序相同？ " +
            Arrays.equals(postorder, treeToPostorderArray(rootFromPostIn, postorder.length)));

        System.out.println("層序重建前序 vs 原前序相同？ " +
            Arrays.equals(preorder, treeToPreorderArray(rootFromLevel, preorder.length)));
    }

    // 1. 根據前序和中序重建
    static int preIndex = 0;

    public static TreeNode buildTreeFromPreIn(int[] preorder, int[] inorder) {
        preIndex = 0;
        Map<Integer, Integer> inMap = new HashMap<>();
        for (int i=0; i<inorder.length; i++) {
            inMap.put(inorder[i], i);
        }
        return buildPreIn(preorder, 0, inorder.length -1, inMap);
    }

    private static TreeNode buildPreIn(int[] preorder, int inStart, int inEnd, Map<Integer, Integer> inMap) {
        if (inStart > inEnd) return null;

        int rootVal = preorder[preIndex++];
        TreeNode root = new TreeNode(rootVal);
        int inIndex = inMap.get(rootVal);

        root.left = buildPreIn(preorder, inStart, inIndex - 1, inMap);
        root.right = buildPreIn(preorder, inIndex + 1, inEnd, inMap);

        return root;
    }

    // 2. 根據後序和中序重建
    static int postIndex;

    public static TreeNode buildTreeFromPostIn(int[] postorder, int[] inorder) {
        postIndex = postorder.length - 1;
        Map<Integer, Integer> inMap = new HashMap<>();
        for (int i=0; i<inorder.length; i++) {
            inMap.put(inorder[i], i);
        }
        return buildPostIn(postorder, 0, inorder.length - 1, inMap);
    }

    private static TreeNode buildPostIn(int[] postorder, int inStart, int inEnd, Map<Integer, Integer> inMap) {
        if (inStart > inEnd) return null;

        int rootVal = postorder[postIndex--];
        TreeNode root = new TreeNode(rootVal);
        int inIndex = inMap.get(rootVal);

        // 注意：後序先建立右子樹，再建立左子樹（因為後序倒序遍歷）
        root.right = buildPostIn(postorder, inIndex + 1, inEnd, inMap);
        root.left = buildPostIn(postorder, inStart, inIndex - 1, inMap);

        return root;
    }

    // 3. 根據層序重建完全二元樹
    public static TreeNode buildCompleteTreeFromLevel(int[] levelorder) {
        if (levelorder.length == 0) return null;

        TreeNode root = new TreeNode(levelorder[0]);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int i = 1;
        while (i < levelorder.length) {
            TreeNode parent = queue.poll();
            TreeNode left = new TreeNode(levelorder[i++]);
            parent.left = left;
            queue.offer(left);

            if (i < levelorder.length) {
                TreeNode right = new TreeNode(levelorder[i++]);
                parent.right = right;
                queue.offer(right);
            }
        }
        return root;
    }

    // 驗證：前序走訪
    public static void printPreorder(TreeNode root) {
        if (root == null) return;
        System.out.print(root.val + " ");
        printPreorder(root.left);
        printPreorder(root.right);
    }

    // 驗證：後序走訪
    public static void printPostorder(TreeNode root) {
        if (root == null) return;
        printPostorder(root.left);
        printPostorder(root.right);
        System.out.print(root.val + " ");
    }

    // 走訪轉陣列（用於驗證）
    public static int[] treeToPreorderArray(TreeNode root, int size) {
        int[] arr = new int[size];
        int[] idx = new int[1];
        preorderToArray(root, arr, idx);
        return arr;
    }

    private static void preorderToArray(TreeNode node, int[] arr, int[] idx) {
        if (node == null) return;
        arr[idx[0]++] = node.val;
        preorderToArray(node.left, arr, idx);
        preorderToArray(node.right, arr, idx);
    }

    public static int[] treeToPostorderArray(TreeNode root, int size) {
        int[] arr = new int[size];
        int[] idx = new int[1];
        postorderToArray(root, arr, idx);
        return arr;
    }

    private static void postorderToArray(TreeNode node, int[] arr, int[] idx) {
        if (node == null) return;
        postorderToArray(node.left, arr, idx);
        postorderToArray(node.right, arr, idx);
        arr[idx[0]++] = node.val;
    }
}


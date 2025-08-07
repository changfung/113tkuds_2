public class TreeMirrorAndSymmetry {

    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    public static void main(String[] args) {
        /*
         建立測試樹：
                  1
                 / \
                2   2
               /     \
              3       3
         */
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(2);
        root.left.left = new TreeNode(3);
        root.right.right = new TreeNode(3);

        System.out.println("是否對稱樹: " + isSymmetric(root));

        TreeNode mirrored = mirrorTree(cloneTree(root));
        System.out.println("鏡像樹前序列印:");
        preOrderPrint(mirrored);
        System.out.println();

        TreeNode tree1 = root;
        TreeNode tree2 = mirrored;
        System.out.println("tree1 與 tree2 是否鏡像: " + areMirrors(tree1, tree2));

        TreeNode subTree = new TreeNode(2);
        subTree.left = new TreeNode(3);
        System.out.println("subTree 是否為 root 的子樹: " + isSubtree(root, subTree));
    }

    // 判斷是否為對稱樹（鏡像對稱）
    public static boolean isSymmetric(TreeNode root) {
        if (root == null) return true;
        return isMirror(root.left, root.right);
    }

    private static boolean isMirror(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) return true;
        if (t1 == null || t2 == null) return false;
        return (t1.val == t2.val)
            && isMirror(t1.left, t2.right)
            && isMirror(t1.right, t2.left);
    }

    // 將樹轉成鏡像樹（原地反轉）
    public static TreeNode mirrorTree(TreeNode root) {
        if (root == null) return null;
        TreeNode leftMirror = mirrorTree(root.left);
        TreeNode rightMirror = mirrorTree(root.right);
        root.left = rightMirror;
        root.right = leftMirror;
        return root;
    }

    // 比較兩棵樹是否為鏡像（節點值相等且左右相反）
    public static boolean areMirrors(TreeNode t1, TreeNode t2) {
        return isMirror(t1, t2);
    }

    // 判斷 sub 是否為 root 的子樹
    public static boolean isSubtree(TreeNode root, TreeNode sub) {
        if (sub == null) return true; // 空樹是任意樹的子樹
        if (root == null) return false;

        if (isSameTree(root, sub)) return true;
        return isSubtree(root.left, sub) || isSubtree(root.right, sub);
    }

    // 判斷兩棵樹是否完全相同
    public static boolean isSameTree(TreeNode s, TreeNode t) {
        if (s == null && t == null) return true;
        if (s == null || t == null) return false;
        if (s.val != t.val) return false;
        return isSameTree(s.left, t.left) && isSameTree(s.right, t.right);
    }

    // 前序列印樹（用於測試鏡像結果）
    public static void preOrderPrint(TreeNode root) {
        if (root == null) return;
        System.out.print(root.val + " ");
        preOrderPrint(root.left);
        preOrderPrint(root.right);
    }

    // 深度複製樹（避免鏡像修改原樹）
    public static TreeNode cloneTree(TreeNode root) {
        if (root == null) return null;
        TreeNode newNode = new TreeNode(root.val);
        newNode.left = cloneTree(root.left);
        newNode.right = cloneTree(root.right);
        return newNode;
    }
}


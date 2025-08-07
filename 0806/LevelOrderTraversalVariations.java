import java.util.*;

public class LevelOrderTraversalVariations {

    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    public static void main(String[] args) {
        /*
             建立測試樹
                   1
                  / \
                 2   3
                / \   \
               4   5   6
        */
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.right = new TreeNode(6);

        System.out.println("1. 每層節點分組:");
        List<List<Integer>> levels = levelOrderGroups(root);
        System.out.println(levels);

        System.out.println("\n2. 之字形層序走訪:");
        List<List<Integer>> zigzag = zigzagLevelOrder(root);
        System.out.println(zigzag);

        System.out.println("\n3. 每層最後一個節點:");
        List<Integer> lastNodes = lastNodeEachLevel(root);
        System.out.println(lastNodes);

        System.out.println("\n4. 垂直層序走訪:");
        List<List<Integer>> vertical = verticalOrder(root);
        System.out.println(vertical);
    }

    // 1. 每層節點分別儲存成 List<List<Integer>>
    public static List<List<Integer>> levelOrderGroups(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;

        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);

        while (!q.isEmpty()) {
            int size = q.size();
            List<Integer> level = new ArrayList<>();
            for (int i=0; i<size; i++) {
                TreeNode node = q.poll();
                level.add(node.val);
                if (node.left != null) q.offer(node.left);
                if (node.right != null) q.offer(node.right);
            }
            res.add(level);
        }
        return res;
    }

    // 2. 之字形層序走訪 (奇數層左→右, 偶數層右→左)
    public static List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;

        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        boolean leftToRight = true;

        while (!q.isEmpty()) {
            int size = q.size();
            LinkedList<Integer> level = new LinkedList<>();
            for (int i=0; i<size; i++) {
                TreeNode node = q.poll();
                if (leftToRight) level.addLast(node.val);
                else level.addFirst(node.val);

                if (node.left != null) q.offer(node.left);
                if (node.right != null) q.offer(node.right);
            }
            res.add(new ArrayList<>(level));
            leftToRight = !leftToRight;
        }
        return res;
    }

    // 3. 每層最後一個節點值
    public static List<Integer> lastNodeEachLevel(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;

        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);

        while (!q.isEmpty()) {
            int size = q.size();
            int lastVal = 0;
            for (int i=0; i<size; i++) {
                TreeNode node = q.poll();
                lastVal = node.val;
                if (node.left != null) q.offer(node.left);
                if (node.right != null) q.offer(node.right);
            }
            res.add(lastVal);
        }
        return res;
    }

    // 4. 垂直層序走訪（根節點水平位置為0，左子節點-1，右子節點+1）
    public static List<List<Integer>> verticalOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;

        // map: key=horizontal distance, value=List of node values in order of BFS
        TreeMap<Integer, List<Integer>> columnTable = new TreeMap<>();

        Queue<TreeNode> queue = new LinkedList<>();
        Queue<Integer> columnQueue = new LinkedList<>();
        queue.offer(root);
        columnQueue.offer(0);

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            int col = columnQueue.poll();

            columnTable.putIfAbsent(col, new ArrayList<>());
            columnTable.get(col).add(node.val);

            if (node.left != null) {
                queue.offer(node.left);
                columnQueue.offer(col - 1);
            }
            if (node.right != null) {
                queue.offer(node.right);
                columnQueue.offer(col + 1);
            }
        }

        for (List<Integer> colNodes : columnTable.values()) {
            res.add(colNodes);
        }
        return res;
    }
}


import java.util.*;

public class AVLLeaderboardSystem {

    class Node {
        String playerId;
        int score;
        Node left, right;
        int height;
        int size; // 以此節點為根的子樹節點總數（用於 rank/select）

        Node(String playerId, int score) {
            this.playerId = playerId;
            this.score = score;
            this.height = 1;
            this.size = 1;
        }
    }

    private Node root;
    private Map<String, Integer> playerScores = new HashMap<>();

    // ==== 基本工具 ====
    int height(Node node) {
        return node == null ? 0 : node.height;
    }

    int size(Node node) {
        return node == null ? 0 : node.size;
    }

    void update(Node node) {
        if (node != null) {
            node.height = 1 + Math.max(height(node.left), height(node.right));
            node.size = 1 + size(node.left) + size(node.right);
        }
    }

    int getBalance(Node node) {
        return node == null ? 0 : height(node.left) - height(node.right);
    }

    Node rightRotate(Node y) {
        Node x = y.left;
        Node T2 = x.right;

        x.right = y;
        y.left = T2;

        update(y);
        update(x);
        return x;
    }

    Node leftRotate(Node x) {
        Node y = x.right;
        Node T2 = y.left;

        y.left = x;
        x.right = T2;

        update(x);
        update(y);
        return y;
    }

    // ==== 插入 ====
    Node insert(Node node, String playerId, int score) {
        if (node == null) return new Node(playerId, score);

        if (score > node.score || (score == node.score && playerId.compareTo(node.playerId) < 0)) {
            node.left = insert(node.left, playerId, score);
        } else {
            node.right = insert(node.right, playerId, score);
        }

        update(node);
        return rebalance(node);
    }

    Node rebalance(Node node) {
        int balance = getBalance(node);

        if (balance > 1 && getBalance(node.left) >= 0)
            return rightRotate(node);
        if (balance > 1 && getBalance(node.left) < 0) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        if (balance < -1 && getBalance(node.right) <= 0)
            return leftRotate(node);
        if (balance < -1 && getBalance(node.right) > 0) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    // ==== 刪除節點 ====
    Node delete(Node node, String playerId, int score) {
        if (node == null) return null;

        if (score > node.score || (score == node.score && playerId.compareTo(node.playerId) < 0)) {
            node.left = delete(node.left, playerId, score);
        } else if (score < node.score || (score == node.score && playerId.compareTo(node.playerId) > 0)) {
            node.right = delete(node.right, playerId, score);
        } else {
            if (node.left == null || node.right == null) {
                return node.left != null ? node.left : node.right;
            } else {
                Node successor = getMin(node.right);
                node.playerId = successor.playerId;
                node.score = successor.score;
                node.right = delete(node.right, successor.playerId, successor.score);
            }
        }

        update(node);
        return rebalance(node);
    }

    Node getMin(Node node) {
        while (node.left != null) node = node.left;
        return node;
    }

    // ==== 公開操作 ====
    public void addPlayer(String playerId, int score) {
        if (playerScores.containsKey(playerId)) {
            updateScore(playerId, score);
            return;
        }
        root = insert(root, playerId, score);
        playerScores.put(playerId, score);
    }

    public void updateScore(String playerId, int newScore) {
        if (playerScores.containsKey(playerId)) {
            int oldScore = playerScores.get(playerId);
            root = delete(root, playerId, oldScore);
        }
        root = insert(root, playerId, newScore);
        playerScores.put(playerId, newScore);
    }

    // ==== 查詢排名（第幾名，1 為最高分）====
    public int getRank(String playerId) {
        if (!playerScores.containsKey(playerId)) return -1;
        int score = playerScores.get(playerId);
        return getRank(root, playerId, score) + 1; // 加 1 表示從第 1 名開始
    }

    private int getRank(Node node, String playerId, int score) {
        if (node == null) return 0;

        if (score > node.score || (score == node.score && playerId.compareTo(node.playerId) < 0)) {
            return getRank(node.left, playerId, score);
        } else if (score < node.score || (score == node.score && playerId.compareTo(node.playerId) > 0)) {
            return size(node.left) + 1 + getRank(node.right, playerId, score);
        } else {
            return size(node.left);
        }
    }

    // ==== 查詢前 K 名玩家 ====
    public List<String> getTopK(int k) {
        List<String> result = new ArrayList<>();
        getTopK(root, result, k);
        return result;
    }

    private void getTopK(Node node, List<String> result, int k) {
        if (node == null || result.size() >= k) return;

        getTopK(node.left, result, k);
        if (result.size() < k)
            result.add(node.playerId);
        getTopK(node.right, result, k);
    }

    // ==== 測試 ====
    public static void main(String[] args) {
        AVLLeaderboardSystem leaderboard = new AVLLeaderboardSystem();

        leaderboard.addPlayer("Alice", 100);
        leaderboard.addPlayer("Bob", 120);
        leaderboard.addPlayer("Charlie", 90);
        leaderboard.addPlayer("David", 120);
        leaderboard.addPlayer("Eve", 110);

        System.out.println("排名查詢 (Alice): " + leaderboard.getRank("Alice")); // e.g. 4
        System.out.println("排名查詢 (Bob): " + leaderboard.getRank("Bob"));     // 1 or 2
        System.out.println("前 3 名玩家: " + leaderboard.getTopK(3)); // 應該是 ["Bob", "David", "Eve"]

        leaderboard.updateScore("Alice", 125);
        System.out.println("排名查詢 (Alice 更新後): " + leaderboard.getRank("Alice")); // 應為第 1 名
        System.out.println("前 3 名玩家: " + leaderboard.getTopK(3)); // 應該是 ["Alice", "Bob", "David"]
    }
}


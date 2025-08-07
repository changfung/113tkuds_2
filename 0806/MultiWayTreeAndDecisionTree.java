import java.util.*;

public class MultiWayTreeAndDecisionTree {

    // 多路樹節點，children可有任意多個子節點
    static class MultiWayNode {
        String val;
        List<MultiWayNode> children;
        MultiWayNode(String val) {
            this.val = val;
            this.children = new ArrayList<>();
        }
    }

    public static void main(String[] args) {
        // 建立範例多路樹
        /*
                Root
               /  |   \
             A    B    C
            / \         \
           A1  A2        C1
        */
        MultiWayNode root = new MultiWayNode("Root");
        MultiWayNode A = new MultiWayNode("A");
        MultiWayNode B = new MultiWayNode("B");
        MultiWayNode C = new MultiWayNode("C");
        MultiWayNode A1 = new MultiWayNode("A1");
        MultiWayNode A2 = new MultiWayNode("A2");
        MultiWayNode C1 = new MultiWayNode("C1");

        root.children.add(A);
        root.children.add(B);
        root.children.add(C);
        A.children.add(A1);
        A.children.add(A2);
        C.children.add(C1);

        System.out.println("多路樹深度優先走訪:");
        dfsMultiWay(root);

        System.out.println("\n多路樹廣度優先走訪:");
        bfsMultiWay(root);

        System.out.println("\n決策樹示範 - 猜數字遊戲:");
        decisionTreeGuessNumber();

        System.out.println("\n多路樹高度: " + getHeight(root));
        System.out.println("各節點度數:");
        printDegrees(root);
    }

    // 1. 多路樹深度優先走訪 (Preorder)
    public static void dfsMultiWay(MultiWayNode node) {
        if (node == null) return;
        System.out.print(node.val + " ");
        for (MultiWayNode child : node.children) {
            dfsMultiWay(child);
        }
    }

    // 2. 多路樹廣度優先走訪
    public static void bfsMultiWay(MultiWayNode root) {
        if (root == null) return;
        Queue<MultiWayNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            MultiWayNode curr = queue.poll();
            System.out.print(curr.val + " ");
            for (MultiWayNode child : curr.children) {
                queue.offer(child);
            }
        }
    }

    // 3. 簡單決策樹 - 猜數字遊戲（範例）
    public static void decisionTreeGuessNumber() {
        /*
            決策樹結構範例：
            問題: 目標數字是大於50嗎？
            - 是 -> 問: 大於75嗎？
                - 是 -> 答: 目標數字是 80
                - 否 -> 答: 目標數字是 60
            - 否 -> 問: 小於25嗎？
                - 是 -> 答: 目標數字是 10
                - 否 -> 答: 目標數字是 40
        */
        DecisionNode root = new DecisionNode("目標數字 > 50?");
        DecisionNode nodeYes = new DecisionNode("目標數字 > 75?");
        DecisionNode nodeNo = new DecisionNode("目標數字 < 25?");

        DecisionNode leaf1 = new DecisionNode("目標數字是 80");
        DecisionNode leaf2 = new DecisionNode("目標數字是 60");
        DecisionNode leaf3 = new DecisionNode("目標數字是 10");
        DecisionNode leaf4 = new DecisionNode("目標數字是 40");

        root.yes = nodeYes;
        root.no = nodeNo;
        nodeYes.yes = leaf1;
        nodeYes.no = leaf2;
        nodeNo.yes = leaf3;
        nodeNo.no = leaf4;

        // 模擬決策流程
        simulateDecision(root, 65);
        simulateDecision(root, 20);
        simulateDecision(root, 80);
        simulateDecision(root, 40);
    }

    static class DecisionNode {
        String questionOrAnswer;
        DecisionNode yes;
        DecisionNode no;
        DecisionNode(String val) { questionOrAnswer = val; }
    }

    // 模擬決策樹(猜數字)根據目標數字走訪
    public static void simulateDecision(DecisionNode node, int target) {
        System.out.println("\n判斷目標數字: " + target);
        DecisionNode curr = node;
        while (curr.yes != null && curr.no != null) {
            System.out.println(curr.questionOrAnswer);
            // 根據問題簡單判斷選擇 yes/no 路徑 (以示範為主)
            if (curr.questionOrAnswer.contains("> 50")) {
                curr = (target > 50) ? curr.yes : curr.no;
            } else if (curr.questionOrAnswer.contains("> 75")) {
                curr = (target > 75) ? curr.yes : curr.no;
            } else if (curr.questionOrAnswer.contains("< 25")) {
                curr = (target < 25) ? curr.yes : curr.no;
            } else {
                // 預設no分支
                curr = curr.no;
            }
        }
        System.out.println("結果: " + curr.questionOrAnswer);
    }

    // 4. 計算多路樹高度 (最大子樹高度 +1)
    public static int getHeight(MultiWayNode node) {
        if (node == null) return 0;
        int maxChildHeight = 0;
        for (MultiWayNode child : node.children) {
            maxChildHeight = Math.max(maxChildHeight, getHeight(child));
        }
        return maxChildHeight + 1;
    }

    // 計算並印出各節點度數 (子節點數)
    public static void printDegrees(MultiWayNode node) {
        if (node == null) return;
        System.out.println("節點 " + node.val + " 度數: " + node.children.size());
        for (MultiWayNode child : node.children) {
            printDegrees(child);
        }
    }
}


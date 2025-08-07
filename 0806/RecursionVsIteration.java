
public class RecursionVsIteration {

    public static void main(String[] args) {
        // 測試資料
        int n = 5, k = 2;
        int[] arr = {2, 3, 4};
        String str = "Recursion vs Iteration";
        String parentheses = "((())())";

        // 1. 二項式係數
        System.out.println("1. 二項式係數 C(" + n + "," + k + ")");
        System.out.println("遞迴版本: " + binomialRecursive(n, k));
        System.out.println("迭代版本: " + binomialIterative(n, k));

        // 2. 陣列乘積
        System.out.println("\n2. 陣列乘積");
        System.out.println("遞迴版本: " + productRecursive(arr, 0));
        System.out.println("迭代版本: " + productIterative(arr));

        // 3. 元音字母數量
        System.out.println("\n3. 字串中元音數量: \"" + str + "\"");
        System.out.println("遞迴版本: " + countVowelsRecursive(str.toLowerCase(), 0));
        System.out.println("迭代版本: " + countVowelsIterative(str.toLowerCase()));

        // 4. 括號配對檢查
        System.out.println("\n4. 括號是否配對正確: \"" + parentheses + "\"");
        System.out.println("遞迴版本: " + (isBalancedRecursive(parentheses, 0, 0) ? "正確" : "錯誤"));
        System.out.println("迭代版本: " + (isBalancedIterative(parentheses) ? "正確" : "錯誤"));
    }

    // ===== 1. 二項式係數 C(n, k) =====
    public static int binomialRecursive(int n, int k) {
        if (k == 0 || k == n) return 1;
        return binomialRecursive(n - 1, k - 1) + binomialRecursive(n - 1, k);
    }

    public static int binomialIterative(int n, int k) {
        int[][] C = new int[n + 1][k + 1];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= Math.min(i, k); j++) {
                if (j == 0 || j == i)
                    C[i][j] = 1;
                else
                    C[i][j] = C[i - 1][j - 1] + C[i - 1][j];
            }
        }
        return C[n][k];
    }

    // ===== 2. 陣列元素乘積 =====
    public static int productRecursive(int[] arr, int index) {
        if (index == arr.length) return 1;
        return arr[index] * productRecursive(arr, index + 1);
    }

    public static int productIterative(int[] arr) {
        int result = 1;
        for (int num : arr) {
            result *= num;
        }
        return result;
    }

    // ===== 3. 字串元音數量 =====
    public static int countVowelsRecursive(String str, int index) {
        if (index == str.length()) return 0;
        char c = str.charAt(index);
        int count = (isVowel(c)) ? 1 : 0;
        return count + countVowelsRecursive(str, index + 1);
    }

    public static int countVowelsIterative(String str) {
        int count = 0;
        for (char c : str.toCharArray()) {
            if (isVowel(c)) count++;
        }
        return count;
    }

    private static boolean isVowel(char c) {
        return "aeiou".indexOf(c) != -1;
    }

    // ===== 4. 檢查括號配對 =====
    public static boolean isBalancedRecursive(String str, int index, int balance) {
        if (balance < 0) return false; // 提前失衡
        if (index == str.length()) return balance == 0;
        char c = str.charAt(index);
        if (c == '(') return isBalancedRecursive(str, index + 1, balance + 1);
        if (c == ')') return isBalancedRecursive(str, index + 1, balance - 1);
        return isBalancedRecursive(str, index + 1, balance); // 跳過非括號
    }

    public static boolean isBalancedIterative(String str) {
        int balance = 0;
        for (char c : str.toCharArray()) {
            if (c == '(') balance++;
            else if (c == ')') balance--;
            if (balance < 0) return false;
        }
        return balance == 0;
    }
}


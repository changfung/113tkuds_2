public class RecursiveMathCalculator {
    public static void main(String[] args) {
        // 測試組合數 C(n, k)
        int n = 5, k = 2;
        System.out.println("組合數 C(" + n + ", " + k + ") = " + combination(n, k));

        // 測試卡塔蘭數
        int catalanN = 5;
        System.out.println("卡塔蘭數 C(" + catalanN + ") = " + catalan(catalanN));

        // 測試漢諾塔步數
        int hanoiN = 4;
        System.out.println("漢諾塔 " + hanoiN + " 層的最少步數 = " + hanoiSteps(hanoiN));

        // 測試回文判斷
        int number = 12321;
        System.out.println("數字 " + number + (isPalindrome(number) ? " 是" : " 不是") + " 回文數");
    }

    // 1. 組合數 C(n, k) 遞迴版本
    public static int combination(int n, int k) {
        if (k == 0 || k == n) {
            return 1;
        }
        return combination(n - 1, k - 1) + combination(n - 1, k);
    }

    // 2. 卡塔蘭數 Catalan(n) 遞迴版本（不建議太大，會很慢）
    public static int catalan(int n) {
        if (n <= 1) return 1;

        int result = 0;
        for (int i = 0; i < n; i++) {
            result += catalan(i) * catalan(n - 1 - i);
        }
        return result;
    }

    // 3. 漢諾塔步數 hanoi(n) = 2 * hanoi(n-1) + 1
    public static int hanoiSteps(int n) {
        if (n == 1) return 1;
        return 2 * hanoiSteps(n - 1) + 1;
    }

    // 4. 判斷數字是否為回文數（遞迴版本）
    public static boolean isPalindrome(int number) {
        String str = String.valueOf(number);
        return isPalindromeHelper(str, 0, str.length() - 1);
    }

    private static boolean isPalindromeHelper(String s, int left, int right) {
        if (left >= right) return true;
        if (s.charAt(left) != s.charAt(right)) return false;
        return isPalindromeHelper(s, left + 1, right - 1);
    }
}


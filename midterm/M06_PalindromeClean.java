import java.util.*;

public class M06_PalindromeClean {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();

        if (isPalindrome(s)) {
            System.out.println("Yes");
        } else {
            System.out.println("No");
        }
    }

    /**
     * 雙指標檢查清洗後是否回文
     */
    static boolean isPalindrome(String s) {
        int left = 0, right = s.length() - 1;
        while (left < right) {
            char cl = s.charAt(left);
            char cr = s.charAt(right);

            if (!Character.isLetter(cl)) {
                left++;
                continue;
            }
            if (!Character.isLetter(cr)) {
                right--;
                continue;
            }

            if (Character.toLowerCase(cl) != Character.toLowerCase(cr)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}

/*
 * Time Complexity: O(n)
 * 說明：
 * 1. 每個字元最多檢查一次（跳過或比較），總操作 O(n)。
 * 2. 空間複雜度 O(1)，僅使用指標變數。
 */


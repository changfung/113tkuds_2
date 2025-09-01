public class lt_09_palindromeinteger {
    public static boolean isPalindrome(int x) {
        if (x < 0 || (x % 10 == 0 && x != 0)) return false;

        int reversed = 0;
        while (x > reversed) {
            int pop = x % 10;
            x /= 10;
            reversed = reversed * 10 + pop;
        }

        return x == reversed || x == reversed / 10;
    }

    public static void main(String[] args) {
        System.out.println(isPalindrome(121));  // true
        System.out.println(isPalindrome(-121)); // false
        System.out.println(isPalindrome(10));   // false
        System.out.println(isPalindrome(0));    // true
    }
}


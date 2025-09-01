public class lt_08_stringtointeger {
    public static int myAtoi(String s) {
        if (s == null || s.length() == 0) return 0;

        int i = 0, n = s.length();
        while (i < n && s.charAt(i) == ' ') i++; // 忽略前導空格

        if (i == n) return 0;

        int sign = 1;
        char c = s.charAt(i);
        if (c == '+') {
            sign = 1;
            i++;
        } else if (c == '-') {
            sign = -1;
            i++;
        }

        int result = 0;
        while (i < n) {
            char ch = s.charAt(i);
            if (ch < '0' || ch > '9') break;

            int digit = ch - '0';

            // 檢查溢位
            if (result > (Integer.MAX_VALUE - digit) / 10) {
                return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }

            result = result * 10 + digit;
            i++;
        }

        return result * sign;
    }

    public static void main(String[] args) {
        System.out.println(myAtoi("42"));            // 42
        System.out.println(myAtoi("   -042"));       // -42
        System.out.println(myAtoi("1337c0d3"));      // 1337
        System.out.println(myAtoi("0-1"));           // 0
        System.out.println(myAtoi("words and 987")); // 0
        System.out.println(myAtoi("-91283472332"));  // Integer.MIN_VALUE
        System.out.println(myAtoi("91283472332"));   // Integer.MAX_VALUE
    }
}


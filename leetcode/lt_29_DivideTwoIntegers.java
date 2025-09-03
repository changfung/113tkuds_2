class Solution {
    public int divide(int dividend, int divisor) {
        if (dividend == Integer.MIN_VALUE && divisor == -1) return Integer.MAX_VALUE;

        boolean negative = (dividend < 0) ^ (divisor < 0);
        long ldividend = Math.abs((long) dividend);
        long ldivisor = Math.abs((long) divisor);
        int result = 0;

        while (ldividend >= ldivisor) {
            long temp = ldivisor, multiple = 1;
            while (ldividend >= (temp << 1)) {
                temp <<= 1;
                multiple <<= 1;
            }
            ldividend -= temp;
            result += multiple;
        }

        return negative ? -result : result;
    }
}

/*
解題思路：
1. 使用位運算與減法模擬除法，避免使用 *, /, %。
2. 每次將除數翻倍，找到最大可減去的倍數。
3. 處理溢位情況。
時間：O(log^2(n))，空間：O(1)
*/

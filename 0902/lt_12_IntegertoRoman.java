class Solution {
    public String intToRoman(int num) {
        // 羅馬數字與數值對照表，從大到小排序
        int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] symbols = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < values.length && num > 0; i++) {
            while (num >= values[i]) {
                num -= values[i];          // 減去對應數值
                sb.append(symbols[i]);     // 附加對應羅馬符號
            }
        }
        return sb.toString();
    }
}

/*
解題思路：
1. 準備好數值與符號對照表，含特殊數值（900、400、90、40、9、4）。
2. 由大至小迭代，減去能匹配的最大值，並附加符號到結果。
3. 使用 StringBuilder 優化字串串接效率。
4. 時間複雜度 O(1)，因為數值範圍固定 (1 ≤ num ≤ 3999)。
*/
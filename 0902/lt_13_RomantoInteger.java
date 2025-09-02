import java.util.HashMap;
import java.util.Map;

class Solution {
    public int romanToInt(String s) {
        // 羅馬符號對應的數值
        Map<Character, Integer> map = new HashMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);

        int total = 0;
        int n = s.length();

        for (int i = 0; i < n; i++) {
            int value = map.get(s.charAt(i));

            // 如果不是最後一個字符，且當前值小於下一個值，需做減法
            if (i < n - 1 && value < map.get(s.charAt(i + 1))) {
                total -= value;
            } else {
                total += value;
            }
        }
        return total;
    }
}

/*
解題思路：
1. 使用 HashMap 存放符號與數值對應關係。
2. 從左到右掃描字串，若當前值小於下一值，表示為減法情況，累加時取負。
3. 否則直接加上該值。
4. 時間複雜度 O(n)，空間複雜度 O(1)。
*/


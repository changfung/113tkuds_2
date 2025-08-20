import java.util.*;

public class M05_GCD_LCM_Recursive {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        long a = sc.nextLong();
        long b = sc.nextLong();

        long g = gcd(a, b);
        long l = (a / g) * b;  // 先除後乘，避免溢位

        System.out.println("GCD: " + g);
        System.out.println("LCM: " + l);
    }

    /**
     * 遞迴歐幾里得算法
     */
    static long gcd(long x, long y) {
        if (y == 0) return x;
        return gcd(y, x % y);
    }
}

/*
 * Time Complexity: O(log min(a, b))
 * 說明：
 * 1. 歐幾里得演算法每次遞迴都縮小數值，步數與 log(min(a,b)) 成正比。
 * 2. LCM 計算僅需一次除法與乘法，O(1)。
 * 3. 總時間複雜度：O(log min(a,b))。
 */


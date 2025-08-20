import java.util.*;

public class M04_TieredTaxSimple {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        long totalTax = 0;

        for (int i = 0; i < n; i++) {
            long income = sc.nextLong();
            long tax = calcTax(income);
            totalTax += tax;
            System.out.println("Tax: " + tax);
        }

        long avg = totalTax / n;
        System.out.println("Average: " + avg);
    }

    /**
     * 計算稅額（逐段級距）
     */
    static long calcTax(long income) {
        long tax = 0;

        if (income <= 120000) {
            tax = Math.round(income * 0.05);
        } else if (income <= 500000) {
            tax = Math.round(120000 * 0.05 + (income - 120000) * 0.12);
        } else if (income <= 1000000) {
            tax = Math.round(120000 * 0.05
                           + (500000 - 120000) * 0.12
                           + (income - 500000) * 0.20);
        } else {
            tax = Math.round(120000 * 0.05
                           + (500000 - 120000) * 0.12
                           + (1000000 - 500000) * 0.20
                           + (income - 1000000) * 0.30);
        }

        return tax;
    }
}

/*
 * Time Complexity: O(n)
 * 說明：
 * 1. 每筆收入只需常數時間 O(1) 計算稅額。
 * 2. 共 n 筆收入 → O(n)。
 * 3. 空間複雜度 O(1)，只需保存累計總稅額與暫存變數。
 */


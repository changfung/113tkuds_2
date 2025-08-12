import java.util.*;

public class StockMaximizer {

    // 主函式：找出最多 K 次交易的最大利潤
    public static int maxProfit(int[] prices, int k) {
        if (prices == null || prices.length < 2 || k <= 0) return 0;

        // 儲存所有可獲利的交易區間的利潤（使用 Max Heap）
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

        int n = prices.length;
        int buy = 0;

        while (buy < n - 1) {
            // 找到當前的谷底（買點）
            while (buy < n - 1 && prices[buy] >= prices[buy + 1]) {
                buy++;
            }

            if (buy == n - 1) break;

            int sell = buy + 1;

            // 找到當前的波峰（賣點）
            while (sell < n && prices[sell] >= prices[sell - 1]) {
                sell++;
            }

            int profit = prices[sell - 1] - prices[buy];
            if (profit > 0) {
                maxHeap.offer(profit);
            }

            buy = sell; // 下一段
        }

        int totalProfit = 0;
        while (k > 0 && !maxHeap.isEmpty()) {
            totalProfit += maxHeap.poll();
            k--;
        }

        return totalProfit;
    }

    // 測試用主程式
    public static void main(String[] args) {
        test(new int[]{2, 4, 1}, 2, 2);
        test(new int[]{3, 2, 6, 5, 0, 3}, 2, 7);
        test(new int[]{1, 2, 3, 4, 5}, 2, 4);
        test(new int[]{5, 4, 3, 2, 1}, 3, 0); // 無利潤可賺
        test(new int[]{1, 3, 2, 8, 4, 9}, 2, 12); // 多段上升
    }

    private static void test(int[] prices, int k, int expected) {
        int result = maxProfit(prices, k);
        System.out.println("價格: " + Arrays.toString(prices) + ", K = " + k);
        System.out.println("最大利潤: " + result + ", 預期: " + expected);
        System.out.println(result == expected ? "✅ 通過" : "❌ 錯誤");
        System.out.println("------");
    }
}


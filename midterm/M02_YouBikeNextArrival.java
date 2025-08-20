import java.util.*;

public class M02_YouBikeNextArrival {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        sc.nextLine(); // consume newline

        int[] times = new int[n];
        String[] raw = new String[n];

        for (int i = 0; i < n; i++) {
            raw[i] = sc.nextLine().trim();
            times[i] = toMinutes(raw[i]);
        }

        String queryStr = sc.nextLine().trim();
        int query = toMinutes(queryStr);

        int idx = upperBound(times, query);
        if (idx == n) {
            System.out.println("No bike");
        } else {
            System.out.println(raw[idx]);
        }
    }

    /** 時間字串轉換成從 00:00 起的分鐘數 */
    static int toMinutes(String t) {
        String[] parts = t.split(":");
        int h = Integer.parseInt(parts[0]);
        int m = Integer.parseInt(parts[1]);
        return h * 60 + m;
    }

    /** 找到第一個大於 target 的位置（二分搜尋 upper bound） */
    static int upperBound(int[] arr, int target) {
        int lo = 0, hi = arr.length;
        while (lo < hi) {
            int mid = (lo + hi) / 2;
            if (arr[mid] <= target) {
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }
        return lo;
    }
}


import java.util.*;

public class LC04_Median_QuakeFeeds {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 讀取輸入
        int n = sc.nextInt();
        int m = sc.nextInt();

        double[] A = new double[n];
        double[] B = new double[m];

        for (int i = 0; i < n; i++) {
            A[i] = sc.nextDouble();
        }
        for (int j = 0; j < m; j++) {
            B[j] = sc.nextDouble();
        }

        double result = findMedianSortedArrays(A, B);

        // 輸出保留 1 位小數
        System.out.printf("%.1f\n", result);

        sc.close();
    }

    /**
     * 求兩個排序陣列的中位數
     */
    public static double findMedianSortedArrays(double[] nums1, double[] nums2) {
        // 確保 nums1 是較短的陣列
        if (nums1.length > nums2.length) {
            return findMedianSortedArrays(nums2, nums1);
        }

        int n = nums1.length;
        int m = nums2.length;
        int totalLeft = (n + m + 1) / 2;  // 左半邊總長度

        int left = 0, right = n;
        while (left <= right) {
            int i = (left + right) / 2;   // 切 nums1 左半長度
            int j = totalLeft - i;        // nums2 左半長度

            // 處理邊界
            double nums1LeftMax  = (i == 0) ? Double.NEGATIVE_INFINITY : nums1[i - 1];
            double nums1RightMin = (i == n) ? Double.POSITIVE_INFINITY : nums1[i];
            double nums2LeftMax  = (j == 0) ? Double.NEGATIVE_INFINITY : nums2[j - 1];
            double nums2RightMin = (j == m) ? Double.POSITIVE_INFINITY : nums2[j];

            if (nums1LeftMax <= nums2RightMin && nums2LeftMax <= nums1RightMin) {
                // 找到正確切割
                if ((n + m) % 2 == 1) {
                    return Math.max(nums1LeftMax, nums2LeftMax);
                } else {
                    return (Math.max(nums1LeftMax, nums2LeftMax) +
                            Math.min(nums1RightMin, nums2RightMin)) / 2.0;
                }
            } else if (nums1LeftMax > nums2RightMin) {
                // i 太大，往左縮
                right = i - 1;
            } else {
                // i 太小，往右擴
                left = i + 1;
            }
        }

        throw new IllegalArgumentException("Input arrays are not sorted properly.");
    }
}

/*
解題思路：

1. 問題要求：
   - 給兩個已排序的陣列 A, B（長度可不同，允許一個為空），求它們的「聯合集中位數」。
   - 若總長度為奇數 → 中位數為中間元素。
   - 若總長度為偶數 → 中位數為中間兩元素平均。

2. 方法（經典二分切割法）：
   - 假設 A 為較短的陣列。
   - 我們嘗試在 A 的索引 i 做切割，對應 B 的切割點 j = (n+m+1)/2 - i。
   - 左半部分：A[0..i-1] 和 B[0..j-1]。
   - 右半部分：A[i..] 和 B[j..]。
   - 條件：左半最大 <= 右半最小。
   - 若條件成立，則：
     - (n+m) 為奇數 → 中位數 = 左半最大。
     - (n+m) 為偶數 → 中位數 = (左半最大 + 右半最小) / 2。

3. 特殊情況處理：
   - i=0 → A 左半空，左半最大視為 -∞。
   - i=n → A 右半空，右半最小視為 +∞。
   - 同理處理 j=0 或 j=m。

4. 範例：
   輸入：
   A = [1.1, 2.0], B = [1.5, 3.2, 4.0]
   總長度 = 5 → 取第 3 個元素
   正確切割後，左半 = [1.1, 1.5, 2.0], 右半 = [3.2, 4.0]
   中位數 = 2.0

5. 複雜度：
   - 時間：O(log(min(n,m)))，因為二分搜尋只在較短陣列上操作。
   - 空間：O(1)。

6. 邊界案例：
   - n=0, m>0 → 中位數即為另一陣列的中位數。
   - n=m=1 → 取平均。
   - 值全部相同 → 還是能正確輸出。
   - 長度落差極大，例如 (1, 1e5) → 仍正確。
*/

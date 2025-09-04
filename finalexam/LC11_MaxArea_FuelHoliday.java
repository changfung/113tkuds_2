import java.util.*;

public class LC11_MaxArea_FuelHoliday {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 讀取輸入
        int n = sc.nextInt();
        int[] heights = new int[n];
        for (int i = 0; i < n; i++) {
            heights[i] = sc.nextInt();
        }

        int result = maxArea(heights);

        // 輸出最大面積
        System.out.println(result);

        sc.close();
    }

    /**
     * 計算最大輸出帶寬 (最大面積)
     */
    public static int maxArea(int[] heights) {
        int left = 0;
        int right = heights.length - 1;
        int maxArea = 0;

        while (left < right) {
            int width = right - left;
            int height = Math.min(heights[left], heights[right]);
            maxArea = Math.max(maxArea, width * height);

            // 移動較短邊
            if (heights[left] < heights[right]) {
                left++;
            } else {
                right--;
            }
        }

        return maxArea;
    }
}

/*
解題思路：

1. 問題要求：
   - 給一個陣列 heights，每個元素代表油槽可用高度。
   - 選兩個索引 i < j，使 (j-i)*min(heights[i], heights[j]) 最大。

2. 方法：雙指針夾逼
   - 初始化 left = 0, right = n-1。
   - 計算面積 = (right-left)*min(heights[left], heights[right])。
   - 更新 maxArea。
   - 移動較短邊：
     - 因為增加距離會減少短邊限制，高邊移動不會增大面積。
     - 只有短邊移動可能遇到更高的高度 → 面積可能變大。
   - 重複直到 left >= right。

3. 範例：
   heights = [1,8,6,2,5,4,8,3,7]
   - 初始 left=0, right=8 → 面積=1*8=8
   - heights[left]<heights[right] → left++
   - left=1, right=8 → 面積=7*7=49 → 更新 maxArea=49
   - 依此類推，最終最大面積=49

4. 複雜度：
   - 時間：O(n)，每個指標最多移動 n 次。
   - 空間：O(1)，只使用兩個指標和變數。

5. 邊界情況：
   - n=2 → 唯一可能答案 (0,1)。
   - 全相等高度 → 最大距離兩端。
   - 單調遞增/遞減 → 兩端最大距離。
   - 高度為 0 → 面積計算自動為 0，無需額外處理。
*/

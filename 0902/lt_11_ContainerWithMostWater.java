// 題目：Container With Most Water
// 給定一個整數陣列 height，陣列元素代表垂直線的高度。
// 找出能盛最多水的兩條線，並回傳最大容積。

class Solution {
    public int maxArea(int[] height) {
        int left = 0;                      // 左指針
        int right = height.length - 1;     // 右指針
        int maxArea = 0;                   // 最大容積

        while (left < right) {
            // 計算當前容積
            int width = right - left;
            int minHeight = Math.min(height[left], height[right]);
            int area = width * minHeight;

            // 更新最大容積
            if (area > maxArea) {
                maxArea = area;
            }

            // 移動較短的線，嘗試找到更高的高度
            if (height[left] < height[right]) {
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
1. 容積公式：Area = (right - left) * min(height[left], height[right])
2. 指針從最外側往內移動，因為最寬距離在最外側。
3. 移動高度較小的指針，嘗試找到更高的線以增加容積。
4. 時間複雜度：O(n)；空間複雜度：O(1)
*/

import java.util.*;

class Solution {
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);  // 排序，方便雙指針搜尋
        int n = nums.length;
        int closestSum = nums[0] + nums[1] + nums[2]; // 初始化答案

        for (int i = 0; i < n - 2; i++) {
            int left = i + 1, right = n - 1;

            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];

                // 如果這個 sum 更接近 target，更新答案
                if (Math.abs(sum - target) < Math.abs(closestSum - target)) {
                    closestSum = sum;
                }

                if (sum < target) {
                    left++;   // 總和太小，需要更大
                } else if (sum > target) {
                    right--;  // 總和太大，需要更小
                } else {
                    return sum; // sum == target，最佳答案
                }
            }
        }
        return closestSum;
    }
}

/*
解題思路：
1. 排序陣列，然後固定一個數字，對剩下兩個數字用雙指針搜尋。
2. 每次更新最接近 target 的總和。
3. 若 sum == target，立即回傳，因為已經是最佳答案。
4. 時間複雜度 O(n^2)，空間複雜度 O(1)。
*/


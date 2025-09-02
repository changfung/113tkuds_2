import java.util.*;

class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);  // 先排序，方便去重與雙指針搜尋

        for (int i = 0; i < nums.length - 2; i++) {
            // 跳過重複的起始數字
            if (i > 0 && nums[i] == nums[i - 1]) continue;

            int left = i + 1;
            int right = nums.length - 1;

            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];

                if (sum == 0) {
                    res.add(Arrays.asList(nums[i], nums[left], nums[right]));

                    // 跳過重複的左值
                    while (left < right && nums[left] == nums[left + 1]) left++;
                    // 跳過重複的右值
                    while (left < right && nums[right] == nums[right - 1]) right--;

                    left++;
                    right--;
                } else if (sum < 0) {
                    left++;  // 需要更大的數
                } else {
                    right--; // 需要更小的數
                }
            }
        }
        return res;
    }
}

/*
解題思路：
1. 先排序陣列，方便雙指針搜尋與去重。
2. 對每個 i，使用 left 和 right 指針尋找組合。
3. 跳過重複值以避免重複三元組。
4. 時間複雜度 O(n^2)，空間複雜度 O(1) (結果除外)。
*/


class Solution {
    public int[] searchRange(int[] nums, int target) {
        return new int[]{findBound(nums, target, true), findBound(nums, target, false)};
    }

    private int findBound(int[] nums, int target, boolean isFirst) {
        int l = 0, r = nums.length - 1, ans = -1;
        while (l <= r) {
            int mid = (l + r) / 2;
            if (nums[mid] == target) {
                ans = mid;
                if (isFirst) r = mid - 1;
                else l = mid + 1;
            } else if (nums[mid] < target) l = mid + 1;
            else r = mid - 1;
        }
        return ans;
    }
}

/*
解題思路：
1. 用二分搜尋兩次：
   - 第一次找 target 的最左邊界。
   - 第二次找 target 的最右邊界。
2. 時間複雜度 O(log n)，比線性搜尋更高效。
*/

class Solution {
    public int search(int[] nums, int target) {
        int l = 0, r = nums.length - 1;
        while (l <= r) {
            int mid = (l + r) / 2;
            if (nums[mid] == target) return mid;
            if (nums[l] <= nums[mid]) {
                if (nums[l] <= target && target < nums[mid]) r = mid - 1;
                else l = mid + 1;
            } else {
                if (nums[mid] < target && target <= nums[r]) l = mid + 1;
                else r = mid - 1;
            }
        }
        return -1;
    }
}

/*
解題思路：
1. 這是二分搜尋的變形，數組是旋轉過的有序陣列。
2. 每次判斷左半邊是否有序：
   - 若有序，檢查 target 是否落在這一段。
   - 否則去右半邊。
3. 持續縮小範圍直到找到 target 或返回 -1。
*/

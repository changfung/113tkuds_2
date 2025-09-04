class Solution {
    public int searchInsert(int[] nums, int target) {
        int l = 0, r = nums.length - 1;
        while (l <= r) {
            int mid = (l + r) / 2;
            if (nums[mid] == target) return mid;
            else if (nums[mid] < target) l = mid + 1;
            else r = mid - 1;
        }
        return l;
    }
}

/*
解題思路：
1. 標準二分搜尋，若找到 target 則回傳索引。
2. 若沒找到，返回 l（此時是 target 應插入的位置）。
*/

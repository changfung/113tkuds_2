class Solution {
    public void nextPermutation(int[] nums) {
        int i = nums.length - 2;
        while (i >= 0 && nums[i] >= nums[i + 1]) i--;
        if (i >= 0) {
            int j = nums.length - 1;
            while (nums[j] <= nums[i]) j--;
            swap(nums, i, j);
        }
        reverse(nums, i + 1, nums.length - 1);
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    private void reverse(int[] nums, int l, int r) {
        while (l < r) swap(nums, l++, r--);
    }
}

/*
解題思路：
1. 從右往左找到第一個遞減的位置 i（nums[i] < nums[i+1]）。
2. 再從右往左找到第一個比 nums[i] 大的數字 j，交換 nums[i] 和 nums[j]。
3. 最後將 i 之後的序列反轉，使其成為最小遞增序列。
*/

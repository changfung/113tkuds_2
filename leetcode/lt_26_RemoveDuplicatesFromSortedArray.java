class Solution {
    public int removeDuplicates(int[] nums) {
        if (nums.length == 0) return 0;
        int i = 0; // 慢指針
        for (int j = 1; j < nums.length; j++) {
            if (nums[j] != nums[i]) {
                i++;
                nums[i] = nums[j];
            }
        }
        return i + 1;
    }
}

/*
解題思路：
1. 使用雙指針：慢指針 i 指向已處理最後元素，快指針 j 遍歷陣列。
2. 當 nums[j] != nums[i] 時，更新 nums[i+1] = nums[j]。
3. 返回新長度 i+1。
時間：O(n)，空間：O(1)
*/

class Solution {
    public int removeElement(int[] nums, int val) {
        int i = 0; // 慢指針
        for (int j = 0; j < nums.length; j++) {
            if (nums[j] != val) {
                nums[i] = nums[j];
                i++;
            }
        }
        return i;
    }
}

/*
解題思路：
1. 使用雙指針：慢指針 i 指向新陣列位置，快指針 j 遍歷原陣列。
2. 將不等於 val 的元素搬到前面。
時間：O(n)，空間：O(1)
*/

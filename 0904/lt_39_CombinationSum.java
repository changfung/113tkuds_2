import java.util.*;

class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        backtrack(candidates, target, 0, new ArrayList<>(), res);
        return res;
    }

    private void backtrack(int[] nums, int remain, int start, List<Integer> path, List<List<Integer>> res) {
        if (remain == 0) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = start; i < nums.length; i++) {
            if (remain - nums[i] >= 0) {
                path.add(nums[i]);
                backtrack(nums, remain - nums[i], i, path, res);
                path.remove(path.size() - 1);
            }
        }
    }
}

/*
解題思路：
1. 使用回溯法枚舉所有可能的組合。
2. 每個數字可以重複選擇，所以遞歸時傳入 i（不是 i+1）。
3. 當剩餘值 == 0 時，收集當前組合。
*/

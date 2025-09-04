import java.util.*;

class Solution {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
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
            if (i > start && nums[i] == nums[i - 1]) continue; // 去重
            if (remain - nums[i] < 0) break;
            path.add(nums[i]);
            backtrack(nums, remain - nums[i], i + 1, path, res);
            path.remove(path.size() - 1);
        }
    }
}

/*
解題思路：
1. 與 Combination Sum 類似，但每個數字只能使用一次。
2. 為了避免重複，先排序，然後若當前數字與前一個相同且在同一層，則跳過。
3. 遞歸時傳 i+1（不可重複選）。
*/

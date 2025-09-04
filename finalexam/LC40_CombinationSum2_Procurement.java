import java.util.*;

public class LC40_CombinationSum2_Procurement {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int target = sc.nextInt();

        int[] candidates = new int[n];
        for (int i = 0; i < n; i++) candidates[i] = sc.nextInt();

        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(candidates); // 先排序方便去重與剪枝

        backtrack(candidates, target, 0, new ArrayList<>(), res);

        for (List<Integer> comb : res) {
            for (int i = 0; i < comb.size(); i++) {
                System.out.print(comb.get(i));
                if (i < comb.size() - 1) System.out.print(" ");
            }
            System.out.println();
        }

        sc.close();
    }

    private static void backtrack(int[] nums, int remain, int start, List<Integer> path, List<List<Integer>> res) {
        if (remain == 0) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = start; i < nums.length; i++) {
            if (i > start && nums[i] == nums[i - 1]) continue; // 同層去重
            if (nums[i] > remain) break; // 剪枝
            path.add(nums[i]);
            backtrack(nums, remain - nums[i], i + 1, path, res); // 下一層傳 i+1
            path.remove(path.size() - 1);
        }
    }
}

/*
解題思路：
1. 排序陣列方便：
   - 剪枝：nums[i] > remain → break
   - 同層去重：i>start 且 nums[i]==nums[i-1] → skip
2. 每個數字僅能用一次 → 下一層傳 i+1
3. 回溯樹：
   - 節點為當前選擇
   - 邊為加入某數字
   - 剩餘值為 0 → 收錄組合
   - 剩餘值 < 0 → 回溯
4. 複雜度：
   - 指數級，n <=30 可接受
5. 範例：
   candidates=[10,1,2,7,6,1,5], target=8
   → 組合：
     1 1 6
     1 2 5
     1 7
     2 6
*/

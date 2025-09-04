import java.util.*;

public class LC39_CombinationSum_PPE {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int target = sc.nextInt();

        int[] candidates = new int[n];
        for (int i = 0; i < n; i++) candidates[i] = sc.nextInt();

        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(candidates); // 先排序方便剪枝

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
            if (nums[i] > remain) break; // 剪枝
            path.add(nums[i]);
            backtrack(nums, remain - nums[i], i, path, res); // 同一元素可重複
            path.remove(path.size() - 1);
        }
    }
}

/*
解題思路：
1. 回溯樹：節點代表當前選擇，邊為加入某個數字。
2. 當剩餘值為 0 → 收錄 path。
3. 當剩餘值 < 0 → 回溯。
4. I 版可以重複使用同一數字 → 下一層仍傳 i。
5. 排序陣列方便剪枝：nums[i] > remain → break。
6. 複雜度：指數級，但 n <=30 可接受。
*/

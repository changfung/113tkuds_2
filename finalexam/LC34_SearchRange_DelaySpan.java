import java.util.*;

public class LC34_SearchRange_DelaySpan {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int target = sc.nextInt();

        int[] nums = new int[n];
        for (int i = 0; i < n; i++) nums[i] = sc.nextInt();

        int[] range = searchRange(nums, target);
        System.out.println(range[0] + " " + range[1]);

        sc.close();
    }

    /**
     * 找出 target 在排序陣列的首尾索引
     */
    public static int[] searchRange(int[] nums, int target) {
        int left = lowerBound(nums, target);
        if (left == nums.length || nums[left] != target) return new int[]{-1, -1};

        int right = lowerBound(nums, target + 1) - 1;
        return new int[]{left, right};
    }

    /**
     * 找出第一個 >= target 的索引
     */
    public static int lowerBound(int[] nums, int target) {
        int l = 0, r = nums.length; // 注意 r = nums.length
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (nums[mid] < target) l = mid + 1;
            else r = mid;
        }
        return l;
    }
}

/*
解題思路：

1. 問題要求：
   - 已排序陣列 nums
   - 找出 target 出現的首尾索引
   - 若不存在 → -1 -1

2. 方法：
   - 使用 lower_bound 二分找第一個 >= target 的位置 → left
   - 若 nums[left] != target → 不存在
   - 右界可用 lower_bound(target+1)-1 → right

   另一種方法：
   - 寫兩個專用二分：
       - 找左界：第一個等於 target
       - 找右界：最後一個等於 target

3. 範例：
   nums=[5,7,7,8,8,10], target=8
   - left = lowerBound(8) = 3
   - right = lowerBound(9)-1 = 5-1 = 4
   → 返回 [3,4]

4. 複雜度：
   - 時間 O(log n)
   - 空間 O(1)

5. 邊界情況：
   - n=0 → [-1,-1]
   - 所有元素皆 target → [0, n-1]
   - target 只出現一次 → [index,index]
   - target 不存在 → [-1,-1]
*/

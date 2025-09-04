import java.util.*;

public class LC33_SearchRotated_RentHot {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int target = sc.nextInt();

        int[] nums = new int[n];
        for (int i = 0; i < n; i++) nums[i] = sc.nextInt();

        int index = search(nums, target);
        System.out.println(index);

        sc.close();
    }

    /**
     * 在旋轉升序陣列中查找 target
     */
    public static int search(int[] nums, int target) {
        int l = 0, r = nums.length - 1;

        while (l <= r) {
            int mid = l + (r - l) / 2;

            if (nums[mid] == target) return mid;

            // 左半段有序
            if (nums[l] <= nums[mid]) {
                if (nums[l] <= target && target < nums[mid]) {
                    r = mid - 1; // 在左半段
                } else {
                    l = mid + 1; // 在右半段
                }
            } 
            // 右半段有序
            else {
                if (nums[mid] < target && target <= nums[r]) {
                    l = mid + 1; // 在右半段
                } else {
                    r = mid - 1; // 在左半段
                }
            }
        }

        return -1;
    }
}

/*
解題思路：

1. 問題要求：
   - 在旋轉後的升序陣列中查找 target
   - 找到回傳索引，找不到回 -1

2. 方法：
   - 二分查找，但需判斷哪半區有序
   - 每次計算 mid：
       - 如果 nums[mid] == target → 返回 mid
       - 如果 nums[l] <= nums[mid] → 左半段有序
           - 若 target 在左半段範圍 → r = mid - 1
           - 否則 l = mid + 1
       - 否則右半段有序
           - 若 target 在右半段範圍 → l = mid + 1
           - 否則 r = mid - 1

3. 範例：
   nums = [4,5,6,7,0,1,2], target=0
   - l=0, r=6, mid=3 → nums[3]=7
   - 左半段 4..7 有序，target=0 不在左半段 → l=4
   - l=4, r=6, mid=5 → nums[5]=1
   - 左半段 0..1 有序，target=0 在左半段 → r=4
   - mid=4 → nums[4]=0 → 返回 4

4. 複雜度：
   - 時間 O(log n)
   - 空間 O(1)

5. 邊界情況：
   - 未旋轉（純升序） → 標準二分
   - 單元素 → 匹配 / 不匹配
   - target 在旋轉分界附近 → 正確縮小區間
   - target 不存在 → 返回 -1
*/

import java.util.*;

public class LC15_3Sum_THSRStops {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }

        List<List<Integer>> result = threeSum(nums);

        // 輸出每一組三元組
        for (List<Integer> triplet : result) {
            System.out.println(triplet.get(0) + " " + triplet.get(1) + " " + triplet.get(2));
        }

        sc.close();
    }

    /**
     * 找出所有三元組和為 0，且無重複
     */
    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums); // 先排序

        for (int i = 0; i < nums.length - 2; i++) {
            // 提前結束
            if (nums[i] > 0) break;

            // 跳過重複
            if (i > 0 && nums[i] == nums[i - 1]) continue;

            int left = i + 1;
            int right = nums.length - 1;

            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (sum == 0) {
                    res.add(Arrays.asList(nums[i], nums[left], nums[right]));

                    // 跳過重複
                    while (left < right && nums[left] == nums[left + 1]) left++;
                    while (left < right && nums[right] == nums[right - 1]) right--;

                    left++;
                    right--;
                } else if (sum < 0) {
                    left++;
                } else {
                    right--;
                }
            }
        }

        return res;
    }
}

/*
解題思路：

1. 問題要求：
   - 給整數陣列 nums，找出所有不重複的三元組 (a,b,c)，使 a+b+c=0。
   - 輸出每組遞增排列，無重複組合。

2. 方法：
   - 先排序 nums[]。
   - 固定第一個元素 nums[i]。
     - 若 nums[i] > 0 → 後面皆為正數 → 不可能再有和為 0 → break。
     - 跳過重複 nums[i]。
   - 對剩餘部分使用雙指針 left=i+1, right=nums.length-1：
     - 計算 sum = nums[i]+nums[left]+nums[right]
     - 若 sum==0 → 加入答案，並跳過重複 left/right
     - 若 sum<0 → left++
     - 若 sum>0 → right--

3. 範例：
   nums = [-1,0,1,2,-1,-4] → 排序 [-4,-1,-1,0,1,2]
   - i=0 → nums[i]=-4 → sum>0?否 → left=1,right=5 → sum=-4+-1+2=-3 → left++
   - i=1 → nums[i]=-1 → left=2,right=5 → sum=-1+-1+2=0 → 存 [-1,-1,2]
             left=3,right=4 → sum=-1+0+1=0 → 存 [-1,0,1]
   - i=2 → nums[i]=-1 重複 → 跳過
   → 最終答案：[[-1,-1,2],[-1,0,1]]

4. 複雜度：
   - 時間：O(n²)，固定 i 後雙指針掃描。
   - 空間：O(n) 或 O(1) 視輸出結果計算。

5. 邊界情況：
   - n<3 → 無解（題目保證 n>=3）
   - 全部 0 → 只輸出 [0,0,0]
   - 重複元素 → 雙指針略過重複以去重
*/

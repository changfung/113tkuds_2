import java.util.*;

public class LC18_4Sum_Procurement {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        long target = sc.nextLong();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }

        List<List<Integer>> result = fourSum(nums, target);

        // 輸出每一組四元組
        for (List<Integer> quad : result) {
            System.out.println(quad.get(0) + " " + quad.get(1) + " " + quad.get(2) + " " + quad.get(3));
        }

        sc.close();
    }

    /**
     * 找出所有不重複四元組和為 target
     */
    public static List<List<Integer>> fourSum(int[] nums, long target) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);

        int n = nums.length;

        for (int i = 0; i < n - 3; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue; // 去重 i

            for (int j = i + 1; j < n - 2; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1]) continue; // 去重 j

                int left = j + 1;
                int right = n - 1;

                while (left < right) {
                    long sum = (long)nums[i] + nums[j] + nums[left] + nums[right];
                    if (sum == target) {
                        res.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));

                        // 跳過重複 left/right
                        while (left < right && nums[left] == nums[left + 1]) left++;
                        while (left < right && nums[right] == nums[right - 1]) right--;

                        left++;
                        right--;
                    } else if (sum < target) {
                        left++;
                    } else {
                        right--;
                    }
                }
            }
        }

        return res;
    }
}

/*
解題思路：

1. 問題要求：
   - 給一個整數陣列 nums，找出所有不重複四元組 (a,b,c,d)，使 a+b+c+d = target。
   - 輸出每組遞增排序，無重複組合。

2. 方法：
   - 先排序 nums。
   - 固定第一個元素 i。
     - 跳過重複 i。
   - 固定第二個元素 j (j>i)。
     - 跳過重複 j。
   - 對剩餘部分使用雙指針 left=j+1, right=n-1：
     - 計算 sum = nums[i]+nums[j]+nums[left]+nums[right]
     - sum==target → 加入結果，跳過重複 left/right
     - sum<target → left++
     - sum>target → right--

3. 範例：
   nums = [1,0,-1,0,-2,2], target=0 → 排序 [-2,-1,0,0,1,2]
   - i=-2,j=-1 → left=2,right=5 → sum=-2-1+0+2=-1 → left++
   - left=3,right=5 → sum=-2-1+0+2=-1 → left++
   - left=4,right=5 → sum=-2-1+1+2=0 → 存 [-2,-1,1,2]
   - 同理處理其他組合
   → 最終答案：[-2,-1,1,2], [-2,0,0,2], [-1,0,0,1]

4. 複雜度：
   - 時間：O(n³)
   - 空間：O(n) 或 O(1) 視輸出結果計算

5. 邊界情況：
   - n=4 → 唯一組合可能
   - 全 0，target=0 → 輸出 [0,0,0,0]
   - 重複值 → 跳過重複 i,j,left,right 保證去重
   - 無解 → 輸出空列表
*/


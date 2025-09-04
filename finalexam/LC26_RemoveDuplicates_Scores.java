import java.util.*;

public class LC26_RemoveDuplicates_Scores {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) nums[i] = sc.nextInt();

        int newLen = removeDuplicates(nums);

        // 輸出新長度
        System.out.println(newLen);

        // 輸出前段內容
        for (int i = 0; i < newLen; i++) {
            System.out.print(nums[i]);
            if (i < newLen - 1) System.out.print(" ");
        }
        System.out.println();

        sc.close();
    }

    /**
     * 原地去重排序陣列，返回新長度
     */
    public static int removeDuplicates(int[] nums) {
        if (nums.length == 0) return 0;

        int write = 1; // 下一個可寫位置
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[write - 1]) {
                nums[write] = nums[i];
                write++;
            }
        }
        return write;
    }
}

/*
解題思路：

1. 問題要求：
   - 已排序陣列 nums
   - 去除重複，使每個元素只出現一次
   - 返回新長度，前段陣列存放結果
   - O(1) 額外空間

2. 方法：
   - 使用 write 指針指向可寫位置
   - 從 i=1 開始遍歷：
       - 若 nums[i] != nums[write-1] → 寫入 nums[write]，write++
       - 否則跳過
   - 遍歷完成後，write 即為新長度

3. 範例：
   nums = [0,0,1,1,1,2,2]
   - write=1
   - i=1, nums[1]=0 → 與 nums[0]=0 相同 → skip
   - i=2, nums[2]=1 → != nums[0]=0 → nums[1]=1, write=2
   - i=3, nums[3]=1 → == nums[1]=1 → skip
   - i=4, nums[4]=1 → skip
   - i=5, nums[5]=2 → != nums[1]=1 → nums[2]=2, write=3
   - i=6, nums[6]=2 → == nums[2]=2 → skip
   → 新長度=3, nums前段=[0,1,2]

4. 複雜度：
   - 時間 O(n)
   - 空間 O(1)

5. 邊界情況：
   - n=0 → 返回 0
   - 全部唯一 → write 每次增加，保持不變
   - 全部相同 → 只保留第一個
   - 交替重複 → 正確壓縮
*/

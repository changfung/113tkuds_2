import java.util.*;

public class LC27_RemoveElement_Recycle {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int val = sc.nextInt();

        int[] nums = new int[n];
        for (int i = 0; i < n; i++) nums[i] = sc.nextInt();

        int newLen = removeElement(nums, val);

        // 輸出新長度
        System.out.println(newLen);

        // 輸出前段結果
        for (int i = 0; i < newLen; i++) {
            System.out.print(nums[i]);
            if (i < newLen - 1) System.out.print(" ");
        }
        System.out.println();

        sc.close();
    }

    /**
     * 原地移除指定元素 val
     */
    public static int removeElement(int[] nums, int val) {
        int write = 0; // 下一個可寫位置
        for (int x : nums) {
            if (x != val) {
                nums[write++] = x;
            }
        }
        return write;
    }
}

/*
解題思路：

1. 問題要求：
   - 在陣列 nums 中移除所有等於 val 的元素
   - 返回新長度
   - 保留剩餘元素原始順序
   - O(1) 額外空間

2. 方法：
   - 使用 write 指針指向可寫位置
   - 遍歷每個元素 x：
       - 若 x != val → 寫入 nums[write]，write++
       - 否則跳過
   - 遍歷完成後，write 為新長度，nums 前段為結果

3. 範例：
   nums = [3,2,2,3], val=2
   - write=0
   - x=3 → !=2 → nums[0]=3, write=1
   - x=2 → ==2 → skip
   - x=2 → skip
   - x=3 → !=2 → nums[1]=3, write=2
   → 新長度=2, 前段=[3,3]

4. 複雜度：
   - 時間 O(n)
   - 空間 O(1)

5. 邊界情況：
   - n=0 → 返回 0
   - 全部為 val → 新長度 0
   - 沒有 val → 新長度保持 n
   - val 在開頭/結尾/中間 → 正確覆寫
*/

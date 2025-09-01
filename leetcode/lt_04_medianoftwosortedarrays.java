public class lt_04_medianoftwosortedarrays {
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length, n = nums2.length;
        int[] merged = new int[m + n];
        int i = 0, j = 0, k = 0;

        // 合併兩個已排序陣列
        while (i < m && j < n) {
            if (nums1[i] <= nums2[j]) {
                merged[k++] = nums1[i++];
            } else {
                merged[k++] = nums2[j++];
            }
        }
        while (i < m) merged[k++] = nums1[i++];
        while (j < n) merged[k++] = nums2[j++];

        // 計算中位數
        int len = merged.length;
        if (len % 2 == 0) {
            return (merged[len/2 - 1] + merged[len/2]) / 2.0;
        } else {
            return merged[len/2];
        }
    }

    public static void main(String[] args) {
        int[] nums1 = {1,3};
        int[] nums2 = {2};
        System.out.println(findMedianSortedArrays(nums1, nums2)); // 2.0

        int[] nums3 = {1,2};
        int[] nums4 = {3,4};
        System.out.println(findMedianSortedArrays(nums3, nums4)); // 2.5
    }
}

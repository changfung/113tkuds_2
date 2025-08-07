import java.util.Arrays;

public class AdvancedArrayRecursion {
    public static void main(String[] args) {
        int[] arr = {7, 2, 1, 8, 6, 3};
        System.out.println("原始陣列：" + Arrays.toString(arr));

        // 1. 快速排序
        quickSort(arr, 0, arr.length - 1);
        System.out.println("快速排序後：" + Arrays.toString(arr));

        // 2. 合併兩個已排序陣列（遞迴）
        int[] a1 = {1, 3, 5};
        int[] a2 = {2, 4, 6, 7};
        int[] merged = mergeSortedRecursive(a1, a2, 0, 0);
        System.out.println("遞迴合併結果：" + Arrays.toString(merged));

        // 3. 尋找第 k 小元素（QuickSelect）
        int[] arr2 = {10, 4, 5, 8, 6, 11, 26};
        int k = 4;
        int kthSmallest = quickSelect(arr2, 0, arr2.length - 1, k - 1); // 第 k 小是 index k-1
        System.out.println("第 " + k + " 小的元素是：" + kthSmallest);

        // 4. 子序列總和是否等於目標值
        int[] nums = {3, 34, 4, 12, 5, 2};
        int target = 9;
        boolean exists = subsetSum(nums, 0, target);
        System.out.println("是否存在子序列總和為 " + target + "： " + exists);
    }

    // 1. 遞迴快速排序
    public static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high); // 劃分點
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];  // 使用最後一個元素為 pivot
        int i = low - 1; // 小於 pivot 的區間尾端

        for (int j = low; j < high; j++) {
            if (arr[j] < pivot) {
                i++;
                swap(arr, i, j);
            }
        }

        swap(arr, i + 1, high); // 把 pivot 放到中間
        return i + 1;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i]; arr[i] = arr[j]; arr[j] = temp;
    }

    // 2. 遞迴合併已排序陣列
    public static int[] mergeSortedRecursive(int[] a, int[] b, int i, int j) {
        if (i == a.length) {
            return Arrays.copyOfRange(b, j, b.length);
        }
        if (j == b.length) {
            return Arrays.copyOfRange(a, i, a.length);
        }

        if (a[i] < b[j]) {
            int[] rest = mergeSortedRecursive(a, b, i + 1, j);
            return prepend(a[i], rest);
        } else {
            int[] rest = mergeSortedRecursive(a, b, i, j + 1);
            return prepend(b[j], rest);
        }
    }

    private static int[] prepend(int value, int[] arr) {
        int[] result = new int[arr.length + 1];
        result[0] = value;
        System.arraycopy(arr, 0, result, 1, arr.length);
        return result;
    }

    // 3. 遞迴找第 k 小元素（QuickSelect）
    public static int quickSelect(int[] arr, int left, int right, int k) {
        if (left == right) return arr[left];

        int pivotIndex = partition(arr, left, right);

        if (k == pivotIndex) {
            return arr[k];
        } else if (k < pivotIndex) {
            return quickSelect(arr, left, pivotIndex - 1, k);
        } else {
            return quickSelect(arr, pivotIndex + 1, right, k);
        }
    }

    // 4. 遞迴檢查子序列總和是否等於目標值
    public static boolean subsetSum(int[] arr, int index, int target) {
        if (target == 0) return true;
        if (index == arr.length || target < 0) return false;

        // 選 or 不選
        return subsetSum(arr, index + 1, target - arr[index]) ||
               subsetSum(arr, index + 1, target);
    }
}


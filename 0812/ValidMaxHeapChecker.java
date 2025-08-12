public class ValidMaxHeapChecker {

    // 檢查是否為有效的 Max Heap
    public static boolean isValidMaxHeap(int[] arr) {
        int n = arr.length;
        // 最後一個非葉子節點的索引
        for (int i = 0; i <= (n - 2) / 2; i++) {
            int left = 2 * i + 1;
            int right = 2 * i + 2;

            // 如果左子節點存在且比父節點大，回傳 false 並印出錯誤位置
            if (left < n && arr[left] > arr[i]) {
                System.out.println("違反 Max Heap 規則：索引 " + left + " 的值 " + arr[left] + " 大於父節點索引 " + i + " 的值 " + arr[i]);
                return false;
            }

            // 如果右子節點存在且比父節點大，回傳 false 並印出錯誤位置
            if (right < n && arr[right] > arr[i]) {
                System.out.println("違反 Max Heap 規則：索引 " + right + " 的值 " + arr[right] + " 大於父節點索引 " + i + " 的值 " + arr[i]);
                return false;
            }
        }

        return true;
    }

    // 測試主程式
    public static void main(String[] args) {
        int[][] testCases = {
            {100, 90, 80, 70, 60, 75, 65},
            {100, 90, 80, 95, 60, 75, 65},
            {50},
            {}
        };

        for (int i = 0; i < testCases.length; i++) {
            int[] test = testCases[i];
            System.out.print("Test Case " + (i + 1) + ": ");
            boolean isValid = isValidMaxHeap(test);
            System.out.println(isValid ? "是有效的 Max Heap" : "不是有效的 Max Heap");
        }
    }
}

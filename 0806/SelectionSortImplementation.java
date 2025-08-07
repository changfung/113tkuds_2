import java.util.Arrays;

public class SelectionSortImplementation {
    public static void main(String[] args) {
        int[] data1 = {64, 25, 12, 22, 11};
        int[] data2 = Arrays.copyOf(data1, data1.length); // 用於氣泡排序比較

        System.out.println("原始陣列：" + Arrays.toString(data1));

        System.out.println("\n--- 選擇排序 ---");
        selectionSort(data1);

        System.out.println("\n--- 氣泡排序 ---");
        bubbleSort(data2);
    }

    // 選擇排序實作
    public static void selectionSort(int[] array) {
        int n = array.length;
        int compareCount = 0;
        int swapCount = 0;

        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;

            // 尋找最小值索引
            for (int j = i + 1; j < n; j++) {
                compareCount++;
                if (array[j] < array[minIndex]) {
                    minIndex = j;
                }
            }

            // 交換
            if (minIndex != i) {
                int temp = array[i];
                array[i] = array[minIndex];
                array[minIndex] = temp;
                swapCount++;
            }

            System.out.println("第 " + (i + 1) + " 輪排序結果：" + Arrays.toString(array));
        }

        System.out.println("總比較次數：" + compareCount);
        System.out.println("總交換次數：" + swapCount);
    }

    // 氣泡排序實作（用於比較）
    public static void bubbleSort(int[] array) {
        int n = array.length;
        int compareCount = 0;
        int swapCount = 0;
        boolean swapped;

        for (int i = 0; i < n - 1; i++) {
            swapped = false;

            for (int j = 0; j < n - 1 - i; j++) {
                compareCount++;
                if (array[j] > array[j + 1]) {
                    // 交換
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    swapCount++;
                    swapped = true;
                }
            }

            System.out.println("第 " + (i + 1) + " 輪排序結果：" + Arrays.toString(array));

            if (!swapped) break; // 提早結束
        }

        System.out.println("總比較次數：" + compareCount);
        System.out.println("總交換次數：" + swapCount);
    }
}


import java.util.*;

public class NumberArrayProcessor {
    public static void main(String[] args) {
        int[] array1 = {3, 5, 2, 3, 8, 5, 2, 9, 1};
        int[] array2 = {1, 3, 5, 7};
        int[] array3 = {2, 4, 6, 8};

        // 移除重複元素
        System.out.println("原始陣列 (含重複)： " + Arrays.toString(array1));
        int[] uniqueArray = removeDuplicates(array1);
        System.out.println("移除重複後：       " + Arrays.toString(uniqueArray));

        // 合併兩個已排序陣列
        System.out.println("已排序陣列1：" + Arrays.toString(array2));
        System.out.println("已排序陣列2：" + Arrays.toString(array3));
        int[] mergedArray = mergeSortedArrays(array2, array3);
        System.out.println("合併後陣列：" + Arrays.toString(mergedArray));

        // 找出出現頻率最高的元素
        int mostFrequent = findMostFrequent(array1);
        System.out.println("出現頻率最高的元素：" + mostFrequent);

        // 將陣列分成兩個總和近似相等的子陣列
        int[] balancedArray = {1, 2, 3, 4, 5, 6, 7};
        System.out.println("原始陣列： " + Arrays.toString(balancedArray));
        splitArrayIntoBalancedSubarrays(balancedArray);
    }

    // 1. 移除重複元素
    public static int[] removeDuplicates(int[] array) {
        Set<Integer> set = new LinkedHashSet<>();
        for (int num : array) {
            set.add(num);
        }
        int[] result = new int[set.size()];
        int i = 0;
        for (int num : set) {
            result[i++] = num;
        }
        return result;
    }

    // 2. 合併兩個已排序陣列
    public static int[] mergeSortedArrays(int[] a, int[] b) {
        int[] result = new int[a.length + b.length];
        int i = 0, j = 0, k = 0;

        while (i < a.length && j < b.length) {
            if (a[i] <= b[j]) {
                result[k++] = a[i++];
            } else {
                result[k++] = b[j++];
            }
        }

        while (i < a.length) result[k++] = a[i++];
        while (j < b.length) result[k++] = b[j++];

        return result;
    }

    // 3. 找出出現頻率最高的元素
    public static int findMostFrequent(int[] array) {
        Map<Integer, Integer> freqMap = new HashMap<>();
        for (int num : array) {
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
        }

        int maxCount = 0;
        int mostFrequent = array[0];

        for (Map.Entry<Integer, Integer> entry : freqMap.entrySet()) {
            if (entry.getValue() > maxCount) {
                maxCount = entry.getValue();
                mostFrequent = entry.getKey();
            }
        }

        return mostFrequent;
    }

    // 4. 將陣列分成兩個子陣列，總和近似相等
    public static void splitArrayIntoBalancedSubarrays(int[] array) {
        Arrays.sort(array);
        List<Integer> group1 = new ArrayList<>();
        List<Integer> group2 = new ArrayList<>();
        int sum1 = 0, sum2 = 0;

        // 貪婪法：從大到小加入總和較小的子集合
        for (int i = array.length - 1; i >= 0; i--) {
            if (sum1 <= sum2) {
                group1.add(array[i]);
                sum1 += array[i];
            } else {
                group2.add(array[i]);
                sum2 += array[i];
            }
        }

        System.out.println("子陣列 1：" + group1 + "（總和: " + sum1 + "）");
        System.out.println("子陣列 2：" + group2 + "（總和: " + sum2 + "）");
    }
}


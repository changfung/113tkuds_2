import java.util.*;

public class MeetingRoomScheduler {

    // 子問題 1：計算最少需要的會議室數量
    public static int minMeetingRooms(int[][] meetings) {
        if (meetings.length == 0) return 0;

        // 依開始時間排序
        Arrays.sort(meetings, Comparator.comparingInt(a -> a[0]));

        // 最小堆：存會議結束時間
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        for (int[] meeting : meetings) {
            int start = meeting[0];
            int end = meeting[1];

            // 如果最早結束的會議已經結束，重用該會議室
            if (!minHeap.isEmpty() && start >= minHeap.peek()) {
                minHeap.poll();
            }

            minHeap.offer(end); // 新的會議進入
        }

        return minHeap.size(); // heap size 就是同時進行的最大會議數
    }

    // 子問題 2：若只有 N 間會議室，選擇不重疊會議以最大化總時長
    public static int maximizeMeetingTime(int[][] meetings, int roomCount) {
        // 根據會議結束時間排序，為了 Greedy 挑最早結束的會議
        Arrays.sort(meetings, Comparator.comparingInt(a -> a[1]));

        // 儲存每間會議室最後使用時間
        PriorityQueue<Integer> roomEndTimes = new PriorityQueue<>();

        int totalTime = 0;

        for (int[] meeting : meetings) {
            int start = meeting[0];
            int end = meeting[1];

            // 如果有空的會議室（最早結束時間 <= 當前會議開始時間）
            if (!roomEndTimes.isEmpty() && roomEndTimes.peek() <= start) {
                roomEndTimes.poll(); // 重用會議室
            } else if (roomEndTimes.size() >= roomCount) {
                continue; // 所有會議室都被佔用
            }

            // 安排此會議
            roomEndTimes.offer(end);
            totalTime += end - start;
        }

        return totalTime;
    }

    // 測試主程式
    public static void main(String[] args) {
        System.out.println("=== 子問題 1：最少會議室 ===");
        testMinRooms(new int[][]{{0,30},{5,10},{15,20}}, 2);
        testMinRooms(new int[][]{{9,10},{4,9},{4,17}}, 2);
        testMinRooms(new int[][]{{1,5},{8,9},{8,9}}, 2);

        System.out.println("\n=== 子問題 2：最大總會議時間 ===");
        testMaxTime(new int[][]{{1,4},{2,3},{4,6}}, 1, 5); // 選 [1,4] & [4,6]
        testMaxTime(new int[][]{{1,3},{2,5},{4,6}}, 1, 4); // 選 [1,3] & [4,6]
        testMaxTime(new int[][]{{0,2},{1,5},{6,8}}, 2, 7); // 可用兩個會議室
    }

    private static void testMinRooms(int[][] meetings, int expected) {
        int result = minMeetingRooms(meetings);
        System.out.println("會議: " + Arrays.deepToString(meetings));
        System.out.println("需要會議室: " + result + "，預期: " + expected);
        System.out.println(result == expected ? "✅ 通過" : "❌ 錯誤");
        System.out.println("------");
    }

    private static void testMaxTime(int[][] meetings, int roomCount, int expected) {
        int result = maximizeMeetingTime(meetings, roomCount);
        System.out.println("會議: " + Arrays.deepToString(meetings) + ", N = " + roomCount);
        System.out.println("最大總時間: " + result + "，預期: " + expected);
        System.out.println(result == expected ? "✅ 通過" : "❌ 錯誤");
        System.out.println("------");
    }
}


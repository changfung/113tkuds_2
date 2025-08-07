public class GradeStatisticsSystem {
    public static void main(String[] args) {
        int[] scores = {85, 92, 78, 96, 87, 73, 89, 94, 82, 90};

        // 統計變數
        int sum = 0;
        int max = scores[0];
        int min = scores[0];

        // 等第統計
        int countA = 0, countB = 0, countC = 0, countD = 0, countF = 0;

        // 計算總分、最高、最低、等第
        for (int score : scores) {
            sum += score;

            if (score > max) max = score;
            if (score < min) min = score;

            // 分數等第判斷
            if (score >= 90) {
                countA++;
            } else if (score >= 80) {
                countB++;
            } else if (score >= 70) {
                countC++;
            } else if (score >= 60) {
                countD++;
            } else {
                countF++;
            }
        }

        double average = (double) sum / scores.length;

        // 計算高於平均的人數
        int aboveAverageCount = 0;
        for (int score : scores) {
            if (score > average) {
                aboveAverageCount++;
            }
        }

        // 印出報表
        System.out.println("成績報表");
        System.out.println("--------");
        System.out.print("所有成績：");
        for (int score : scores) {
            System.out.print(score + " ");
        }
        System.out.println();
        System.out.printf("平均成績：%.2f\n", average);
        System.out.println("最高分數：" + max);
        System.out.println("最低分數：" + min);
        System.out.println("等第人數：");
        System.out.println("A (90~100)：" + countA);
        System.out.println("B (80~89) ：" + countB);
        System.out.println("C (70~79) ：" + countC);
        System.out.println("D (60~69) ：" + countD);
        System.out.println("F (<60)   ：" + countF);
        System.out.println("高於平均分的學生人數：" + aboveAverageCount);
    }
}


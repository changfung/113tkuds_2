public class MatrixCalculator {
    public static void main(String[] args) {
        int[][] matrixA = {
            {1, 2, 3},
            {4, 5, 6}
        };

        int[][] matrixB = {
            {7, 8, 9},
            {10, 11, 12}
        };

        int[][] matrixC = {
            {1, 2},
            {3, 4},
            {5, 6}
        };

        // 矩陣加法
        System.out.println("矩陣 A + B：");
        int[][] sum = addMatrices(matrixA, matrixB);
        printMatrix(sum);

        // 矩陣乘法 A * C
        System.out.println("矩陣 A * C：");
        int[][] product = multiplyMatrices(matrixA, matrixC);
        printMatrix(product);

        // 矩陣 A 的轉置
        System.out.println("矩陣 A 的轉置：");
        int[][] transpose = transposeMatrix(matrixA);
        printMatrix(transpose);

        // 最大值與最小值（以 A 為例）
        System.out.println("矩陣 A 的最大值與最小值：");
        findMaxMin(matrixA);
    }

    // 矩陣加法
    public static int[][] addMatrices(int[][] a, int[][] b) {
        int rows = a.length;
        int cols = a[0].length;
        int[][] result = new int[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result[i][j] = a[i][j] + b[i][j];
            }
        }

        return result;
    }

    // 矩陣乘法
    public static int[][] multiplyMatrices(int[][] a, int[][] b) {
        int rows = a.length;
        int cols = b[0].length;
        int common = a[0].length;

        int[][] result = new int[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int sum = 0;
                for (int k = 0; k < common; k++) {
                    sum += a[i][k] * b[k][j];
                }
                result[i][j] = sum;
            }
        }

        return result;
    }

    // 矩陣轉置
    public static int[][] transposeMatrix(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;

        int[][] transposed = new int[cols][rows];

        for (int i = 0; i < cols; i++) {
            for (int j = 0; j < rows; j++) {
                transposed[i][j] = matrix[j][i];
            }
        }

        return transposed;
    }

    // 最大值與最小值
    public static void findMaxMin(int[][] matrix) {
        int max = matrix[0][0];
        int min = matrix[0][0];

        for (int[] row : matrix) {
            for (int value : row) {
                if (value > max) max = value;
                if (value < min) min = value;
            }
        }

        System.out.println("最大值：" + max);
        System.out.println("最小值：" + min);
    }

    // 印出矩陣
    public static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int value : row) {
                System.out.printf("%4d", value);
            }
            System.out.println();
        }
        System.out.println();
    }
}


package lab;


import java.util.Random;

// 2305
// C5 = 0
// C7 = 2
// C11 = 6
// C = a * B
// short
// Обчислити суму найбільших елементів в стовпцях матриці з парними номерами та найменших елементів в стовпцях матриці з непарними номерами
public class Lab1 implements LabInterface {
    private final short[][] matrix;
    private final int constantMultiplier;
    private final Random random = new Random();
    public Lab1(short[][] matrix, int constantMultiplier) {
        this.matrix = matrix;
        this.constantMultiplier = constantMultiplier;
    }

    public Lab1(int rows, int columns, int constantMultiplier) {
        this.constantMultiplier = constantMultiplier;

        int bound = (Short.MAX_VALUE + 1) / Math.abs(constantMultiplier != 0 ? constantMultiplier : 1);
        this.matrix = generateRandomMatrix(rows, columns, bound);
    }

    @Override
    public void execute() {
        System.out.println("Початкова матриця: ");
        printMatrix(matrix);
        System.out.println("Множення на константу a = " + constantMultiplier);
        short[][] multipliedMatrix = multiplyMatrixByConstant(matrix, constantMultiplier);
        printMatrix(multipliedMatrix);
        System.out.println("Сума найбільших елементів в стовпцях матриці з парними номерами та найменших елементів в стовпцях матриці з непарними номерами:");
        System.out.println(calculateColumnSum(multipliedMatrix));
    }

    @Override
    public void printVariant() {
        System.out.println("Номер заліковки: " + scoreBookNumber);
        System.out.println("C5 = " + scoreBookNumber % 5);
        System.out.println("C7 = " + scoreBookNumber % 7);
        System.out.println("C11 = " + scoreBookNumber % 11);
    }

    private short[][] multiplyMatrixByConstant(short[][] matrix, int constant) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j] *= constant;
            }
        }
        return matrix;
    }

    private short[][] generateRandomMatrix(int rows, int cols, int bound) {
        short[][] matrix = new short[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = (short) random.nextInt(bound);
            }
        }
        return matrix;
    }

    private void printMatrix(short[][] matrix) {
        StringBuilder sb = new StringBuilder();
        for (short[] row : matrix) {
            sb.append("|");
            for (short value : row) {
                sb.append(String.format(" %-10d|", value));
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }

    private int calculateColumnSum(short[][] matrix) {
        int sum = 0;

        for (int col = 0; col < matrix[0].length; col++) {
            int extremeValue;

            if ((col + 1) % 2 == 0) { // Парний стовпець
                extremeValue = findMaxInColumn(matrix, col);
            } else { // Непарний стовпець
                extremeValue = findMinInColumn(matrix, col);
            }

            sum += extremeValue;
        }

        return sum;
    }

    private int findMaxInColumn(short[][] matrix, int col) {
        int max = Short.MIN_VALUE;

        for (short[] rows : matrix) {
            max = Math.max(max, rows[col]);
        }

        return max;
    }

    private int findMinInColumn(short[][] matrix, int col) {
        int min = Short.MAX_VALUE;

        for (short[] rows : matrix) {
            min = Math.min(min, rows[col]);
        }

        return min;
    }
}

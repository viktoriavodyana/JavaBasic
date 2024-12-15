import lab.Lab1;

import java.util.Scanner;

// 2305
// C5 = 0
// C7 = 2
// C11 = 6
// C = a * B
// short
// Обчислити суму найбільших елементів в стовпцях матриці з парними номерами та найменших елементів в стовпцях матриці з непарними номерами
public class Main {

    private static final Scanner SCANNER = new Scanner(System.in);
    public static void main(String[] args) {
        try {
            System.out.println("Введіть кількість рядків:");
            int rows = SCANNER.nextInt();
            System.out.println("Введіть кількість стовпців:");
            int columns = SCANNER.nextInt();
            System.out.println("Введіть консанту:");
            int constantMultiplier = SCANNER.nextInt();
            if (rows <= 0 || columns <= 0) {
                System.err.println("Matrix values can't be negative or zero");
                return;
            }
            Lab1 lab1 = new Lab1(rows, columns, constantMultiplier);
            lab1.printVariant();
            lab1.execute();

        } catch (Exception ex) {
            System.err.println("Error during execution");
            ex.printStackTrace();
        }
    }
}
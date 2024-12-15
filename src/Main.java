import lab.Lab1;
import lab.Lab2;
import lab.Lab3;
import lab.Lab4;

import java.util.Scanner;

public class Main {

    private static final Scanner SCANNER = new Scanner(System.in);
    public static void main(String[] args) {
        try {
            System.out.println("Перша лабораторна");
            executeLab1();
            System.out.println("Друга лабораторна");
            executeLab2();
            System.out.println("Третя лабораторна");
            executeLab3();
            System.out.println("Четверта лабораторна");
            executeLab4();
        } catch (Exception ex) {
            System.err.println("Error during execution");
            ex.printStackTrace();
        }
    }

    public static void executeLab1() {
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
    }

    private static void executeLab2() {
        System.out.println("Введіть текст:");
        String valueString = SCANNER.nextLine();
        System.out.println("Введіть першу літеру");
        char firstChar = SCANNER.nextLine().charAt(0);
        System.out.println("Введіть другу літеру");
        char lastChar = SCANNER.nextLine().charAt(0);
        Lab2 lab2 = new Lab2(valueString, firstChar, lastChar);
        lab2.printVariant();
        lab2.execute();
    }

    private static void executeLab3() {
        Lab3 lab3 = new Lab3();
        lab3.printVariant();
        lab3.execute();
    }

    private static void executeLab4() {
        System.out.println("Введіть текст:");
        String valueString = SCANNER.nextLine();
        System.out.println("Введіть першу літеру");
        char firstChar = SCANNER.nextLine().charAt(0);
        System.out.println("Введіть другу літеру");
        char lastChar = SCANNER.nextLine().charAt(0);
        Lab4 lab4 = new Lab4(valueString, firstChar, lastChar);
        lab4.printVariant();
        lab4.execute();
    }

}
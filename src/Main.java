import lab.*;

import java.util.Scanner;

public class Main {

    private static final Scanner SCANNER = new Scanner(System.in);
    public static void main(String[] args) {
        try {
            System.out.println("Введіть номер лабораторної роботи яку бажаєте запустити, або -1 - якщо всі по черзі.");
            int labToExecute = SCANNER.nextInt();
            SCANNER.nextLine();

            if (labToExecute == -1) {
                System.out.println("Перша лабораторна");
                executeLab1();
                System.out.println("Друга лабораторна");
                executeLab2();
                System.out.println("Третя лабораторна");
                executeLab3();
                System.out.println("Четверта лабораторна");
                executeLab4();
                System.out.println("П'ята лабораторна");
                executeLab5();
                System.out.println("Шоста лабораторна");
                executeLab6();
            } else {
                switch (labToExecute) {
                   case 1 -> executeLab1();
                   case 2 -> executeLab2();
                   case 3 -> executeLab3();
                   case 4 -> executeLab4();
                   case 5 -> executeLab5();
                   case 6 -> executeLab6();
                   default -> System.out.println("Неправильне значення");
                }
            }
        } catch (Exception ex) {
            System.err.println("Error during execution");
            ex.printStackTrace();
        }
    }

    public static void executeLab1() {
        System.out.println("Введіть кількість рядків:");
        System.out.flush();
        int rows = SCANNER.nextInt();
        System.out.println("Введіть кількість стовпців:");
        System.out.flush();
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

    public static void executeLab2() {
        System.out.println("Введіть текст:");
        String valueString = SCANNER.nextLine();
        System.out.println("Введіть першу літеру");
        char firstChar = SCANNER.next().charAt(0);
        System.out.println("Введіть другу літеру");
        char lastChar = SCANNER.next().charAt(0);
        Lab2 lab2 = new Lab2(valueString, firstChar, lastChar);
        lab2.printVariant();
        lab2.execute();
    }

    public static void executeLab3() {
        Lab3 lab3 = new Lab3();
        lab3.printVariant();
        lab3.execute();
    }

    public static void executeLab4() {
        System.out.println("Введіть текст:");
        String valueString = SCANNER.nextLine();
        System.out.println("Введіть першу літеру");
        char firstChar = SCANNER.next().charAt(0);
        System.out.println("Введіть другу літеру");
        char lastChar = SCANNER.next().charAt(0);
        Lab4 lab4 = new Lab4(valueString, firstChar, lastChar);
        lab4.printVariant();
        lab4.execute();
    }

    public static void executeLab5() {
        Lab5 lab5 = new Lab5();
        lab5.printVariant();
        lab5.execute();
    }

    public static void executeLab6() {
        Lab6 lab6 = new Lab6();
        lab6.printVariant();
        lab6.execute();
    }
}
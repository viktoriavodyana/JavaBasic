package lab;

import java.util.Arrays;

public class Lab3 implements LabInterface {
    @Override
    public void execute() {
        SportInventory[] inventoryArray = new SportInventory[] {
                new SportInventory("М'яч для баскетболу", "М'яч", 100, 30.0, "Nike"),
                new SportInventory("Ракетка для тенісу", "Ракетка", 50, 70.0, "Wilson"),
                new SportInventory("М'яч для футболу", "М'яч", 150, 20.0, "Adidas"),
                new SportInventory("Рукавичка для бейсболу", "Рукавичка", 200, 40.0, "Rawlings"),
                new SportInventory("Клюшка для хокею", "Клюшка", 75, 60.0, "Bauer")
        };

        // Сортування масиву за ціною за зростанням
        Arrays.sort(inventoryArray);
        System.out.println("Відсортовано за ціною (за зростанням):");
        for (SportInventory item : inventoryArray) {
            System.out.println(item);
        }

        // Сортування масиву за кількістю за спаданням
        Arrays.sort(inventoryArray, (a, b) -> Integer.compare(b.quantity(), a.quantity()));
        System.out.println("Відсортовано за кількістю (за спаданням):");
        for (SportInventory item : inventoryArray) {
            System.out.println(item);
        }

        // Пошук об'єкта, який ідентичний заданому
        SportInventory searchItem = new SportInventory("М'яч для футболу", "М'яч", 150, 20.0, "Adidas");
        SportInventory foundItem = findItem(inventoryArray, searchItem);

        if (foundItem != null) {
            System.out.println("Знайдено об'єкт: " + foundItem);
        } else {
            System.out.println("Об'єкт не знайдено.");
        }
    }

    @Override
    public void printVariant() {
        System.out.println("Номер заліковки: " + scoreBookNumber);
        System.out.println("C11 = " + scoreBookNumber % 11);
    }

    private SportInventory findItem(SportInventory[] inventoryArray, SportInventory searchItem) {
        for (SportInventory item : inventoryArray) {
            if (item.name().equals(searchItem.name()) &&
                    item.type().equals(searchItem.type()) &&
                    item.quantity() == searchItem.quantity() &&
                    item.price() == searchItem.price() &&
                    item.brand().equals(searchItem.brand())) {
                return item;
            }
        }
        return null;
    }

    record SportInventory(String name, String type, int quantity, double price,
                          String brand) implements Comparable<SportInventory> {

        @Override
            public int compareTo(SportInventory other) {
                // Сортування за ціною за зростанням
                return Double.compare(this.price, other.price);
            }

            @Override
            public String toString() {
                return "SportInventory{name='" + name + "', type='" + type + "', quantity=" + quantity + ", price=" + price + ", brand='" + brand + "'}";
            }
        }
}

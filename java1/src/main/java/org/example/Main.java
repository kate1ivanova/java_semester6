package org.example;

import java.util.Scanner;

/**
 * Демонстрация работы с IntContainer с вводом через клавиатуру.
 */
public class Main {
    public static void main(String[] args) {
        IntContainer container = new IntContainer();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Демонстрация работы IntContainer");
        System.out.println("Доступные команды:");
        System.out.println("add <число> - добавить число");
        System.out.println("get <индекс> - получить число по индексу");
        System.out.println("remove <индекс> - удалить число по индексу");
        System.out.println("contains <число> - проверить наличие числа");
        System.out.println("size - показать размер контейнера");
        System.out.println("clear - очистить контейнер");
        System.out.println("print - вывести все элементы");
        System.out.println("exit - выход из программы");

        while (true) {
            System.out.print("\nВведите команду: ");
            String command = scanner.next();

            try {
                switch (command) {
                    case "add":
                        int numToAdd = scanner.nextInt();
                        container.add(numToAdd);
                        System.out.println("Добавлено число: " + numToAdd);
                        break;

                    case "get":
                        int indexToGet = scanner.nextInt();
                        int value = container.get(indexToGet);
                        System.out.println("Элемент с индексом " + indexToGet + ": " + value);
                        break;

                    case "remove":
                        int indexToRemove = scanner.nextInt();
                        int removedValue = container.remove(indexToRemove);
                        System.out.println("Удален элемент: " + removedValue);
                        break;

                    case "contains":
                        int numToCheck = scanner.nextInt();
                        boolean contains = container.contains(numToCheck);
                        System.out.println("Содержит " + numToCheck + ": " + contains);
                        break;

                    case "size":
                        System.out.println("Размер контейнера: " + container.size());
                        break;

                    case "clear":
                        container.clear();
                        System.out.println("Контейнер очищен");
                        break;

                    case "print":
                        System.out.println("Элементы контейнера:");
                        for (int i = 0; i < container.size(); i++) {
                            System.out.println("Индекс " + i + ": " + container.get(i));
                        }
                        break;

                    case "exit":
                        System.out.println("Завершение программы");
                        scanner.close();
                        return;

                    default:
                        System.out.println("Неизвестная команда");
                        break;
                }
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Ошибка: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Ошибка ввода: " + e.getMessage());
                scanner.nextLine(); // Очистка буфера сканера
            }
        }
    }
}
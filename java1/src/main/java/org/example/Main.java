package org.example;

import java.util.Scanner;

/**
 * Демонстрация работы с IntContainer с вводом через клавиатуру.
 */
public class Main {
    public static void main(String[] args) {
        IntContainer container = new IntContainer();
        Scanner scanner = new Scanner(System.in);

        printMenu();

        while (true) {
            System.out.print("\nВведите команду: ");
            String command = scanner.next().toLowerCase().trim(); // Нормализация ввода

            try {
                switch (command) {
                    case "add" -> {
                        System.out.print("Введите число для добавления: ");
                        int numToAdd = scanner.nextInt();
                        container.add(numToAdd);
                        System.out.println("Добавлено число: " + numToAdd);
                    }
                    case "get" -> {
                        System.out.print("Введите индекс: ");
                        int indexToGet = scanner.nextInt();
                        int value = container.get(indexToGet);
                        System.out.println("Элемент с индексом " + indexToGet + ": " + value);
                    }
                    case "remove" -> {
                        System.out.print("Введите индекс для удаления: ");
                        int indexToRemove = scanner.nextInt();
                        int removedValue = container.remove(indexToRemove);
                        System.out.println("Удален элемент: " + removedValue);
                    }
                    case "contains" -> {
                        System.out.print("Введите число для проверки: ");
                        int numToCheck = scanner.nextInt();
                        boolean contains = container.contains(numToCheck);
                        System.out.println("Содержит " + numToCheck + ": " + contains);
                    }
                    case "size" -> {
                        System.out.println("Размер контейнера: " + container.size());
                    }
                    case "clear" -> {
                        container.clear();
                        System.out.println("Контейнер очищен");
                    }
                    case "print" -> {
                        printContainer(container);
                    }
                    case "exit" -> {
                        System.out.println("Завершение программы");
                        scanner.close();
                        return;
                    }
                    case "help" -> {
                        printMenu();
                    }
                    default -> {
                        System.out.println("Неизвестная команда. Введите 'help' для списка команд.");
                    }
                }
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Ошибка: неверный индекс. " + e.getMessage());
            } catch (java.util.InputMismatchException e) {
                System.out.println("Ошибка: введены некорректные данные. Ожидается число.");
                scanner.nextLine(); // Очистка буфера
            } catch (Exception e) {
                System.out.println("Неожиданная ошибка: " + e.getMessage());
            }
        }
    }

    private static void printMenu() {
        System.out.println("""
                Демонстрация работы IntContainer
                Доступные команды:
                add    - добавить число
                get    - получить число по индексу
                remove - удалить число по индексу
                contains - проверить наличие числа
                size   - показать размер контейнера
                clear  - очистить контейнер
                print  - вывести все элементы
                help   - показать это меню
                exit   - выход из программы
                """);
    }

    private static void printContainer(IntContainer container) {
        if (container.size() == 0) {
            System.out.println("Контейнер пуст.");
            return;
        }
        System.out.println("Элементы контейнера (" + container.size() + "):");
        for (int i = 0; i < container.size(); i++) {
            System.out.printf("[%d] = %d\n", i, container.get(i));
        }
    }
}
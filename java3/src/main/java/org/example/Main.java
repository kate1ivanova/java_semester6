package org.example;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;

public class Main {
    private static final int[] TEST_SIZES = {1_000, 10_000, 100_000, 1_000_000};
    private static final int WARMUP_ITERATIONS = 5;
    private static final int TEST_ITERATIONS = 10;
    private static final int OPERATION_COUNT = 10_000;

    public static void main(String[] args) {
        System.out.println("=== Сравнение производительности ArrayList и LinkedList ===");
        System.out.printf("%-20s | %-15s | %-15s | %-15s | %-15s%n",
                "Операция", "Размер", "ArrayList (нс)", "LinkedList (нс)", "Разница");

        testAllOperations();
    }

    private static void testAllOperations() {
        for (int size : TEST_SIZES) {
            // Тестируем разные операции
            testOperation("Добавление в конец", size, list -> {
                for (int i = 0; i < OPERATION_COUNT; i++) {
                    list.add(i);
                }
            });

            testOperation("Добавление в начало", size, list -> {
                for (int i = 0; i < OPERATION_COUNT; i++) {
                    list.add(0, i);
                }
            });

            testOperation("Получение по индексу", size, list -> {
                for (int i = 0; i < OPERATION_COUNT; i++) {
                    list.get(i % list.size());
                }
            });

            testOperation("Удаление с конца", size, list -> {
                for (int i = 0; i < OPERATION_COUNT && !list.isEmpty(); i++) {
                    if (list instanceof LinkedList) {
                        ((LinkedList<Integer>) list).removeLast();
                    } else {
                        list.remove(list.size() - 1);
                    }
                }
            });

            testOperation("Удаление с начала", size, list -> {
                for (int i = 0; i < OPERATION_COUNT && !list.isEmpty(); i++) {
                    list.remove(0);
                }
            });

            testOperation("Итерация по всем", size, list -> {
                for (Integer num : list) {
                    // Просто итерация
                }
            });
        }
    }

    private static void testOperation(String operationName, int initialSize, Consumer<List<Integer>> operation) {
        // Создаем и заполняем списки
        List<Integer> arrayList = new ArrayList<>();
        List<Integer> linkedList = new LinkedList<>();
        fillList(arrayList, initialSize);
        fillList(linkedList, initialSize);

        // Прогрев JVM
        warmUp(operation, arrayList, linkedList);

        // Тестирование ArrayList
        long arrayListTime = measurePerformance(new ArrayList<>(arrayList), operation);

        // Тестирование LinkedList
        long linkedListTime = measurePerformance(new LinkedList<>(linkedList), operation);

        // Вывод результатов
        printResults(operationName, initialSize, arrayListTime, linkedListTime);
    }

    private static void fillList(List<Integer> list, int size) {
        for (int i = 0; i < size; i++) {
            list.add(i);
        }
    }

    private static void warmUp(Consumer<List<Integer>> operation, List<Integer> arrayList, List<Integer> linkedList) {
        for (int i = 0; i < WARMUP_ITERATIONS; i++) {
            operation.accept(new ArrayList<>(arrayList));
            operation.accept(new LinkedList<>(linkedList));
        }
    }

    private static long measurePerformance(List<Integer> list, Consumer<List<Integer>> operation) {
        long totalTime = 0;

        for (int i = 0; i < TEST_ITERATIONS; i++) {
            List<Integer> testList = list instanceof ArrayList ?
                    new ArrayList<>(list) : new LinkedList<>(list);

            long startTime = System.nanoTime();
            operation.accept(testList);
            long endTime = System.nanoTime();

            totalTime += (endTime - startTime);
        }

        return totalTime / TEST_ITERATIONS;
    }

    private static void printResults(String operation, int size, long arrayListTime, long linkedListTime) {
        String difference;
        if (arrayListTime < linkedListTime) {
            double percent = 100.0 * (linkedListTime - arrayListTime) / linkedListTime;
            difference = String.format("ArrayList быстрее на %.1f%%", percent);
        } else {
            double percent = 100.0 * (arrayListTime - linkedListTime) / arrayListTime;
            difference = String.format("LinkedList быстрее на %.1f%%", percent);
        }

        System.out.printf("%-20s | %-15d | %-15d | %-15d | %-15s%n",
                operation, size, arrayListTime, linkedListTime, difference);
    }
}
package org.example;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Main {
    private static final int OPERATION_COUNT = 10000;
    private static final int WARMUP_ITERATIONS = 5;
    private static final int TEST_ITERATIONS = 10;

    public static void main(String[] args) {
        int smallSize = 1000;
        int mediumSize = 10000;
        int largeSize = 100000;

        System.out.println("Сравнение производительности ArrayList и LinkedList");
        System.out.println("Количество операций: " + OPERATION_COUNT);
        System.out.println("Количество итераций теста: " + TEST_ITERATIONS);
        System.out.println("-----------------------------------------------");

        testPerformance(smallSize, "Small (" + smallSize + " elements)");
        testPerformance(mediumSize, "Medium (" + mediumSize + " elements)");
        testPerformance(largeSize, "Large (" + largeSize + " elements)");
    }

    private static void testPerformance(int initialSize, String testName) {
        System.out.println("\n" + testName);
        System.out.println("-----------------------------------------------------");
        System.out.printf("%-15s | %-15s | %-15s | %-15s | %-15s\n",
                "Operation", "List Type", "Executions", "Time (ns)", "Difference");
        System.out.println("-----------------------------------------------------");

        testAdd(initialSize);
        testGet(initialSize);
        testRemove(initialSize);
    }

    private static void testAdd(int initialSize) {
        long arrayListTime = testOperation(new ArrayList<>(), initialSize,
                list -> {
                    for (int i = 0; i < OPERATION_COUNT; i++) {
                        list.add(i); // Добавление в конец
                    }
                });

        long linkedListTime = testOperation(new LinkedList<>(), initialSize,
                list -> {
                    for (int i = 0; i < OPERATION_COUNT; i++) {
                        list.add(i); // Добавление в конец
                    }
                });

        printResults("add", "ArrayList", OPERATION_COUNT, arrayListTime, linkedListTime);
        printResults("add", "LinkedList", OPERATION_COUNT, linkedListTime, arrayListTime);
    }

    private static void testGet(int initialSize) {
        List<Integer> arrayList = new ArrayList<>();
        List<Integer> linkedList = new LinkedList<>();
        for (int i = 0; i < initialSize; i++) {
            arrayList.add(i);
            linkedList.add(i);
        }

        long arrayListTime = testOperation(arrayList, initialSize,
                list -> {
                    for (int i = 0; i < OPERATION_COUNT; i++) {
                        list.get(i % list.size());
                    }
                });

        long linkedListTime = testOperation(linkedList, initialSize,
                list -> {
                    for (int i = 0; i < OPERATION_COUNT; i++) {
                        list.get(i % list.size());
                    }
                });

        printResults("get", "ArrayList", OPERATION_COUNT, arrayListTime, linkedListTime);
        printResults("get", "LinkedList", OPERATION_COUNT, linkedListTime, arrayListTime);
    }

    private static void testRemove(int initialSize) {
        long arrayListTime = testOperation(new ArrayList<>(), initialSize,
                list -> {
                    for (int i = 0; i < OPERATION_COUNT && !list.isEmpty(); i++) {
                        list.remove(list.size() - 1); // Удаление с конца
                    }
                });

        long linkedListTime = testOperation(new LinkedList<>(), initialSize,
                list -> {
                    for (int i = 0; i < OPERATION_COUNT && !list.isEmpty(); i++) {
                        list.removeLast(); // Удаление с конца
                    }
                });

        printResults("remove", "ArrayList", OPERATION_COUNT, arrayListTime, linkedListTime);
        printResults("remove", "LinkedList", OPERATION_COUNT, linkedListTime, arrayListTime);
    }

    private static long testOperation(List<Integer> list, int initialSize, Operation operation) {
        // Подготовка данных
        for (int i = 0; i < initialSize; i++) {
            list.add(i);
        }

        // Прогрев JVM
        for (int i = 0; i < WARMUP_ITERATIONS; i++) {
            operation.execute(new ArrayList<>(list));
            operation.execute(new LinkedList<>(list));
        }

        // Тестирование
        long totalTime = 0;
        for (int i = 0; i < TEST_ITERATIONS; i++) {
            List<Integer> testList = list instanceof ArrayList ?
                    new ArrayList<>(list) : new LinkedList<>(list);

            long startTime = System.nanoTime();
            operation.execute(testList);
            long endTime = System.nanoTime();

            totalTime += (endTime - startTime);
        }

        return totalTime / TEST_ITERATIONS;
    }

    private static void printResults(String operation, String listType, int executions, long time, long compareTime) {
        String difference = time < compareTime ?
                String.format("Faster by %.1f%%", 100 * (compareTime - time) / (double)compareTime) :
                String.format("Slower by %.1f%%", 100 * (time - compareTime) / (double)compareTime);

        System.out.printf("%-15s | %-15s | %-15d | %-15d | %-15s\n",
                operation, listType, executions, time, difference);
    }

    @FunctionalInterface
    private interface Operation {
        void execute(List<Integer> list);
    }
}
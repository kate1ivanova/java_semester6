package org.example;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Main {
    private static final int OPERATION_COUNT = 10000;

    public static void main(String[] args) {
        int smallSize = 1000;
        int mediumSize = 10000;
        int largeSize = 100000;

        System.out.println("Демонстрация операций с ArrayList и LinkedList");
        System.out.println("Количество операций: " + OPERATION_COUNT);
        System.out.println("-----------------------------------------------");

        demonstrateOperations(smallSize, "Small (" + smallSize + " elements)");
        demonstrateOperations(mediumSize, "Medium (" + mediumSize + " elements)");
        demonstrateOperations(largeSize, "Large (" + largeSize + " elements)");
    }

    private static void demonstrateOperations(int initialSize, String testName) {
        System.out.println("\n" + testName);
        System.out.println("-----------------------------------------------------");
        System.out.printf("%-15s | %-15s\n", "Operation", "List Type");
        System.out.println("-----------------------------------------------------");

        demonstrateAdd(initialSize);
        demonstrateGet(initialSize);
        demonstrateRemove(initialSize);
    }

    private static void demonstrateAdd(int initialSize) {
        System.out.printf("%-15s | %-15s\n", "add", "ArrayList");
        System.out.printf("%-15s | %-15s\n", "add", "LinkedList");
    }

    private static void demonstrateGet(int initialSize) {
        System.out.printf("%-15s | %-15s\n", "get", "ArrayList");
        System.out.printf("%-15s | %-15s\n", "get", "LinkedList");
    }

    private static void demonstrateRemove(int initialSize) {
        System.out.printf("%-15s | %-15s\n", "remove", "ArrayList");
        System.out.printf("%-15s | %-15s\n", "remove", "LinkedList");
    }
}
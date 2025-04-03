package org.example;


import java.util.HashSet;
import java.util.Map;

import java.util.Scanner;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        ExpressionCalculator calculator = new ExpressionCalculator();
        Scanner scanner = new Scanner(System.in);

        printWelcomeMessage();

        while (true) {
            System.out.println("\nМеню:");
            System.out.println("1. Вычислить выражение");
            System.out.println("2. Добавить/изменить переменную");
            System.out.println("3. Просмотреть текущие переменные");
            System.out.println("4. Показать пример выражения");
            System.out.println("5. Выход");
            System.out.print("Выберите действие (1-5): ");

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    calculateExpression(calculator, scanner);
                    break;
                case "2":
                    addVariable(calculator, scanner);
                    break;
                case "3":
                    showVariables(calculator);
                    break;
                case "4":
                    showExample(calculator);
                    break;
                case "5":
                    System.out.println("\nДо свидания! Программа завершена.");
                    scanner.close();
                    return;
                default:
                    System.out.println("Неверный ввод. Пожалуйста, выберите от 1 до 5.");
            }
        }
    }

    private static void printWelcomeMessage() {
        System.out.println("╔══════════════════════════════════════╗");
        System.out.println("║      КАЛЬКУЛЯТОР ВЫРАЖЕНИЙ 2.0      ║");
        System.out.println("╚══════════════════════════════════════╝");
        System.out.println("\nПоддерживаемые функции: sin, cos, tan, ctan, asin, acos, atan, sqrt");
        System.out.println("Поддерживаемые операторы: + - * / ^");
    }
    private static void calculateExpression(ExpressionCalculator calculator, Scanner scanner) {
        System.out.print("\nВведите выражение для вычисления: ");
        String expression = scanner.nextLine().trim();

        // Получаем все переменные из выражения
        Set<String> variablesInExpression = extractVariables(expression);

        // Запрашиваем значения для каждой переменной
        for (String var : variablesInExpression) {
            if (!calculator.getVariables().containsKey(var)) {
                System.out.print("Введите значение для переменной '" + var + "': ");
                try {
                    double value = Double.parseDouble(scanner.nextLine().trim());
                    calculator.addVariable(var, value);
                } catch (NumberFormatException e) {
                    System.out.println("Ошибка: введено некорректное значение. Попробуйте снова.");
                    return;
                }
            }
        }

        try {
            double result = calculator.evaluateExpression(expression);
            System.out.println("\n══════════════════════════════════════");
            System.out.printf("  Результат: %s = %.4f\n", expression, result);
            System.out.println("══════════════════════════════════════");
        } catch (Exception e) {
            System.out.println("\n❌ Ошибка: " + e.getMessage());
        }
    }

    // Метод для извлечения переменных из выражения
    private static Set<String> extractVariables(String expression) {
        Set<String> variables = new HashSet<>();
        Pattern pattern = Pattern.compile("[a-zA-Z]+");
        Matcher matcher = pattern.matcher(expression);

        while (matcher.find()) {
            String var = matcher.group();
            // Исключаем названия функций
            if (!var.equals("sin") && !var.equals("cos") && !var.equals("tan") &&
                    !var.equals("ctan") && !var.equals("asin") && !var.equals("acos") &&
                    !var.equals("atan") && !var.equals("sqrt")) {
                variables.add(var);
            }
        }
        return variables;
    }

    private static void addVariable(ExpressionCalculator calculator, Scanner scanner) {
        System.out.print("\nВведите имя переменной: ");
        String name = scanner.nextLine().trim();

        System.out.print("Введите значение переменной " + name + ": ");
        try {
            double value = Double.parseDouble(scanner.nextLine().trim());
            calculator.addVariable(name, value);
            System.out.println("Переменная " + name + " = " + value + " успешно добавлена.");
        } catch (NumberFormatException e) {
            System.out.println("Ошибка: введено некорректное значение.");
        }
    }

    private static void showVariables(ExpressionCalculator calculator) {
        Map<String, Double> vars = calculator.getVariables();

        if (vars.isEmpty()) {
            System.out.println("\nНет сохраненных переменных.");
        } else {
            System.out.println("\nТекущие переменные:");
            System.out.println("┌────────────┬────────────┐");
            System.out.println("│ Имя        │ Значение   │");
            System.out.println("├────────────┼────────────┤");

            vars.forEach((name, value) ->
                    System.out.printf("│ %-10s │ %-10.4f │\n", name, value));

            System.out.println("└────────────┴────────────┘");
        }
    }

    private static void showExample(ExpressionCalculator calculator) {
        System.out.println("\nПример выражения с переменными:");
        System.out.println("  " + calculator.giveExample());
        System.out.println("\nДля использования этого примера:");
        System.out.println("1. Добавьте необходимые переменные (например, x и y)");
        System.out.println("2. Введите это выражение для вычисления");
    }
}
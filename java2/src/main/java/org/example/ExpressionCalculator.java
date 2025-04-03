package org.example;

import java.util.*;

/**
 * Калькулятор математических выражений, который вычисляет выражения с переменными.
 * Поддерживает основные арифметические операции, тригонометрические функции и др.
 */
public class ExpressionCalculator {
    boolean hasOperator = false; // Флаг для отслеживания последовательных операторов
    private final Map<String, Double> variables; // Хранилище переменных и их значений

    /**
     * Возвращает карту переменных и их значений.
     *
     * @return Карта переменных в формате Map<String, Double>
     */
    public Map<String, Double> getVariables() {
        return variables;
    }

    /**
     * Конструктор по умолчанию. Инициализирует пустую карту переменных.
     */
    public ExpressionCalculator() {
        this.variables = new HashMap<>();
    }

    /**
     * Добавляет переменную с указанным значением в калькулятор.
     *
     * @param variableName Имя переменной
     * @param value Значение переменной
     */
    public void addVariable(String variableName, double value) {
        variables.put(variableName, value);
    }

    /**
     * Вычисляет значение выражения, подставляя значения переменных.
     *
     * @param expression Строка с математическим выражением
     * @return Результат вычисления
     */
    public double evaluateExpression(String expression) {
        // Заменяем все переменные в выражении на их значения
        for (Map.Entry<String, Double> entry : variables.entrySet()) {
            expression = expression.replaceAll(entry.getKey(), Double.toString(entry.getValue()));
        }

        return evaluate(expression);
    }

    /**
     * Возвращает пример математического выражения с переменными.
     *
     * @return Пример строки с выражением
     */
    public String giveExample() {
        return "sin(x)+cos(x)+tan(y)-asin(x)-ctan(y) + x^3 - y*x / sqrt(y)";
    }

    /**
     * Основной метод вычисления математического выражения.
     * Использует внутренний класс для парсинга и вычисления.
     *
     * @param expression Строка с выражением
     * @return Результат вычисления
     */
    private double evaluate(String expression) {
        return new Object() {
            int pos = -1, ch; // Текущая позиция и символ

            /**
             * Переход к следующему символу в выражении.
             */
            void nextChar() {
                ch = (++pos < expression.length()) ? expression.charAt(pos) : -1;
            }

            /**
             * Проверяет и пропускает указанный символ, если он встречается.
             *
             * @param charToEat Символ для проверки
             * @return true если символ был найден и пропущен
             */
            boolean eat(int charToEat) {
                while (ch == ' ') nextChar(); // Пропускаем пробелы
                if (ch == charToEat) {
                    nextChar();
                    return true;
                }
                return false;
            }

            /**
             * Начинает парсинг выражения.
             *
             * @return Результат вычисления
             */
            double parse() {
                nextChar();
                double x = parseExpression();
                if (pos < expression.length()) throw new RuntimeException("Неожиданный символ: " + (char)ch);
                return x;
            }

            /**
             * Обрабатывает операции сложения и вычитания.
             *
             * @return Результат операций
             */
            double parseExpression() {
                double x = parseTerm();
                for (;;) {
                    if (eat('+')) {
                        if (hasOperator) {
                            throw new RuntimeException("Неожиданный оператор после другого оператора на позиции " + pos);
                        }
                        hasOperator = true;
                        x += parseTerm();
                    } else if (eat('-')) {
                        if (hasOperator) {
                            throw new RuntimeException("Неожиданный оператор после другого оператора на позиции " + pos);
                        }
                        hasOperator = true;
                        x -= parseTerm();
                    } else {
                        hasOperator = false;
                        return x;
                    }
                }
            }

            /**
             * Обрабатывает операции умножения и деления.
             *
             * @return Результат операций
             */
            double parseTerm() {
                double x = parseFactor();
                for (;;) {
                    if (eat('*')) {
                        if (hasOperator) {
                            throw new RuntimeException("Неожиданный оператор после другого оператора на позиции " + pos);
                        }
                        hasOperator = true;
                        x *= parseFactor();
                    } else if (eat('/')) {
                        if (hasOperator) {
                            throw new RuntimeException("Неожиданный оператор после другого оператора на позиции " + pos);
                        }
                        hasOperator = true;
                        x /= parseFactor();
                    } else {
                        hasOperator = false;
                        return x;
                    }
                }
            }

            /**
             * Обрабатывает числа, скобки, функции и унарные операторы.
             *
             * @return Результат вычисления фактора
             */
            double parseFactor() {
                if (eat('+')) {
                    if (hasOperator) {
                        throw new RuntimeException("Неожиданный оператор после другого оператора на позиции " + pos);
                    }
                    hasOperator = true;
                    return parseFactor();
                }
                if (eat('-')) {
                    if (hasOperator) {
                        throw new RuntimeException("Неожиданный оператор после другого оператора на позиции " + pos);
                    }
                    hasOperator = true;
                    return -parseFactor();
                }

                double x;
                boolean prevhasOperator = hasOperator;
                int startPos = this.pos;

                if (eat('(')) {
                    hasOperator = false;
                    x = parseExpression();
                    if (!eat(')')) throw new RuntimeException("Ожидалась закрывающая скобка на позиции " + pos);
                    else hasOperator = prevhasOperator;
                } else if ((ch >= '0' && ch <= '9') || ch == '.') {
                    // Обработка чисел
                    while ((ch >= '0' && ch <= '9') || ch == '.') nextChar();
                    x = Double.parseDouble(expression.substring(startPos, this.pos));
                } else if (ch >= 'a' && ch <= 'z') {
                    // Обработка функций
                    while (ch >= 'a' && ch <= 'z') nextChar();
                    String func = expression.substring(startPos, this.pos);
                    if (!eat('(')) throw new RuntimeException("Ожидалась открывающая скобка после функции на позиции " + startPos);
                    x = parseExpression();
                    if (!eat(')')) throw new RuntimeException("Ожидалась закрывающая скобка после аргумента функции на позиции " + pos);

                    // Выбор и вычисление соответствующей функции
                    switch (func) {
                        case "sqrt":
                            x = Math.sqrt(x);
                            break;
                        case "sin":
                            x = Math.sin(Math.toRadians(x));
                            break;
                        case "cos":
                            x = Math.cos(Math.toRadians(x));
                            break;
                        case "tan":
                            x = Math.tan(Math.toRadians(x));
                            break;
                        case "ctan":
                            x = 1.0 / Math.tan(Math.toRadians(x));
                            break;
                        case "asin":
                            x = Math.asin(Math.toRadians(x));
                            break;
                        case "acos":
                            x = Math.acos(Math.toRadians(x));
                            break;
                        case "atan":
                            x = Math.atan(Math.toRadians(x));
                            break;
                        default:
                            throw new RuntimeException("Неизвестная функция: " + func);
                    }
                } else {
                    throw new RuntimeException("Неожиданный символ: " + (char)ch);
                }

                if (eat('^')) x = Math.pow(x, parseFactor());

                hasOperator = false;
                return x;
            }

        }.parse();
    }
}
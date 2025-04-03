package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ExpressionCalculatorTest {

    @Test
    void testBasicOperations() {
        ExpressionCalculator calc = new ExpressionCalculator();
        assertEquals(5.0, calc.evaluateExpression("2 + 3"), 0.001);
        assertEquals(-1.0, calc.evaluateExpression("2 - 3"), 0.001);
        assertEquals(6.0, calc.evaluateExpression("2 * 3"), 0.001);
        assertEquals(0.666, calc.evaluateExpression("2 / 3"), 0.001);
    }

    @Test
    void testVariables() {
        ExpressionCalculator calc = new ExpressionCalculator();
        calc.addVariable("x", 2.5);
        calc.addVariable("y", 4.0);

        assertEquals(6.5, calc.evaluateExpression("x + y"), 0.001);
        assertEquals(10.0, calc.evaluateExpression("x * y"), 0.001);
    }

    @Test
    void testFunctions() {
        ExpressionCalculator calc = new ExpressionCalculator();

        assertEquals(1.0, calc.evaluateExpression("sin(90)"), 0.001);
        assertEquals(0.0, calc.evaluateExpression("cos(90)"), 0.001);
        assertEquals(2.0, calc.evaluateExpression("sqrt(4)"), 0.001);
        assertEquals(1.0, calc.evaluateExpression("tan(45)"), 0.001);
    }

    @Test
    void testParentheses() {
        ExpressionCalculator calc = new ExpressionCalculator();

        assertEquals(14.0, calc.evaluateExpression("2 * (3 + 4)"), 0.001);
        assertEquals(10.0, calc.evaluateExpression("(2 + 3) * 2"), 0.001);
    }

    @Test
    void testPowerOperation() {
        ExpressionCalculator calc = new ExpressionCalculator();

        assertEquals(8.0, calc.evaluateExpression("2 ^ 3"), 0.001);
        assertEquals(9.0, calc.evaluateExpression("3 ^ 2"), 0.001);
    }



    @Test
    void testInvalidExpressions() {
        ExpressionCalculator calc = new ExpressionCalculator();

        assertThrows(RuntimeException.class, () -> calc.evaluateExpression("2 + + 3"));
        assertThrows(RuntimeException.class, () -> calc.evaluateExpression("2 + (3 * 4"));
        assertThrows(RuntimeException.class, () -> calc.evaluateExpression("unknown(5)"));
    }

    @Test
    void testUnaryOperators() {
        ExpressionCalculator calc = new ExpressionCalculator();

        assertEquals(-5.0, calc.evaluateExpression("-5"), 0.001);
        assertEquals(3.0, calc.evaluateExpression("+3"), 0.001);
        assertEquals(-2.0, calc.evaluateExpression("-(1 + 1)"), 0.001);
    }

    @Test
    void testExampleExpression() {
        ExpressionCalculator calc = new ExpressionCalculator();
        calc.addVariable("x", 30.0);
        calc.addVariable("y", 2.0);

        String example = calc.giveExample();
        assertNotNull(example);
        assertDoesNotThrow(() -> calc.evaluateExpression(example));
    }
}
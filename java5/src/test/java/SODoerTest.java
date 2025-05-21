
import org.junit.jupiter.api.Test;
import org.example.entities.SODoer;
import org.example.interfaces.SomeOtherInterface;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit tests for the {@code SODoer} class to ensure expected behavior.
 *
 * This class contains tests to verify specific functionality within {@code SODoer},
 * such as checking the console output from the {@code doSomeOther} method.
 *
 * The test uses a utility method to capture console output, allowing assertion
 * of expected behavior based on output content.
 *
 */
public class SODoerTest {
    /**
     * Tests the {@code doSomething} method of {@code SODoer} by capturing console output.
     * Verifies that the output matches the expected value.
     */
    @Test
    void testDoSomething() {
        SomeOtherInterface someImpl = new SODoer();
        String output = getConsoleOutput(someImpl::doSomeOther);
        assertEquals("C\r\n", output);

    }

    /**
     * Utility method to capture console output from a {@code Runnable}.
     * Redirects {@code System.out} to a custom {@code ByteArrayOutputStream}, runs the provided
     * {@code Runnable}, and then restores the original {@code System.out}.
     *
     * This method allows capturing console output to verify expected behavior in tests.
     *
     * @param runnable the {@code Runnable} to execute while capturing console output
     * @return the captured console output as a string
     */
    private String getConsoleOutput(Runnable runnable) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        PrintStream oldOut = System.out;
        System.setOut(printStream);

        try {
            runnable.run();
        } finally {
            System.setOut(oldOut);
        }

        return outputStream.toString();
    }
}
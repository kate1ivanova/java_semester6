
import org.junit.jupiter.api.Test;
import org.example.entities.OtherImpl;
import org.example.interfaces.SomeInterface;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit tests for the {@code OtherImpl} class to ensure expected behavior.
 *
 * This class contains tests to verify specific functionality within {@code OtherImpl},
 * such as checking the console output from the {@code doSomething} method.
 *
 * The test uses a utility method to capture console output, allowing assertion
 * of expected behavior based on output content.
 *
 */
public class OtherImplTest {
    /**
     * Tests the {@code doSomething} method of {@code OtherImpl} by capturing console output.
     * Verifies that the output matches the expected value.
     */
    @Test
    void testDoSomething() {
        SomeInterface someImpl = new OtherImpl();
        String output = getConsoleOutput(someImpl::doSomething);
        assertEquals("B\r\n", output);

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
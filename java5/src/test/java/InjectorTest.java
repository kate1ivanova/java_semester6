
import org.junit.Test;
import org.example.entities.Injector;

import static org.junit.Assert.*;

/**
 * Contains module tests for the {@code Injector} class to ensure proper dependency injection functionality.
 * Use TestClass for tests.
 *
 * This class includes tests for different scenarios:
 * - Testing injection using a custom properties file specified by the user.
 * - Verifying that dependencies are injected correctly into the target object.
 * - Testing behavior when providing a non-existing configuration file path.
 */
public class InjectorTest {
    /**
     * Tests dependency injection using a custom properties file.
     * Verifies that dependencies are injected correctly into the target object.
     */
    @Test
    public void testInject() {

        TestClass testClass = (new Injector("resTests/custom.properties")).inject(new TestClass());

        // Check if dependencies are injected properly
        assertNotNull(testClass.getService());
        assertTrue(testClass.getService() instanceof ServiceImpl);
    }

    /**
     * Tests dependency injection using a custom properties file path.
     * Verifies that dependencies are injected correctly into the target object.
     */
    @Test
    public void testCustomConfigPath() {
        Injector injector = new Injector("resTests/custom.properties");
        TestClass testClass = new TestClass();

        // Inject dependencies into testClass using custom config file
        testClass = injector.inject(testClass);

        // Check if dependencies are injected properly
        assertNotNull(testClass.getService());
        assertTrue(testClass.getService() instanceof ServiceImpl);
    }

    /**
     * Tests behavior when providing a non-existing configuration file path.
     * Verifies that an exception is thrown when attempting to create an Injector with a non-existing config file.
     */
    @Test
    public void testNonExistingConfigPath() {
        // Using a non-existing config path should throw an exception
        assertThrows(NullPointerException.class, () -> {
            Injector injector = new Injector("non_existing.properties");
        });
    }
}





package org.example.interfaces;

/**
 * Defines a contract for classes that implement a specific action.
 * Classes implementing this interface must provide an implementation for the {@code doSomeOther} method.
 *
 * Example usage:
 * {@code
 * public class MyClass implements SomeInterface {
 *     {@literal @}Override
 *     public void doSomeOther() {
 *         // Implementation logic here
 *     }
 * }
 * }
 */
public interface SomeOtherInterface {
    void doSomeOther();
}
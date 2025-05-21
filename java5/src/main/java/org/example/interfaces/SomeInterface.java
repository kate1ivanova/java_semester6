package org.example.interfaces;


/**
 * Defines a contract for classes that implement a specific action.
 * Classes implementing this interface must provide an implementation for the {@code doSomething} method.
 *
 * Example usage:
 * {@code
 * public class MyClass implements SomeInterface {
 *     {@literal @}Override
 *     public void doSomething() {
 *         // Implementation logic here
 *     }
 * }
 * }
 */
public interface SomeInterface {
    void doSomething();
}
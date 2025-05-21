package org.example.entities;

import org.example.interfaces.SomeInterface;

/**
 * An implementation of the {@code SomeInterface} interface that performs a specific action when the {@code doSomething} method is called.
 * This implementation prints "B" to the standard output.
 *
 * Example usage:
 * {@code
 * SomeInterface instance = new OtherImpl();
 * instance.doSomething(); // Output: B
 * }
 */
public class OtherImpl implements SomeInterface {
    @Override
    public void doSomething() {
        System.out.println("B");
    }
}
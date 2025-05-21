package org.example.entities;

import org.example.interfaces.SomeInterface;

/**
 * An implementation of the {@code SomeInterface} interface that performs a specific action when the {@code doSomething} method is called.
 * This implementation prints "A" to the standard output.
 *
 * Example usage:
 * {@code
 * SomeInterface instance = new SomeImpl();
 * instance.doSomething(); // Output: A
 * }
 *
 */
public class SomeImpl implements SomeInterface {
    @Override
    public void doSomething() {
        System.out.println("A");
    }
}
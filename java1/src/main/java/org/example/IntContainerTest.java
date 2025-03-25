package org.example;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class IntContainerTest {
    private IntContainer container;

    @BeforeEach
    public void setUp() {
        container = new IntContainer();
    }

    @Test
    public void testAddAndGet() {
        container.add(10);
        container.add(20);
        assertEquals(10, container.get(0));
        assertEquals(20, container.get(1));
        assertEquals(2, container.size());
    }

    @Test
    public void testRemove() {
        container.add(10);
        container.add(20);
        container.add(30);
        assertEquals(20, container.remove(1));
        assertEquals(2, container.size());
        assertEquals(30, container.get(1));
    }

    @Test
    public void testIsEmpty() {
        assertTrue(container.isEmpty());
        container.add(10);
        assertFalse(container.isEmpty());
    }

    @Test
    public void testClear() {
        container.add(10);
        container.add(20);
        container.clear();
        assertTrue(container.isEmpty());
        assertEquals(0, container.size());
    }

    @Test
    public void testContains() {
        container.add(10);
        container.add(20);
        assertTrue(container.contains(10));
        assertFalse(container.contains(30));
    }

    @Test
    public void testInitialCapacity() {
        IntContainer customContainer = new IntContainer(5);
        assertEquals(0, customContainer.size());
    }

    @Test
    public void testNegativeInitialCapacity() {
        assertThrows(IllegalArgumentException.class, () -> new IntContainer(-1));
    }

    @Test
    public void testGetOutOfBounds() {
        container.add(10);
        assertThrows(IndexOutOfBoundsException.class, () -> container.get(1));
    }

    @Test
    public void testRemoveOutOfBounds() {
        container.add(10);
        assertThrows(IndexOutOfBoundsException.class, () -> container.remove(1));
    }

    @Test
    public void testDynamicExpansion() {
        for (int i = 0; i < 100; i++) {
            container.add(i);
        }
        assertEquals(100, container.size());
        for (int i = 0; i < 100; i++) {
            assertEquals(i, container.get(i));
        }
    }
}
package com.twu.biblioteca.models;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class BookTests {
    @Test
    public void compareTwoEqualBooksShouldReturnTrue() {
        assertEquals(new Book("title", "author", 2000), new Book("title", "author", 2000));
    }
    @Test
    public void compareTwoDifferentBooksShouldReturnFalse() {
        assertNotEquals(new Book("title", "author", 2000), new Book("title", "author", 1000));
    }
}

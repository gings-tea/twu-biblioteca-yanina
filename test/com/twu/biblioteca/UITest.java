package com.twu.biblioteca;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UITest {

    @Test
    public void shouldReturnTrueIfBookIsAvailable() {
        LibraryBook libraryBook = new LibraryBook("TDD", "Kent Beck", 2003);
        assertEquals(true, new UI().checkOutBook(libraryBook, false, "", ""));
    }

    @Test
    public void shouldReturnFalseIfBookIsNotAvailable() {
        LibraryBook libraryBook = new LibraryBook("TD", "Kent Beck", 2003);
        assertEquals(false, new UI().checkOutBook(libraryBook, false, "", ""));
    }

    @Test
    public void shouldMakeAvailableAReturnedBookWhenTheBookExists() {
        LibraryBook libraryBook = new LibraryBook("Harry Potter", "J K Rowling", 2000);
        assertEquals(true, new UI().makeAvailableAReturnedBook(libraryBook, true, "", ""));
    }

    @Test
    public void shouldNotMakeAvailableAReturnedBookWhenTheBookDoesntExists() {
        LibraryBook libraryBook = new LibraryBook("Harry Pot", "J K Rowling", 2000);
        assertEquals(false, new UI().makeAvailableAReturnedBook(libraryBook, true, "", ""));
    }
}

package com.twu.biblioteca;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

public class BookManagerTests {
    private BookManager mockBookManager;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @Before
    public void beforeTest(){
        mockBookManager = new BookManager();
        mockBookManager.addBook(new LibraryBook("TDD", "Kent Beck", 2003),true);
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void isBookAvailableShouldReturnTrue() {
        LibraryBook libraryBook = new LibraryBook("TDD", "Kent Beck", 2003);

        assertEquals(true, mockBookManager.isBookInAvailable(libraryBook));
    }

    @Test
    public void checkedOutBookShouldNotBeAvailable(){
        LibraryBook libraryBook = new LibraryBook("TDD", "Kent Beck", 2003);
        mockBookManager.changeAvailability(libraryBook, false);
        assertEquals(false, mockBookManager.isBookInAvailable(libraryBook));
    }

    @Test
    public void getBookDetails() {
        mockBookManager.getAvailabilityBookDetails();
        assertEquals("TDD\tKent Beck\t2003", outContent.toString());
    }
}

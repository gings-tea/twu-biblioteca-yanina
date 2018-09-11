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
    private String loggedUser;

    @Before
    public void beforeTest(){
        mockBookManager = new BookManager();
        System.setOut(new PrintStream(outContent));
        loggedUser = "111-1111";
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void isBookAvailableShouldReturnTrue() {
        Book book = new Book("TDD", "Kent Beck", 2003);
        assertEquals(true, mockBookManager.isBookInLibraryAvailable(book));
    }

    @Test
    public void isBookAvailableShouldReturnFalse() {
        Book book = new Book("T", "Kent Beck", 2000);
        assertEquals(false, mockBookManager.isBookInLibraryAvailable(book));
    }

    @Test
    public void checkedOutBookShouldNotBeAvailable(){
        Book book = new Book("TDD", "Kent Beck", 2003);
        mockBookManager.changeAvailability(book, false, loggedUser);
        assertEquals(false, mockBookManager.isBookInLibraryAvailable(book));
    }

    @Test
    public void returnedBookShouldBeAvailable(){
        Book book = new Book("NotAvailable", "Author", 0);
        mockBookManager.changeAvailability(book, true, loggedUser);
        assertEquals(true, mockBookManager.isBookInLibraryAvailable(book));
    }

    @Test
    public void returnNonExistentBook() {
        Book book = new Book("T", "Kent Beck", 2000);
        mockBookManager.changeAvailability(book, true, loggedUser);
        assertEquals(false, mockBookManager.isBookInLibraryAvailable(book));
    }

    @Test
    public void getBookDetails() {
        mockBookManager.getAvailableBookDetails("%s\t%s\t%s\n");
        assertEquals("TDD\tKent Beck\t2003\n", outContent.toString());
    }

}

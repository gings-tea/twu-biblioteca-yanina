package com.twu.biblioteca;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Hashtable;

import static org.junit.Assert.assertEquals;

public class BookManagerTests {
    private BookManager mockBookManager;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @Before
    public void beforeTest(){
        Hashtable<LibraryBook, Boolean> libraryBookCollection = new Hashtable<>();
        libraryBookCollection.put(new LibraryBook("TDD", "Kent Beck", 2003),true);
        libraryBookCollection.put(new LibraryBook("NotAvailable", "Author", 0),false);
        mockBookManager = new BookManager(libraryBookCollection);
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void isBookAvailableShouldReturnTrue() {
        LibraryBook libraryBook = new LibraryBook("TDD", "Kent Beck", 2003);
        assertEquals(true, mockBookManager.isBookInLibraryAvailable(libraryBook));
    }

    @Test
    public void isBookAvailableShouldReturnFalse() {
        LibraryBook libraryBook = new LibraryBook("T", "Kent Beck", 2000);
        assertEquals(false, mockBookManager.isBookInLibraryAvailable(libraryBook));
    }

    @Test
    public void checkedOutBookShouldNotBeAvailable(){
        LibraryBook libraryBook = new LibraryBook("TDD", "Kent Beck", 2003);
        mockBookManager.changeAvailability(libraryBook, false);
        assertEquals(false, mockBookManager.isBookInLibraryAvailable(libraryBook));
    }

    @Test
    public void returnedBookShouldBeAvailable(){
        LibraryBook libraryBook = new LibraryBook("NotAvailable", "Author", 0);
        mockBookManager.changeAvailability(libraryBook, true);
        assertEquals(true, mockBookManager.isBookInLibraryAvailable(libraryBook));
    }

    @Test
    public void returnNonExistentBook() {
        LibraryBook libraryBook = new LibraryBook("T", "Kent Beck", 2000);
        mockBookManager.changeAvailability(libraryBook, true);
        assertEquals(false, mockBookManager.isBookInLibraryAvailable(libraryBook));
    }

    @Test
    public void getBookDetails() {
        mockBookManager.getAvailableBookDetails("%s\t%s\t%s\n");
        assertEquals("TDD\tKent Beck\t2003\n", outContent.toString());
    }

}

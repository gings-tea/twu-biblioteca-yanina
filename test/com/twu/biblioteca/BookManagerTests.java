package com.twu.biblioteca;

import com.twu.biblioteca.models.Book;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Hashtable;

import static org.junit.Assert.*;

public class BookManagerTests {
    private BookManager bookManager;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private String loggedUser;

    @Before
    public void beforeTest(){
        Hashtable<Book, String> checkedOutBooks = new Hashtable<>();
        ArrayList<Book> availableBooks = new ArrayList<>();

        availableBooks.add(new Book("TDD", "Kent Beck", 2003));

        checkedOutBooks.put(new Book("NotAvailable", "Author", 0),"111-1111");
        checkedOutBooks.put(new Book("Harry Potter", "J K Rowling", 2000),"111-1111");
        checkedOutBooks.put(new Book("Java", "Kathy Sierra", 2001),"111-1111");
        bookManager = new BookManager(availableBooks,checkedOutBooks);
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
        assertTrue(bookManager.isBookInLibraryAvailable(book));
    }

    @Test
    public void isBookAvailableShouldReturnFalse() {
        Book book = new Book("T", "Kent Beck", 2000);
        assertFalse(bookManager.isBookInLibraryAvailable(book));
    }

    @Test
    public void checkedOutBookShouldNotBeAvailable(){
        Book book = new Book("TDD", "Kent Beck", 2003);
        bookManager.changeAvailability(book, false, loggedUser);
        assertFalse(bookManager.isBookInLibraryAvailable(book));
    }

    @Test
    public void returnedBookShouldBeAvailable(){
        Book book = new Book("NotAvailable", "Author", 0);
        bookManager.changeAvailability(book, true, loggedUser);
        assertTrue(bookManager.isBookInLibraryAvailable(book));
    }

    @Test
    public void returnNonExistentBook() {
        Book book = new Book("T", "Kent Beck", 2000);
        bookManager.changeAvailability(book, true, loggedUser);
        assertFalse(bookManager.isBookInLibraryAvailable(book));
    }

    @Test
    public void getBookDetails() {
        bookManager.getAvailableBookDetails("%s\t%s\t%s\n");
        assertEquals("TDD\tKent Beck\t2003\n", outContent.toString());
    }

    @Test
    public void askForWhoCheckedABook() {
        Book book = new Book("NotAvailable", "Author", 0);
        assertEquals("111-1111", bookManager.whoChecked(book));
    }
}

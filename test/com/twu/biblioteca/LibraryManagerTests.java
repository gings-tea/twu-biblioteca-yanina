package com.twu.biblioteca;

import com.twu.biblioteca.models.AbstractLibraryItem;
import com.twu.biblioteca.models.Book;
import com.twu.biblioteca.models.Movie;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Hashtable;

import static org.junit.Assert.*;

public class LibraryManagerTests {
    private LibraryManager libraryManager;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private String loggedUser;

    @Before
    public void beforeTest(){

        ArrayList<AbstractLibraryItem> availableItems = new ArrayList<>();
        Hashtable<AbstractLibraryItem, String> checkedOutItems = new Hashtable<>();

        availableItems.add(new Book("TDD", "Kent Beck", 2003));

        checkedOutItems.put(new Book("NotAvailable", "Author", 0),"111-1111");
        checkedOutItems.put(new Book("Harry Potter", "J K Rowling", 2000),"111-1111");
        checkedOutItems.put(new Book("Java", "Kathy Sierra", 2001),"111-1111");

        availableItems.add(new Movie("Interstellar", "Christopher Nolan",2014,"8.2"));
        libraryManager = new LibraryManager(availableItems,checkedOutItems);
        System.setOut(new PrintStream(outContent));
        loggedUser = "111-1111";
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void isItemAvailableShouldReturnTrue() {
        Book book = new Book("TDD", "Kent Beck", 2003);
        assertTrue(libraryManager.isItemInLibraryAvailable(book));
        Movie movie = new Movie("Interstellar", "Christopher Nolan",2014,"8.2");
        assertTrue(libraryManager.isItemInLibraryAvailable(movie));
    }

    @Test
    public void isItemAvailableShouldReturnFalse() {
        Book book = new Book("T", "Kent Beck", 2000);
        assertFalse(libraryManager.isItemInLibraryAvailable(book));
        Movie movie = new Movie("Not Available", "Director",1);
        assertFalse(libraryManager.isItemInLibraryAvailable(movie));
    }

    @Test
    public void checkedOutItemShouldNotBeAvailable(){
        Book book = new Book("TDD", "Kent Beck", 2003);
        libraryManager.changeItemAvailability(book, false, loggedUser);
        assertFalse(libraryManager.isItemInLibraryAvailable(book));
        Movie movie = new Movie("Interstellar", "Christopher Nolan",2014,"8.2");
        libraryManager.changeItemAvailability(movie, false, "");
        assertFalse(libraryManager.isItemInLibraryAvailable(movie));
    }

    @Test
    public void returnedBookShouldBeAvailable(){
        Book book = new Book("NotAvailable", "Author", 0);
        libraryManager.changeItemAvailability(book, true, loggedUser);
        assertTrue(libraryManager.isItemInLibraryAvailable(book));
    }

    @Test
    public void returnNonExistentBookShouldNotAddTheBook() {
        Book book = new Book("T", "Kent Beck", 2000);
        libraryManager.changeItemAvailability(book, true, loggedUser);
        assertFalse(libraryManager.isItemInLibraryAvailable(book));
    }

    @Test
    public void getBookDetailsShowsOnlyAvailableBooks() {
        libraryManager.getAvailableItemsDetails("book", "%s\t%s\t%s\n");
        assertEquals("TDD\tKent Beck\t2003\n", outContent.toString());
    }

    @Test
    public void getMovieDetailsShowsOnlyAvailableMovies() {
        libraryManager.getAvailableItemsDetails("movie", "%s\t%s\t%s\t%s\n");
        assertEquals("Interstellar\tChristopher Nolan\t2014\t8.2\n", outContent.toString());
    }

}

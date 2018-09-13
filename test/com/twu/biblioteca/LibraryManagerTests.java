package com.twu.biblioteca;

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
        Hashtable<Book, String> checkedOutBooks = new Hashtable<>();
        ArrayList<Book> availableBooks = new ArrayList<>();
        ArrayList<Movie> availableMovies = new ArrayList<>();

        availableBooks.add(new Book("TDD", "Kent Beck", 2003));

        checkedOutBooks.put(new Book("NotAvailable", "Author", 0),"111-1111");
        checkedOutBooks.put(new Book("Harry Potter", "J K Rowling", 2000),"111-1111");
        checkedOutBooks.put(new Book("Java", "Kathy Sierra", 2001),"111-1111");

        availableMovies.add(new Movie("Interstellar", "Christopher Nolan",2014,"8.2"));
        libraryManager = new LibraryManager(availableBooks,checkedOutBooks,availableMovies);
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
        assertTrue(libraryManager.isBookInLibraryAvailable(book));
    }

    @Test
    public void isBookAvailableShouldReturnFalse() {
        Book book = new Book("T", "Kent Beck", 2000);
        assertFalse(libraryManager.isBookInLibraryAvailable(book));
    }

    @Test
    public void checkedOutBookShouldNotBeAvailable(){
        Book book = new Book("TDD", "Kent Beck", 2003);
        libraryManager.changeBookAvailability(book, false, loggedUser);
        assertFalse(libraryManager.isBookInLibraryAvailable(book));
    }

    @Test
    public void returnedBookShouldBeAvailable(){
        Book book = new Book("NotAvailable", "Author", 0);
        libraryManager.changeBookAvailability(book, true, loggedUser);
        assertTrue(libraryManager.isBookInLibraryAvailable(book));
    }

    @Test
    public void returnNonExistentBookShouldNotAddTheBook() {
        Book book = new Book("T", "Kent Beck", 2000);
        libraryManager.changeBookAvailability(book, true, loggedUser);
        assertFalse(libraryManager.isBookInLibraryAvailable(book));
    }

    @Test
    public void getBookDetailsShowsOnlyAvailableBooks() {
        libraryManager.getAvailableBookDetails("%s\t%s\t%s\n");
        assertEquals("TDD\tKent Beck\t2003\n", outContent.toString());
    }

    @Test
    public void getMovieDetailsShowsOnlyAvailableMovies() {
        libraryManager.getAvailableMovieDetails("%s\t%s\t%s\t%s\n");
        assertEquals("Interstellar\tChristopher Nolan\t2014\t8.2\n", outContent.toString());
    }

    @Test
    public void isMovieAvailableShouldReturnTrue() {
        Movie movie = new Movie("Interstellar", "Christopher Nolan",2014,"8.2");
        assertTrue(libraryManager.isMovieInLibraryAvailable(movie));
    }

    @Test
    public void isMovieAvailableShouldReturnFalse() {
        Movie movie = new Movie("Not Available", "Director",1);
        assertFalse(libraryManager.isMovieInLibraryAvailable(movie));
    }

    @Test
    public void checkedOutMovieShouldNotBeAvailable(){
        Movie movie = new Movie("Interstellar", "Christopher Nolan",2014,"8.2");
        libraryManager.checkOutMovie(movie);
        assertFalse(libraryManager.isMovieInLibraryAvailable(movie));
    }

}

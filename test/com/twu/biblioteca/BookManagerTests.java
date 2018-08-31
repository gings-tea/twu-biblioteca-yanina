package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class BookManagerTests {
    private BookManager mockBookManager;

    @Before
    public void beforeTest(){
        mockBookManager = new BookManager();
        mockBookManager.addBook(new LibraryBook("TDD", "Kent Beck", 2003),true);
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


}

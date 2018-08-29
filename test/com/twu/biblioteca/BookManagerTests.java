package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class BookManagerTests {
    private LibraryBook mock_book;

    @Before
    public void beforeTest(){
        mock_book = new LibraryBook("TDD", "Kent Beck", 2003, false);
    }

    @Test
    public void getBookByTitleShouldReturnFalse() {
        BookManager bookManager = new BookManager();
        bookManager.addBook(mock_book);
        assertEquals(false, bookManager.isBookInLibrary("TDB"));
    }

    @Test
    public void checkOutBookShouldBeTrue(){
        BookManager bookManager = new BookManager();
        bookManager.addBook(mock_book);
        assertEquals(true, bookManager.changeCheckOutState("TDD", true));
    }


}

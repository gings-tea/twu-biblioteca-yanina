package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class BookManagerTests {
    private BookManager mockBookManager;

    @Before
    public void beforeTest(){
        mockBookManager = new BookManager();
        mockBookManager.addBook(new LibraryBook("TDD", "Kent Beck", 2003, false));
    }

    @Test
    public void getBookByTitleShouldReturnFalse() {

        assertEquals(false, mockBookManager.isBookInLibrary("TDB"));
    }

    @Test
    public void checkOutBookShouldBeTrue(){
        mockBookManager.changeCheckOutState("TDD", true);
        LibraryBook mockBook = mockBookManager.getBook("TDD");
        assertEquals(true, mockBook.isCheckedOut());
    }


}

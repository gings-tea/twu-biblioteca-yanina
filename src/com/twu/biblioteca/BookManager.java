package com.twu.biblioteca;

import java.util.Hashtable;
import java.util.Set;

public class BookManager {
    private Hashtable<LibraryBook, Boolean> libraryBookCollection;

    public BookManager(Hashtable<LibraryBook, Boolean> libraryBookCollection) {
        this.libraryBookCollection = libraryBookCollection;
    }


    public boolean isBookInAvailable(LibraryBook libraryBook) {
        return libraryBookCollection.get(libraryBook);
    }


    public void changeAvailability(LibraryBook libraryBook, boolean availability) {
        libraryBookCollection.put(libraryBook, availability);
    }

    public void getAvailableBookDetails(String format) {
        Set<LibraryBook> keys = libraryBookCollection.keySet();
        for(LibraryBook key: keys) {
            if (isBookInAvailable(key)) {
                System.out.printf(format, key.bookDetails());
            }
        }
    }

}

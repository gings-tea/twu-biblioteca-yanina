package com.twu.biblioteca;

import java.util.Hashtable;
import java.util.Set;

public class BookManager {

    private Hashtable<LibraryBook, Boolean> libraryBookCollection;

    public BookManager() {
        this.libraryBookCollection = new Hashtable<>();
    }

    public void addBook( LibraryBook libraryBook , boolean availability) {
        libraryBookCollection.put(libraryBook, availability);
    }

    public boolean isBookInAvailable(LibraryBook libraryBook) {
        return libraryBookCollection.get(libraryBook);
    }


    public void changeAvailability(LibraryBook libraryBook, boolean availability) {
        libraryBookCollection.put(libraryBook, availability);
    }

    public void getAvailableBookDetails() {
        Set<LibraryBook> keys = libraryBookCollection.keySet();
        for(LibraryBook key: keys){
            if(isBookInAvailable(key)){
                key.bookDetails();
            }
        }
    }
}

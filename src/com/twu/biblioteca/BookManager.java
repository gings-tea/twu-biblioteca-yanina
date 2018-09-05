package com.twu.biblioteca;

import java.util.Hashtable;
import java.util.Set;

public class BookManager {
    private Hashtable<LibraryBook, Boolean> libraryBookCollection;

    public BookManager(Hashtable<LibraryBook, Boolean> libraryBookCollection) {
        this.libraryBookCollection = libraryBookCollection;
    }

    public boolean isBookInLibraryAvailable(LibraryBook libraryBook) {
        if(isBookInLibrary(libraryBook)){
            return libraryBookCollection.get(libraryBook);
        }
        return false;
    }


    public boolean changeAvailability(LibraryBook libraryBook, boolean availability) {
        boolean changed = false;
        if(isBookInLibrary(libraryBook)){
            if (isBookInLibraryAvailable(libraryBook) ^ availability){
                libraryBookCollection.put(libraryBook, availability);
                changed = true;
            }
        }
        return changed;
    }

    public void getAvailableBookDetails(String format) {
        Set<LibraryBook> keys = libraryBookCollection.keySet();
        for(LibraryBook key: keys) {
            if (isBookInLibraryAvailable(key)) {
                System.out.printf(format, key.bookDetails());
            }
        }
    }

    private boolean isBookInLibrary(LibraryBook libraryBook){
        return libraryBookCollection.containsKey(libraryBook);
    }

}

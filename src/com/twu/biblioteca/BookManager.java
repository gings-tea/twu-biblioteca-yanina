package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.Iterator;

public class BookManager {

    private ArrayList<LibraryBook> libraryBookCollection;

    public BookManager() {
        this.libraryBookCollection = new ArrayList<>();
    }

    public void addBook( LibraryBook mock_book ) {
        libraryBookCollection.add(mock_book);
    }

    public boolean isBookInLibrary(String title) {
        return getBook(title) != null;
    }


    public boolean changeCheckOutState(String title, boolean checkedOut) {
        if(isBookInLibrary(title)) {
            LibraryBook book = getBook(title);
            libraryBookCollection.remove(book);
            book.setCheckedOut(checkedOut);
            libraryBookCollection.add(book);
            return true;
        }
        return false;
    }

    private LibraryBook getBook(String title){
        Iterator it = libraryBookCollection.iterator();
        while( it.hasNext() ){
            LibraryBook book = (LibraryBook) it.next();
            if( book.equalsBookTitleWithGivenTitle(title) ){
                return book;
            }
        }
        return null;
    }
}

package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.Hashtable;

public class BookManager {
    private Hashtable<Book, String> checkedOutBooks;
    private ArrayList<Book> availableBooks;

    public BookManager() {
        fillBookManager();
    }

    public BookManager(ArrayList availableBooks, Hashtable checkedOutBooks){
        this.availableBooks = availableBooks;
        this.checkedOutBooks = checkedOutBooks;
    }

    public boolean isBookInLibraryAvailable(Book book) {
        return availableBooks.contains(book);
    }


    public boolean changeAvailability(Book book, boolean returning, String libraryId) {
        boolean changed;
        if(returning){
            changed = returnBook(book);
        } else {
            changed = checkOutBook(book,libraryId);
        }
        return changed;
    }

    public void getAvailableBookDetails(String format) {
        for(Book book: availableBooks) {
            if (isBookInLibraryAvailable(book)) {
                System.out.printf(format, book.bookDetails());
            }
        }
    }

    public String whoChecked(Book book) {
        return "111-1111";
    }



    private boolean isBookInLibraryNotAvailable(Book book){
        return checkedOutBooks.containsKey(book);
    }

    private boolean returnBook(Book book) {
        if(isBookInLibraryNotAvailable(book)){
            checkedOutBooks.remove(book);
            availableBooks.add(book);
            return true;
        }
        return false;
    }

    private boolean checkOutBook(Book book, String libraryId) {
        if(isBookInLibraryAvailable(book)){
            availableBooks.remove(book);
            checkedOutBooks.put(book, libraryId);
            return true;
        }
        return false;
    }

    private void fillBookManager(){
        availableBooks.add(new Book("TDD", "Kent Beck", 2003));
        availableBooks.add(new Book("Crime and Punishment", "Fyodor Dostoyevsky", 1935));

        checkedOutBooks.put(new Book("Go in Action", "William Kennedy", 2010),"111-1111");
        checkedOutBooks.put(new Book("Harry Potter I", "J K Rowling", 2000),"111-1112");
        checkedOutBooks.put(new Book("Java", "Kathy Sierra", 2001),"111-1113");
    }
}

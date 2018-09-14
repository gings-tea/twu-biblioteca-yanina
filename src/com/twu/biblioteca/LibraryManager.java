package com.twu.biblioteca;

import com.twu.biblioteca.models.AbstractLibraryItem;
import com.twu.biblioteca.models.Book;
import com.twu.biblioteca.models.Movie;

import java.util.ArrayList;
import java.util.Hashtable;

public class LibraryManager {

    private ArrayList<AbstractLibraryItem> availableItems;
    private Hashtable<AbstractLibraryItem, String> checkedOutItems;

    public LibraryManager() {
        fillLibraryItems();
    }

    public LibraryManager(ArrayList availableItems, Hashtable checkedOutItems){
        this.availableItems = availableItems;
        this.checkedOutItems = checkedOutItems;
    }

    public boolean changeItemAvailability(AbstractLibraryItem item, boolean isReturning, String libraryId) {
        boolean changed;
        if(isReturning){
            changed = returnItem(item);
        } else {
            changed = checkOutItem(item, libraryId);
        }
        return changed;
    }

    public void getAvailableItemsDetails(String type, String format) {
        for(AbstractLibraryItem item: availableItems) {
            if(item.isSameType(type))
                System.out.printf(format, item.getDetails());
        }
    }

    public boolean isItemInLibraryAvailable(AbstractLibraryItem item) {
        return availableItems.contains(item);
    }

    private boolean isItemInLibraryNotAvailable(AbstractLibraryItem item){
        return checkedOutItems.containsKey(item);
    }

    private boolean returnItem(AbstractLibraryItem item) {
        if(isItemInLibraryNotAvailable(item)){
            checkedOutItems.remove(item);
            availableItems.add(item);
            return true;
        }
        return false;
    }

    private boolean checkOutItem(AbstractLibraryItem item, String libraryId) {
        if(isItemInLibraryAvailable(item)){
            availableItems.remove(item);
            checkedOutItems.put(item, libraryId);
            return true;
        }
        return false;
    }

    private void fillLibraryItems(){

        availableItems = new ArrayList<>();
        availableItems.add(new Book("TDD", "Kent Beck", 2003));
        availableItems.add(new Book("Crime and Punishment", "Fyodor Dostoyevsky", 1935));
        availableItems.add(new Movie("The Godfather", "Coppola", 1972, "9.2"));
        availableItems.add(new Movie("Gone Girl", "David Fincher", 2014, "8.1"));

        checkedOutItems = new Hashtable<>();

        checkedOutItems.put(new Book("Go in Action", "William Kennedy", 2010),"123-4567");
        checkedOutItems.put(new Book("Harry Potter I", "J K Rowling", 2000),"123-4567");
        checkedOutItems.put(new Book("Java", "Kathy Sierra", 2001),"987-6543");

    }
}

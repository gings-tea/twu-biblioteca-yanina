package com.twu.biblioteca;

import java.util.Scanner;

public class UI {
    private String welcomeMessage = "*** WELCOME TO THE TWU LIBRARY ***";
    private String optionList = "1) List all books\n2) Check out book\n3) Return book\n4) Quit\nPlease select an option: ";
    private String format = "%-10s  %-25s  %-5s \n";

    public void welcomeMessagePrinter(){
        System.out.println(welcomeMessage);
    }

    public void optionManager(){
        Scanner sc = new Scanner(System.in);
        int n;
        LibraryBook libraryBook;
        DBManager dbManager = new DBManager();
        BookManager bookManager = new BookManager(dbManager.getDB());
        do{
            System.out.print(optionList);
            n = Integer.valueOf(sc.nextLine());
            switch(n){
                case 1:
                    System.out.println("\nAvailable books");
                    System.out.printf(format,"Title","Author","Year Published");
                    bookManager.getAvailableBookDetails(format);
                    System.out.println();
                    break;
                case 2:
                    libraryBook = enterLibraryBookInformation(sc);
                    if(bookManager.isBookInLibraryAvailable(libraryBook)){
                        bookManager.changeAvailability(libraryBook, false);
                        System.out.println("Thank you! Enjoy the book");
                    }else {
                        System.out.println("That book is not available.");
                    }
                    break;
                case 3:
                    libraryBook = enterLibraryBookInformation(sc);
                    if(bookManager.isBookInLibrary(libraryBook)){
                        if(!bookManager.isBookInLibraryAvailable(libraryBook)){
                            bookManager.changeAvailability(libraryBook, true);
                            System.out.println("Thank you for returning the book");
                            break;
                        }
                    }
                    System.out.println("That is not a valid book to return.");
                    break;
                case 4:
                    break;
                default:
                    System.out.println();
                    System.out.println("Select a valid option!");
                    System.out.println();
            }
        } while(n != 4);
    }

    private LibraryBook enterLibraryBookInformation(Scanner sc){
        System.out.print("Please enter the book title: ");
        String title = sc.nextLine();
        System.out.print("Please enter the book author/s: ");
        String author = sc.nextLine();
        System.out.print("Please enter the book year of publication: ");
        int yearPublished = Integer.valueOf(sc.nextLine());
        return new LibraryBook(title, author, yearPublished);
    }
}

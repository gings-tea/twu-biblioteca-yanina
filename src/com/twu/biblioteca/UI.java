package com.twu.biblioteca;

import com.twu.biblioteca.models.Book;

import java.util.Scanner;

class UI {
    private String welcomeMessage = "*** WELCOME TO THE TWU LIBRARY ***";
    private String optionList = "\n1) List all books\n2) Check out book\n3) Return book\n4) Quit\nPlease select an option: ";
    private String format = "%-10s  %-25s  %-5s \n";
    private String returnOkMsg = "Thank you for returning the book";
    private String returnErrorMsg = "That is not a valid book to return.";
    private String checkOutOkMsg = "Thank you! Enjoy the book";
    private String checkOutErrorMsg = "That book is not available.";
    BookManager bookManager;

    UI() {
        bookManager = new BookManager();
    }

    void welcomeMessagePrinter(){
        System.out.println(welcomeMessage);
    }

    void optionManager(){
        Scanner sc = new Scanner(System.in);
        int optionSelected;
        Book book;

        do{
            System.out.print(optionList);
            optionSelected = Integer.valueOf(sc.nextLine());
            switch(optionSelected){
                case 1:
                    listAllBooks();
                    break;
                case 2:
                    book = enterLibraryBookInformation(sc);
                    modifyAvailabilityOfBook(book, false, checkOutOkMsg, checkOutErrorMsg);
                    break;
                case 3:
                    book = enterLibraryBookInformation(sc);
                    modifyAvailabilityOfBook(book, true, returnOkMsg, returnErrorMsg);
                    break;
                case 4:
                    break;
                default:
                    System.out.println();
                    System.out.println("Select a valid option!");
                    System.out.println();
            }
        } while(optionSelected != 4);
    }


    private void modifyAvailabilityOfBook(Book book, boolean availability, String successMsg, String errorMsg) {
        System.out.println();
        // TODO: Cambiar el usuario
        if (bookManager.changeAvailability(book, availability,"111-1111")){
            System.out.println(successMsg);
        } else {
            System.out.println(errorMsg);
        }
    }


    private Book enterLibraryBookInformation(Scanner sc){
        System.out.println();
        System.out.print("Please enter the book title: ");
        String title = sc.nextLine();
        System.out.print("Please enter the book author/s: ");
        String author = sc.nextLine();
        System.out.print("Please enter the book year of publication: ");
        int yearPublished = Integer.valueOf(sc.nextLine());
        return new Book(title, author, yearPublished);
    }

    private void listAllBooks(){
        System.out.println();
        System.out.println("Available books");
        System.out.printf(format,"Title","Author","Year Published");
        bookManager.getAvailableBookDetails(format);
    }
}

package com.twu.biblioteca;

import java.util.Scanner;

public class UI {
    private String welcomeMessage = "*** WELCOME TO THE TWU LIBRARY ***";
    private String optionList = "1) List all books\n2) Check out book\n3) Check in book\n4) Quit\nPlease select an option: ";
    private String format = "%-10s  %-25s  %-5s \n";
    public void welcomeMessagePrinter(){
        System.out.println(welcomeMessage);
    }
    public void optionManager(){
        Scanner sc = new Scanner(System.in);
        int n;
        DBManager dbManager = new DBManager();
        BookManager bookManager = new BookManager(dbManager.getDB());
        do{
            System.out.print(optionList);
            n = sc.nextInt();
            switch(n){
                case 1:
                    System.out.println("\nAvailable books");
                    System.out.printf(format,"Title","Author","Year Published");
                    bookManager.getAvailableBookDetails(format);
                    System.out.println();
                case 2:
                    break;
                case 3:
                    break;
            }
        } while(n != 4);
    }
}

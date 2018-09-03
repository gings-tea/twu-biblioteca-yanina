package com.twu.biblioteca;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Hashtable;

public class DBManager {

    private String filename = "DB/libraryDB";

    public Hashtable<LibraryBook, Boolean> getDB(){
        Hashtable<LibraryBook, Boolean> libraryBookCollection = new Hashtable<>();
        try{

            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String line;
            while ((line = reader.readLine()) != null)
            {
                String[] parts = line.split("\t");
                libraryBookCollection.put(new LibraryBook(parts[0],parts[1],Integer.valueOf(parts[2])),Boolean.valueOf(parts[3]));
            }
        }catch (IOException e){
            System.out.println("DB file doesn't exists :c");
        }
        return libraryBookCollection;
    }
}

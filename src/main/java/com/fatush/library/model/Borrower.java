package com.fatush.library.model;

import java.util.ArrayList;
import java.util.List;

public class Borrower {

    private int id = 1;
    private String name;
    private List<String> borrowedBooks = new ArrayList<>();
    private static int idCounter = 1;

    public Borrower(String name) {
        this.name = name;
        this.setId(id);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = idCounter++;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getBorrowedBooks() {
        return borrowedBooks;
    }

    public void setBorrowedBooks(List<String> borrowedBooks) {
        this.borrowedBooks = borrowedBooks;
    }
}
package com.fatush.library.model;

import java.util.Collection;

public class Borrower {

    private int id = 1;
    private String name;
    private Collection<Book> borrowedBooks;
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

    public Collection<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    public void setBorrowedBooks(Collection<Book> borrowedBooks) {
        this.borrowedBooks = borrowedBooks;
    }
}

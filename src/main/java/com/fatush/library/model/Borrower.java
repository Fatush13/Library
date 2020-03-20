package com.fatush.library.model;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Borrower {

    private int id = 1;
    private static AtomicInteger count = new AtomicInteger(0);
    private String name;
    private List<String> borrowedBooks = new ArrayList<>();

    public Borrower() {
    }

    public Borrower(String name) {
        this.name = name;
        this.setId(count.incrementAndGet());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static AtomicInteger getCount() {
        return count;
    }

    public static void setCount(AtomicInteger count) {
        Borrower.count = count;
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
package com.fatush.library.model;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

public class Book {

    private int id = 1;
    private static AtomicInteger count = new AtomicInteger(0);
    private String name;
    private int age;                 //age in days
    private Calendar registerDate;     //date the book had been added to the library
    private boolean isOld;
    private Borrower borrower;
    private Calendar borrowDate;

    public Book() {
    }

    public Book(String name, int age) {
        this.name = name;
        this.age = age;
        this.setRegisterDate();
        this.setId(id);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = count.incrementAndGet();
    }

    public static AtomicInteger getCount() {
        return count;
    }

    public static void setCount(AtomicInteger count) {
        Book.count = count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Calendar getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate() {
        registerDate = Calendar.getInstance();
    }

    public boolean isOld() {
        return isOld;
    }

    public void setOld(boolean old) {
        isOld = old;
    }

    public Borrower getBorrower() {
        return borrower;
    }

    public void setBorrower(Borrower borrower) {
        this.borrower = borrower;
    }

    public Calendar getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate() {
        if (this.borrowDate == null) {
            this.borrowDate = Calendar.getInstance();
        } else this.borrowDate = null;
    }
}

package com.fatush.library.model;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.atomic.AtomicInteger;

public class Book {

    private int id;
    private static AtomicInteger count = new AtomicInteger(0);
    private String name;
    private int registerDate;
    private int borrowDate;
    private int expireDate;
    private boolean isOld;
    private boolean isExpired;
    private Borrower borrower;

    public Book() {
    }

    public Book(String name) {
        this.name = name;
        this.setRegisterDate();
        this.setId(count.incrementAndGet());
        this.setBorrowDate();
        this.setOld(false);
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
        Book.count = count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        this.registerDate = Integer.parseInt(dateFormat.format(Calendar.getInstance().getTime()));
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

    public int getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate() {
        if(this.borrowDate == 0) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
            this.borrowDate = Integer.parseInt(dateFormat.format(Calendar.getInstance().getTime()));
        } else this.borrowDate = 0;
    }

    public boolean isExpired() {
        return isExpired;
    }

    public void setExpired(boolean expired) {
        isExpired = expired;
    }

    public int getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(int expireDate) {
        this.expireDate = expireDate;
    }
}
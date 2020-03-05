package com.fatush.library.dao;

import com.fatush.library.model.Book;
import com.fatush.library.model.Borrower;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
public class BorrowDao{

    private static Map<Integer, Borrower> borrowers;

    static {
        borrowers = new HashMap<>() {
            {
                put(1, new Borrower("Henry"));
                put(2, new Borrower("George"));
                put(3, new Borrower("Michael"));
            }
        };
    }

    public Collection<Borrower> getBorrowers() {
        return borrowers.values();
    }

    public void addBorrower(Borrower borrower) {
        borrowers.put(borrower.getId(), borrower);
    }

    public void removeBorrower(int id) {
        borrowers.remove(id);
    }

    public void removeBook(Borrower borrower, Book book) {
        borrower.getBorrowedBooks().remove(book);
    }

    public void addBorrowed(Book book, Borrower borrower) {
            borrower.getBorrowedBooks().add(book.getName());
            book.setBorrower(borrower);
            book.setBorrowDate();
    }

    public void changeBorrowDate(Book book) {
        book.setBorrowDate();
    }

    public void removeBorrower(Book book) {
        book.setBorrower(null);
        book.setBorrowDate();
    }
}

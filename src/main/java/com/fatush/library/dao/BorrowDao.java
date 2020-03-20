package com.fatush.library.dao;

import com.fatush.library.model.Book;
import com.fatush.library.model.Borrower;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
public class BorrowDao {

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
        borrowers.put(Book.getCount().intValue() + 1, new Borrower(borrower.getName()));
    }

    public void removeBorrower(int id) {
        borrowers.remove(id);
    }

    public void removeBook(Borrower borrower, Book book) {
        borrower.getBorrowedBooks().remove(book.getName());
    }

    public void addBorrowed(Book book, Borrower borrower) {
        borrower.getBorrowedBooks().add(book.getName());
        book.setBorrower(borrower);
        book.setBorrowDate();
    }

    public void removeBorrower(Book book) {
        book.setBorrower(null);
        book.setBorrowDate();
        book.setExpireDate(0);
    }
}

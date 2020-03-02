package com.fatush.library.dao;

import com.fatush.library.model.Book;
import com.fatush.library.model.Borrower;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public class BorrowDao {

    public Collection<Book> getBooks(Borrower borrower) {
        return borrower.getBorrowedBooks();
    }

    public void addBook(Borrower borrower, Book book) {
        borrower.getBorrowedBooks().add(book);
    }

    public void removeBook(Borrower borrower, Book book) {
        borrower.getBorrowedBooks().remove(book);
    }
}

package com.fatush.library.service;

import com.fatush.library.dao.BookDao;
import com.fatush.library.dao.BorrowDao;
import com.fatush.library.model.Book;
import com.fatush.library.model.Borrower;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class BorrowService {

    private BorrowDao borrowDao;
    private BookDao bookDao;

    @Autowired
    public BorrowService(BorrowDao borrowDao, BookDao bookDao){
        this.borrowDao = borrowDao;
        this.bookDao = bookDao;
    }

    public Collection<Book> getAll(Borrower borrower) {
        return borrowDao.getBooks(borrower);
    }

    public void addBorrowerBook(Borrower borrower, Book book) {
        borrowDao.addBook(borrower, book);
        bookDao.addBorrower(book, borrower);
        bookDao.changeBorrowDate(book);
    }

    public void removeBorrowerBook(Borrower borrower, Book book) {
        borrowDao.removeBook(borrower,book);
        bookDao.removeBorrower(book);
        bookDao.changeBorrowDate(book);
    }

}

package com.fatush.library.service;

import com.fatush.library.dao.BorrowDao;
import com.fatush.library.exceptions.NotFoundException;
import com.fatush.library.model.Book;
import com.fatush.library.model.Borrower;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;

@Service
public class BorrowService {

    private BorrowDao borrowDao;
    private BookService bookService;

    @Autowired
    public BorrowService(BorrowDao borrowDao, BookService bookService) {
        this.borrowDao = borrowDao;
        this.bookService = bookService;
    }

    public void addBorrowedBook(Borrower borrower, Book book) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        int currentDate = Integer.parseInt(dateFormat.format(Calendar.getInstance().getTime()));
        borrowDao.addBorrowed(book, borrower);
        if (bookService.checkIfOld(book) || bookService.checkBookShortage(book)) {
            book.setExpireDate(currentDate + 7);
        } else book.setExpireDate(currentDate + 30);

    }

    public void removeBorrowedBook(Borrower borrower, Book book) {
        borrowDao.removeBook(borrower, book);
        borrowDao.removeBorrower(book);
    }

    public Collection<Borrower> getAll() {
        return borrowDao.getBorrowers();
    }

    public Borrower getBorrowerById(String id) {
        return borrowDao.getBorrowers().stream()
                .filter(borrower -> String.valueOf(borrower.getId()).equals(id))
                .findFirst()
                .orElseThrow(NotFoundException::new);
    }

    public void addBorrower(Borrower borrower) {
        borrowDao.addBorrower(borrower);
    }

    public void removeBorrower(int id) {
        borrowDao.removeBorrower(id);
    }
}

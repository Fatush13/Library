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
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BorrowService {

    private BorrowDao borrowDao;
    private BookService bookService;

    @Autowired
    public BorrowService(BorrowDao borrowDao, BookService bookService) {
        this.borrowDao = borrowDao;
        this.bookService = bookService;
    }

    public String addBorrowedBook(Borrower borrower, Book book) {
        if (book.getBorrower() != null) {
            return "Book is already borrowed";
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        int currentDate = Integer.parseInt(dateFormat.format(Calendar.getInstance().getTime()));
        borrowDao.addBorrowed(book, borrower);
        if (bookService.checkIfOld(book) || bookService.checkBookShortage(book)) {
            book.setExpireDate(currentDate + 7);
        } else book.setExpireDate(currentDate + 30);
        return "Book has been successfully borrowed";
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

    public List<Borrower> getBorrowerByName(String name) {
        return borrowDao.getBorrowers().stream()
                .filter(borrower -> String.valueOf(borrower.getName()).equals(name))
                .collect(Collectors.toList());
    }

    public void addBorrower(Borrower borrower) {
        if (!borrower.getName().isEmpty()) {
            borrowDao.addBorrower(borrower);
        } else throw new IllegalArgumentException("Borrower name cannot be empty");
    }

    public void removeBorrower(int id) {
        borrowDao.removeBorrower(id);
    }
}

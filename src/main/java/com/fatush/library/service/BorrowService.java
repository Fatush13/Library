package com.fatush.library.service;

import com.fatush.library.dao.BorrowDao;
import com.fatush.library.exceptions.NotFoundException;
import com.fatush.library.model.Book;
import com.fatush.library.model.Borrower;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;

@Service
public class BorrowService {

    private final Logger logger = LogManager.getLogger(this.getClass());

    private UserDetailsServiceImpl userDetailsService;
    private BorrowDao borrowDao;
    private BookService bookService;

    @Autowired
    public BorrowService(BorrowDao borrowDao, BookService bookService, UserDetailsServiceImpl userDetailsService) {
        this.borrowDao = borrowDao;
        this.bookService = bookService;
        this.userDetailsService = userDetailsService;
    }

    public String addBorrowedBook(Borrower borrower, Book book, UserDetails user) {
        if (book.getBorrower() != null) {
            logger.error("Prevented borrowing a book twice");

            return "Book is already borrowed";
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        int currentDate = Integer.parseInt(dateFormat.format(Calendar.getInstance().getTime()));
        borrowDao.addBorrowed(book, borrower);
        if (bookService.checkIfOld(book) || bookService.checkBookShortage(book)) {
            book.setExpireDate(currentDate + 7);
        } else
            book.setExpireDate(currentDate + 30);
        logger.warn(user.getUsername() + " has lent a book \"" + book.getName() +
                "\" to " + borrower.getName() + " until " + book.getExpireDate());

        return "Book has been successfully borrowed";
    }


    public void removeBorrowedBook(Borrower borrower, Book book, UserDetails user) {
        borrowDao.removeBook(borrower, book);
        borrowDao.removeBorrower(book);
        logger.warn("Book \"" + book.getName() + "\" with id: " + book.getId() + " has been returned to " + user.getUsername());
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
        if (borrower.getName() != null) {
            if (!borrower.getName().isEmpty()) {
                borrowDao.addBorrower(borrower);
                logger.warn("New borrower \"" + borrower.getName() + "\" has been successfully added");
            } else {
                logger.error("Prevented adding new borrower with empty name");

                throw new IllegalArgumentException("Borrower name cannot be empty");
            }
        } else
            logger.error("Prevented adding new borrower without name parameter");

        throw new IllegalArgumentException("Borrower name cannot be empty");
    }

    public void removeBorrower(int id) {
        borrowDao.removeBorrower(id);
        logger.warn("Borrower " + getBorrowerById(Integer.toString(id)).getName() + " with id: " + id + " has been removed");
    }
}

package com.fatush.library.service;

import com.fatush.library.dao.BookDao;
import com.fatush.library.dao.BorrowerDao;
import com.fatush.library.exceptions.NotFoundException;
import com.fatush.library.model.Book;
import com.fatush.library.model.Borrower;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class BorrowerService {

    private final BorrowerDao borrowerDao;
    private final BookDao bookDao;

    @Autowired
    public BorrowerService (BorrowerDao borrowerDao, BookDao bookDao) {
        this.borrowerDao = borrowerDao;
        this.bookDao = bookDao;
    }

    public Collection<Borrower> getAll() {
        return borrowerDao.getBorrowers();
    }

    public Borrower getBorrowerById(String id) {
        return borrowerDao.getBorrowers().stream()
                .filter(borrower -> String.valueOf(borrower.getId()).equals(id))
                .findFirst()
                .orElseThrow(NotFoundException::new);
    }

    public void addBorrower(Borrower borrower) {
        borrowerDao.add(borrower);
    }

    public void removeBorrower(int id) {
        borrowerDao.remove(id);
    }

}

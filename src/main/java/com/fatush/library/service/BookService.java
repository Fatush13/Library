package com.fatush.library.service;

import com.fatush.library.dao.BookDao;
import com.fatush.library.exceptions.NotFoundException;
import com.fatush.library.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {

    private final BookDao bookDao;

    @Autowired
    public BookService(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    public Collection<Book> getAll() {
        bookDao.getBooks().forEach(book -> {
            checkIfOld(book);
            checkIfExpired(book);
        });

        return bookDao.getBooks();
    }

    public Book getBookById(String id) {

        return bookDao.getBooks().stream()
                .filter(book -> {
                    checkIfExpired(book);
                    checkIfOld(book);
                    return String.valueOf(book.getId()).equals(id);
                })
                .findFirst()
                .orElseThrow(NotFoundException::new);
    }

    public List<Book> getBooksByName(String name) {
        return bookDao.getBooks().stream()
                .filter(book -> book.getName().equals(name))
                .collect(Collectors.toList());
    }

    public Book addBook(Book book) {
        if (!book.getName().isEmpty()) {
            bookDao.add(book);
            return book;
        } else throw new IllegalArgumentException("Book name cannot be empty");
    }

    public void updateBook(int id, Book book) {
        if (!book.getName().isEmpty()) {
            Book bookFromDB = getBookById(Integer.toString(id));
            bookFromDB.setName(book.getName());
            bookDao.update(id, bookFromDB);
        }
    }

    public void removeBook(int id) {
        bookDao.remove(id);
    }

    public boolean checkIfOld(Book book) {
        if (book.getId() <= bookDao.getBooks().size()) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
            int currentDate = Integer.parseInt(dateFormat.format(Calendar.getInstance().getTime()));
            int registryDate = book.getRegisterDate();
            if (registryDate > currentDate + 90) {
                book.setOld(true);
                return true;
            }
        }
        return false;
    }

    public boolean checkBookShortage(Book book) {

        return getBooksByName(book.getName()).size() < 5;
    }

    public boolean checkIfExpired(Book book) {
        if (book.getId() <= bookDao.getBooks().size()) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
            int currentDate = Integer.parseInt(dateFormat.format(Calendar.getInstance().getTime()));

            if (book.isOld()) {
                return book.getBorrowDate() + 28 > currentDate;
            }
            if (!book.isOld()) {
                return book.getBorrowDate() + 7 > currentDate;
            }
            book.setExpired(true);
            return true;
        }
        return false;
    }
}

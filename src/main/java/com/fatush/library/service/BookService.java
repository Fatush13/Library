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
        return bookDao.getBooks();
    }

    public Book getBookById(String id) {
        return bookDao.getBooks().stream()
                .filter(book -> String.valueOf(book.getId()).equals(id))
                .findFirst()
                .orElseThrow(NotFoundException::new);
    }

    public Book getBookByName(String name) {
        return bookDao.getBooks().stream()
                .filter(book -> book.getName().equals(name))
                .findFirst()
                .orElseThrow(NotFoundException::new);
    }

    public List<Book> getSameBooks(String name) {
        return bookDao.getBooks().stream()
                .filter(book -> book.getName().equals(name))
                .collect(Collectors.toList());
    }

    public void addBook(Book book) {
        bookDao.add(book);
    }

    public void updateBook(int id, Book book) {
        bookDao.update(id, book);
    }

    public void removeBook(int id) {
        bookDao.remove(id);
    }

    public boolean checkIfOld(Book book) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        int currentDate = Integer.parseInt(dateFormat.format(Calendar.getInstance().getTime()));
        int registryDate = book.getRegisterDate();
        if (registryDate > currentDate + 90) {
            book.setOld(true);
            return true;
        } else return false;
    }

    public boolean checkBookShortage(Book book) {

        return getSameBooks(book.getName()).size() < 5;
    }
}

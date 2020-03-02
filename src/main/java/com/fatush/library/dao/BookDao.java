package com.fatush.library.dao;

import com.fatush.library.model.Book;
import com.fatush.library.model.Borrower;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
public class BookDao {

    private static Map<Integer, Book> books;

    static {
        books = new HashMap<>() {
            {
                put(1, new Book("Lord of the rings", 5));
                put(2, new Book("Witcher", 1));
                put(3, new Book("Shantaram", 3));
            }
        };
    }

    public Collection<Book> getBooks() {
        return books.values();
    }

    public void add(Book book) {

        books.put(book.getCount().intValue()+1, new Book(book.getName(), book.getAge()));
    }

    public void update(int id, Book book) {
        books.put(id, book);
    }

    public void remove(int id) {
        books.remove(id);
    }

    public void addBorrower(Book book, Borrower borrower) {
        book.setBorrower(borrower);
    }

    public void changeBorrowDate(Book book) {
        book.setBorrowDate();
    }

    public void removeBorrower(Book book) {
        book.setBorrower(null);
    }
}

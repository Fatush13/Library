package com.fatush.library.dao;

import com.fatush.library.model.Book;
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
                put(1, new Book("Lord of the rings"));
                put(2, new Book("Witcher"));
                put(3, new Book("Shantaram"));
            }
        };
    }

    public Collection<Book> getBooks() {
        return books.values();
    }

    public void add(Book book) {
        books.put(book.getCount().intValue() + 1, new Book(book.getName()));
    }

    public void update(int id, Book book) {
        book.setId(id);
        books.put(id, book);
    }

    public void remove(int id) {
        books.remove(id);
    }
}

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
                put(1, new Book("The Master and Margarita"));
                put(2, new Book("The Witcher"));
                put(3, new Book("Shantaram"));
                put(4, new Book("The Witcher"));
                put(5, new Book("The Witcher"));
                put(6, new Book("The Witcher"));
                put(7, new Book("Game of Thrones"));
                put(8, new Book("The Witcher"));
            }
        };
    }

    public Collection<Book> getBooks() {
        return books.values();
    }

    public Book getBook(int id) {
        return books.get(id);
    }

    public void add(Book book) {
        books.put(Book.getCount().intValue() + 1, new Book(book.getName()));
    }

    public void update(int id, Book book) {
        book.setId(id);
        books.put(id, book);
    }

    public void remove(int id) {
        books.remove(id);
    }
}

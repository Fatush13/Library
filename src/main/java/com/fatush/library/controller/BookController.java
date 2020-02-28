package com.fatush.library.controller;

import com.fatush.library.exceptions.NotFoundException;
import com.fatush.library.model.Book;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("book")
@RestController
public class BookController {
    private int counter = 4;

    public List<Book> books = new ArrayList<>() {{
        add(new Book(1, "Lord of the rings", 5));
        add(new Book(2, "Witcher", 1));
        add(new Book(3, "Shantaram", 3));
    }};


    @GetMapping
    public List<Book> list() {
        return books;
    }

    @GetMapping("{id}")
    public Book getOne(@PathVariable String id) {
        return getBook(id);
    }

    public Book getBook(@PathVariable String id) {

        return books.stream()
                .filter(book -> String.valueOf(book.getId()).equals(id))
                .findFirst()
                .orElseThrow(NotFoundException::new);
    }

    @PostMapping
    public Book create(@RequestBody Book book) {

        books.add(counter, book);
        counter++;

        return book;
    }

    @PutMapping("{id}")
    public Book update(
            @PathVariable int id,
            @RequestBody Book book
    ) {
        books.set(id - 1, book);

        return book;
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable int id) {
        books.remove(id - 1);
    }
}

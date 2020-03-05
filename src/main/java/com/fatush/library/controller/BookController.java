package com.fatush.library.controller;

import com.fatush.library.model.Book;
import com.fatush.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RequestMapping("book")
@RestController
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public Collection<Book> getAllBooks() {

        return bookService.getAll();
    }

    @GetMapping("{id}")
    public Book getOneBook(@PathVariable String id) {

        return bookService.getBookById(id);
    }

    @PostMapping
    public Book addBook(@RequestBody Book book) {
        bookService.addBook(book);

        return book;
    }

    @PutMapping("{id}")
    public Book updateBook(
            @PathVariable int id,
            @RequestBody Book book
    ) {
        bookService.updateBook(id, book);

        return book;
    }

    @DeleteMapping("{id}")
    public void deleteBook(@PathVariable int id) {
        bookService.removeBook(id);
    }
}

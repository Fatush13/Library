package com.fatush.library.controller;

import com.fatush.library.model.Book;
import com.fatush.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("hasRole('ADMIN')")
    public Book addBook(@RequestBody Book book) {

        return bookService.addBook(book);

    }

    @PutMapping("{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Book updateBook(
            @PathVariable int id,
            @RequestBody Book book
    ) {
        bookService.updateBook(id, book);

        return book;
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteBook(@PathVariable int id) {
        bookService.removeBook(id);
    }
}

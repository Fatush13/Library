package com.fatush.library.controller;

import com.fatush.library.model.Book;
import com.fatush.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public Collection<Book> collection() {

        return bookService.getAllBooks();
    }

    public Book getBook(@PathVariable String id) {

        return bookService.getBookById(id);
    }

    @GetMapping("{id}")
    public Book getOne(@PathVariable String id) {

        return getBook(id);
    }
}

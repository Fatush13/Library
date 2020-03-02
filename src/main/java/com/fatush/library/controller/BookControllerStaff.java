package com.fatush.library.controller;

import com.fatush.library.model.Book;
import com.fatush.library.service.BookService;
import com.fatush.library.service.BorrowerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("book")
@RestController
public class BookControllerStaff {

    private BookService bookService;
    private BorrowerService borrowerService;

    @Autowired
    public BookControllerStaff(BookService bookService, BorrowerService borrowerService) {
        this.bookService = bookService;
        this.borrowerService = borrowerService;
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

package com.fatush.library.controller;

import com.fatush.library.model.Borrower;
import com.fatush.library.service.BookService;
import com.fatush.library.service.BorrowerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/borrower")
public class BorrowerController {

    private BorrowerService borrowerService;
    private BookService bookService;

    @Autowired
    public BorrowerController(BorrowerService borrowerService, BookService bookService) {
        this.borrowerService = borrowerService;
        this.bookService = bookService;
    }

    @GetMapping
    public Collection<Borrower> getAllBorrowers() {

        return borrowerService.getAll();
    }

    public Borrower getBorrower(@PathVariable String id) {

        return borrowerService.getBorrowerById(id);
    }

    @GetMapping("{id}")
    public Borrower getBorrowedBooks(@PathVariable String id) {

        return getBorrower(id);
    }

    @PostMapping
    public Borrower addBorrower(@RequestBody Borrower borrower) {
        borrowerService.addBorrower(borrower);

        return borrower;
    }

    @DeleteMapping("{id}")
    public Collection<Borrower> removeBorrower(@PathVariable int id) {
        borrowerService.removeBorrower(id);

        return borrowerService.getAll();
    }

}

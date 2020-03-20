package com.fatush.library.controller;

import com.fatush.library.model.Borrower;
import com.fatush.library.service.BookService;
import com.fatush.library.service.BorrowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RequestMapping("/borrower")
@RestController
public class BorrowController {

    private BookService bookService;
    private BorrowService borrowService;

    @Autowired
    public BorrowController(
            BookService bookService, BorrowService borrowService
    ) {
        this.bookService = bookService;
        this.borrowService = borrowService;
    }

    @GetMapping
    public Collection<Borrower> getAllBorrowers() {

        return borrowService.getAll();
    }

    @GetMapping("{id}")
    public Borrower getBorrowedBooks(@PathVariable String id) {

        return borrowService.getBorrowerById(id);
    }

    @PostMapping
    public Borrower addBorrower(@RequestBody Borrower borrower) {
        borrowService.addBorrower(borrower);

        return borrower;
    }

    @DeleteMapping("{id}")
    public Collection<Borrower> removeBorrower(@PathVariable int id) {
        borrowService.removeBorrower(id);

        return borrowService.getAll();
    }

    @GetMapping("{borrowerId}/{bookId}")
    public String addBorrowedBook(
            @PathVariable String borrowerId,
            @PathVariable String bookId,
            @AuthenticationPrincipal UserDetails user
    ) {
        return borrowService.addBorrowedBook(borrowService.getBorrowerById(borrowerId), bookService.getBookById(bookId), user);
    }

    @GetMapping("{borrowerId}/{bookId}/del")
    public Borrower deleteBorrowedBook(
            @PathVariable String borrowerId,
            @PathVariable String bookId,
            @AuthenticationPrincipal UserDetails user
    ) {
        borrowService.removeBorrowedBook(borrowService.getBorrowerById(borrowerId), bookService.getBookById(bookId), user);

        return borrowService.getBorrowerById(borrowerId);
    }
}

package com.fatush.library.controller;

import com.fatush.library.model.Book;
import com.fatush.library.model.Borrower;
import com.fatush.library.service.BookService;
import com.fatush.library.service.BorrowService;
import com.fatush.library.service.BorrowerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/borrower")
@RestController
public class BorrowController {

    private BorrowerService borrowerService;
    private BookService bookService;
    private BorrowService borrowService;

    @Autowired
    public BorrowController(BorrowerService borrowerService, BookService bookService, BorrowService borrowService) {
        this.borrowerService = borrowerService;
        this.bookService = bookService;
        this.borrowService = borrowService;
    }

    @PostMapping("{id}")
    public Borrower addBorrowedBook(
            @PathVariable String id,
            Book book
    ) {
        borrowService.addBorrowerBook(borrowerService.getBorrowerById(id), book);

        return borrowerService.getBorrowerById(id);
    }

    @DeleteMapping("{borrowerId}/{bookId}")
    public Borrower deleteBorrowedBook(
            @PathVariable String borrowerId,
            @PathVariable String bookId
    ) {
        borrowService.removeBorrowerBook(borrowerService.getBorrowerById(borrowerId), bookService.getBookById(bookId));

        return borrowerService.getBorrowerById(borrowerId);
    }
}

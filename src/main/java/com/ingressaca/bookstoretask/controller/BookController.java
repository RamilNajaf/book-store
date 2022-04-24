package com.ingressaca.bookstoretask.controller;

import com.ingressaca.bookstoretask.dto.BookDTO;
import com.ingressaca.bookstoretask.entity.Book;
import com.ingressaca.bookstoretask.service.BookService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/books")
public class BookController extends GenericController<BookDTO, Book> {
    public BookController(BookService bookService) {
        super(bookService);
    }
}
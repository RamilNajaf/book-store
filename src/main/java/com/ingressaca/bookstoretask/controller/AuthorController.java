package com.ingressaca.bookstoretask.controller;

import com.ingressaca.bookstoretask.dto.AuthorDTO;
import com.ingressaca.bookstoretask.entity.Author;
import com.ingressaca.bookstoretask.service.AuthorService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/authors")
public class AuthorController extends GenericController<AuthorDTO, Author> {
    public AuthorController(AuthorService authorService) {
        super(authorService);
    }
}
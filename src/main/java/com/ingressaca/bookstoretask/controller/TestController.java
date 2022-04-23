package com.ingressaca.bookstoretask.controller;


import com.ingressaca.bookstoretask.dto.BookDTO;
import com.ingressaca.bookstoretask.dto.mapper.BookMapper;
import com.ingressaca.bookstoretask.entity.AppUser;
import com.ingressaca.bookstoretask.entity.Author;
import com.ingressaca.bookstoretask.entity.Book;
import com.ingressaca.bookstoretask.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/api")
public class TestController {

    @Autowired
    BookMapper bookMapper;


    @GetMapping("/books")
    public BookDTO getUser(){

        Book book = new Book();

        Author author = new Author();
        author.setFullName("author 1 2");
        AppUser appUser = new AppUser();

        appUser.setName("ramil");
        Role role = new Role();
        role.setName("publisher ");

        appUser.setRoles(Set.of(role));

        book.setAuthor(author);
        book.setPublisher(appUser);
        book.setPrice("111");
        return bookMapper.toDto(book);

    }
}

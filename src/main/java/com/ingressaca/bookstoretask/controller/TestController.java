package com.ingressaca.bookstoretask.controller;


import com.ingressaca.bookstoretask.dto.AppUserDTO;
import com.ingressaca.bookstoretask.dto.AuthorDTO;
import com.ingressaca.bookstoretask.dto.BookDTO;
import com.ingressaca.bookstoretask.service.AppUserService;
import com.ingressaca.bookstoretask.service.AuthorService;
import com.ingressaca.bookstoretask.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class TestController {


    private final BookService bookService;
    private final AuthorService authorService;
    private final AppUserService appUserService;


    @PostMapping("/author")
    public AuthorDTO addBook(@RequestBody AuthorDTO authorDTO) {
        return authorService.save(authorDTO);
    }

    @PostMapping("/appUser")
    public AppUserDTO addBook(@RequestBody AppUserDTO appUserDTO) {
        return appUserService.save(appUserDTO);
    }

    @PostMapping("/book")
    public BookDTO addBook(@RequestBody BookDTO bookDTO) {
        return bookService.save(bookDTO);
    }


    @PutMapping("/book/{id}")
    public BookDTO updateBook(@PathVariable Long id,@RequestBody BookDTO bookDTO) {
        return bookService.update(id , bookDTO).orElseThrow();
    }
}

package com.ingressaca.bookstoretask;

import com.ingressaca.bookstoretask.entity.AppUser;
import com.ingressaca.bookstoretask.entity.Author;
import com.ingressaca.bookstoretask.repository.AppUserRepository;
import com.ingressaca.bookstoretask.repository.AuthorRepository;
import com.ingressaca.bookstoretask.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BookstoretaskApplication implements CommandLineRunner {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private AppUserRepository appUserRepository;

    public static void main(String[] args) {
        SpringApplication.run(BookstoretaskApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        Author author = new Author();
        author.setFullName("ramil");
        authorRepository.save(author);

        AppUser appUser = new AppUser();
        appUser.setName("appuser");
        appUserRepository.save(appUser);

    }
}

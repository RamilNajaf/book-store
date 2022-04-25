package com.ingressaca.bookstoretask;

import com.ingressaca.bookstoretask.entity.AppUser;
import com.ingressaca.bookstoretask.repository.AppUserRepository;
import com.ingressaca.bookstoretask.repository.AuthorRepository;
import com.ingressaca.bookstoretask.repository.BookRepository;
import com.ingressaca.bookstoretask.security.model.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@SpringBootApplication
public class BookStoreTaskApplication implements CommandLineRunner {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public static void main(String[] args) {
        SpringApplication.run(BookStoreTaskApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        AppUser appUser = new AppUser(
                "ramil@gmail.com",
                "ramil", passwordEncoder.encode("ramil"),
                Roles.PUBLISHER);

        AppUser appUser2 = new AppUser(
                "hesen@gmail.com",
                "hesen", passwordEncoder.encode("hesen"),
                Roles.ADMIN);


        appUserRepository.saveAll(List.of(appUser, appUser2));

    }


}

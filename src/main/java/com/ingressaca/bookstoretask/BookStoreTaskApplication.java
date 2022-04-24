package com.ingressaca.bookstoretask;

import com.ingressaca.bookstoretask.entity.AppUser;
import com.ingressaca.bookstoretask.entity.Author;
import com.ingressaca.bookstoretask.entity.Role;
import com.ingressaca.bookstoretask.repository.AppUserRepository;
import com.ingressaca.bookstoretask.repository.AuthorRepository;
import com.ingressaca.bookstoretask.repository.BookRepository;
import com.ingressaca.bookstoretask.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

@SpringBootApplication
public class BookStoreTaskApplication implements CommandLineRunner {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private AppUserRepository appUserRepository;

    public static void main(String[] args) {
        SpringApplication.run(BookStoreTaskApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        Role role1 = new Role("PUBLISHER");
        Role role2 = new Role("USER");
        roleRepository.saveAll(List.of(role1,role2));


        Author author = new Author();
        author.setFullName("ramil");
        authorRepository.save(author);

        AppUser appUser = new AppUser();
        appUser.setUsername("appuser");
        appUserRepository.save(appUser);

    }


    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}

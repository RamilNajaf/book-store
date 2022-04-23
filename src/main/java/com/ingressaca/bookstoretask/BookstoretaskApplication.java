package com.ingressaca.bookstoretask;

import com.ingressaca.bookstoretask.entity.Book;
import com.ingressaca.bookstoretask.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BookstoretaskApplication implements CommandLineRunner {

    @Autowired
    private BookRepository bookRepository;

    public static void main(String[] args) {
        SpringApplication.run(BookstoretaskApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        Book book = new Book();
        book.setId(1l);

        bookRepository.save(book);

    }
}

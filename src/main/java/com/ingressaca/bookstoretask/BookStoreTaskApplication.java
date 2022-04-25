package com.ingressaca.bookstoretask;

import com.ingressaca.bookstoretask.entity.AppUser;
import com.ingressaca.bookstoretask.repository.AppUserRepository;
import com.ingressaca.bookstoretask.security.model.Roles;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@SpringBootApplication
@AllArgsConstructor
public class BookStoreTaskApplication implements CommandLineRunner {

    private AppUserRepository appUserRepository;

    private PasswordEncoder passwordEncoder;

    public static void main(String[] args) {
        SpringApplication.run(BookStoreTaskApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        if(appUserRepository.findAll().size()==0){

        AppUser appUser = new AppUser(
                "ramil@gmail.com",
                "ramil", passwordEncoder.encode("ramil"),
                Roles.PUBLISHER);

        AppUser appUser2 = new AppUser(
                "hesen@gmail.com",
                "hesen", passwordEncoder.encode("hesen"),
                Roles.ADMIN);


        appUserRepository.saveAll(List.of(appUser, appUser2));

    }}


}

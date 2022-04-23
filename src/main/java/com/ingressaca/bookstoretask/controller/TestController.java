package com.ingressaca.bookstoretask.controller;


import com.ingressaca.bookstoretask.entity.AppUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TestController {

    @GetMapping("/user")
    public AppUser getUser(){

        AppUser appUser = new AppUser();
        appUser.setId(1L);
        appUser.setName("Ramil");
        return appUser;
    }
}

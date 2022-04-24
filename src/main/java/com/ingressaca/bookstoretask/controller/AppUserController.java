package com.ingressaca.bookstoretask.controller;

import com.ingressaca.bookstoretask.dto.AppUserDTO;
import com.ingressaca.bookstoretask.entity.AppUser;
import com.ingressaca.bookstoretask.service.AppUserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AppUserController extends GenericController<AppUserDTO, AppUser> {
    public AppUserController(AppUserService appUserService) {
        super(appUserService);
    }
}
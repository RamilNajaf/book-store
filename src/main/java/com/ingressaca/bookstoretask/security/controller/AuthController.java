package com.ingressaca.bookstoretask.security.controller;


import com.ingressaca.bookstoretask.dto.AppUserDTO;
import com.ingressaca.bookstoretask.security.model.dto.request.LoginRequest;
import com.ingressaca.bookstoretask.security.model.dto.request.SignUpRequest;
import com.ingressaca.bookstoretask.security.model.dto.response.TokenResponse;
import com.ingressaca.bookstoretask.security.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequestMapping("/api")
@RestController
@AllArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<TokenResponse> signIn(@Valid @RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.createAuthenticationToken(request));
    }


    @PostMapping("/signup")
    public ResponseEntity<AppUserDTO> signUp(@Valid @RequestBody SignUpRequest request) {
        return new ResponseEntity<>(authService.signUp(request), HttpStatus.CREATED);
    }
}

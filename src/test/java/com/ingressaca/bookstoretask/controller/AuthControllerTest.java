package com.ingressaca.bookstoretask.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.ingressaca.bookstoretask.BaseIntegrationTest;
import com.ingressaca.bookstoretask.entity.AppUser;
import com.ingressaca.bookstoretask.repository.AppUserRepository;
import com.ingressaca.bookstoretask.security.model.dto.request.LoginRequest;
import com.ingressaca.bookstoretask.security.model.dto.request.SignUpRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@BaseIntegrationTest
public class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    protected final ObjectMapper objectMapper = new ObjectMapper();




    @Test
    public void testSingUpWhenSuccessful() throws Exception {

        SignUpRequest signupRequest = new SignUpRequest();
        signupRequest.setEmail("test@gmail.com");
        signupRequest.setUsername("test");
        signupRequest.setPassword("test");

        mockMvc.perform(
                        post("/api/signup")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(signupRequest))
                )
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(jsonPath("$.username", equalTo("test")));
    }


    @Test
    public void testSingUpWhenValidationFails() throws Exception {

        SignUpRequest signupRequest = new SignUpRequest();
        signupRequest.setEmail("");
        signupRequest.setUsername("");
        signupRequest.setPassword("");

        mockMvc.perform(
                        post("/api/signup")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(signupRequest))
                )
                .andExpect(status().isUnprocessableEntity())
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(jsonPath("$.title",notNullValue()))
                .andExpect(jsonPath("$.validation_err.length()",equalTo(3)));
    }


    @Test
    public void testSingUpWhenUserAlreadyExist() throws Exception {

        String testUsername= "test12";
        String testEmail = "test12@gmail.com";

        AppUser appUser = new AppUser();

        appUser.setUsername(testUsername);
        appUser.setEmail(testEmail);

        appUserRepository.saveAndFlush(appUser);


        SignUpRequest signupRequest = new SignUpRequest();
        signupRequest.setEmail("test12@gmail.com");
        signupRequest.setUsername("test12");
        signupRequest.setPassword("test");

        mockMvc.perform(
                        post("/api/signup")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(signupRequest))
                )
                .andExpect(status().isUnprocessableEntity())
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(jsonPath("$.title",notNullValue()))
                .andExpect(jsonPath("$.validation_err.length()",equalTo(2)))
                .andExpect(jsonPath("$.validation_err.username", equalTo("Bu dəyər artıq mövcuddur")))
                .andExpect(jsonPath("$.validation_err.email", equalTo("Bu dəyər artıq mövcuddur")));
    }


    @Test
    public void testSignIn() throws Exception {
        AppUser appUser = new AppUser();
        appUser.setUsername("test123");
        appUser.setPassword(passwordEncoder.encode("test123"));
        appUser.setRoles("");

        appUserRepository.save(appUser);


        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setPassword("test123");
        loginRequest.setUsername("test123");

        mockMvc.perform(
                        post("/api/login")
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(loginRequest))
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(jsonPath("$.token",notNullValue()));

    }

    @Test
    public void testSignInWhenWithBadCredentials() throws Exception {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setPassword("test");
        loginRequest.setUsername("test");

        mockMvc.perform(
                        post("/api/login")
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(loginRequest))
                )
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(jsonPath("$.title",notNullValue()));

    }


    @Test
    @WithMockUser(username = "ramil")
    public void test() throws Exception {
        mockMvc.perform(
                        get("/api/me")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(jsonPath("$.username",equalTo("ramil")));

    }

}

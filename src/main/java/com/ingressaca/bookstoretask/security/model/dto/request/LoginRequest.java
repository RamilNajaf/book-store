package com.ingressaca.bookstoretask.security.model.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class LoginRequest {

    @NotBlank(message = "{user.username.msg}")
    private String username;

    @NotBlank(message = "{user.password.msg}")
    private String password;

}


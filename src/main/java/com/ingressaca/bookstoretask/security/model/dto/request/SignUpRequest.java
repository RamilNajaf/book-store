package com.ingressaca.bookstoretask.security.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SignUpRequest {


    @NotBlank(message = "{user.username.msg}")
    private String username;

    @NotBlank(message = "{user.password.msg}")
    private String password;
}

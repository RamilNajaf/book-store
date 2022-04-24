package com.ingressaca.bookstoretask.security.model.dto.request;

import com.ingressaca.bookstoretask.entity.AppUser;
import com.ingressaca.bookstoretask.validation.UniqueField;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SignUpRequest {

    @Email(message = "{user.email.msg}")
    @NotBlank(message = "{user.email.msg}")
    @UniqueField(entityClass = AppUser.class, fieldName = "email")
    private String email;

    @NotBlank(message = "{user.username.msg}")
    @UniqueField(entityClass = AppUser.class, fieldName = "username")
    private String username;

    @NotBlank(message = "{user.password.msg}")
    private String password;
}

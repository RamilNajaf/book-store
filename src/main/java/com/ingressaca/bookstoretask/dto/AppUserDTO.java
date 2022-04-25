package com.ingressaca.bookstoretask.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AppUserDTO extends BaseDTO {

    private String email;

    private String username;

    private String roles;
}

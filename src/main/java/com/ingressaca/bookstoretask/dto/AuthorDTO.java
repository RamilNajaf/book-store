package com.ingressaca.bookstoretask.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthorDTO extends BaseDTO {

    private String fullName;

    private String birthDay;
}

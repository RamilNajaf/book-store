package com.ingressaca.bookstoretask.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookDTO {

    private Long id;

    private String name;

    private String bookIntro;

    private String price;

    private AuthorDTO author;

    private AppUserDTO publisher;

}

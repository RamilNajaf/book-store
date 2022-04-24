package com.ingressaca.bookstoretask.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookDTO   extends BaseDTO{

    private String name;

    private String bookIntro;

    private String price;

    private AuthorDTO author;

    private AppUserDTO publisher;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long authorId;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long publisherId;

}

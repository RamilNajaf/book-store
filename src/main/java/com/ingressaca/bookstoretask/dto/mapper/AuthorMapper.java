package com.ingressaca.bookstoretask.dto.mapper;

import com.ingressaca.bookstoretask.dto.AuthorDTO;
import com.ingressaca.bookstoretask.entity.Author;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthorMapper  {

    AuthorDTO toDto(Author entity);
}

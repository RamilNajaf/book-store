package com.ingressaca.bookstoretask.mapper;

import com.ingressaca.bookstoretask.dto.AuthorDTO;
import com.ingressaca.bookstoretask.entity.Author;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import static org.mapstruct.NullValueCheckStrategy.ALWAYS;
import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

@Mapper(componentModel = "spring")
public interface AuthorMapper  {

    AuthorDTO toDto(Author entity);

    Author toEntity(AuthorDTO dto);

    @BeanMapping(nullValueCheckStrategy = ALWAYS,nullValuePropertyMappingStrategy = IGNORE)
    public abstract void updateModel(AuthorDTO dto, @MappingTarget Author author);

}

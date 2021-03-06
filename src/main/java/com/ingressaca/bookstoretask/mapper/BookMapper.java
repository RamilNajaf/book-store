package com.ingressaca.bookstoretask.mapper;

import com.ingressaca.bookstoretask.dto.BookDTO;
import com.ingressaca.bookstoretask.entity.Author;
import com.ingressaca.bookstoretask.entity.Book;
import com.ingressaca.bookstoretask.repository.AppUserRepository;
import com.ingressaca.bookstoretask.repository.AuthorRepository;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;

import static org.mapstruct.NullValueCheckStrategy.ALWAYS;
import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

@Mapper(componentModel = "spring", uses = {AuthorMapper.class, AppUserMapper.class})
public abstract class BookMapper {

    @Autowired
    protected AuthorRepository authorRepository;

    @Autowired
    protected AppUserRepository appUserRepository;

    public abstract BookDTO toDto(Book entity);

    @Mapping(target = "author", expression = "java(findAuthor(dto.getAuthorId()))")
    public abstract Book toEntity(BookDTO dto);

    @Mapping(target = "author", expression = "java(updateAuthor(dto.getAuthorId(),book))")
    @BeanMapping(nullValueCheckStrategy = ALWAYS, nullValuePropertyMappingStrategy = IGNORE)
    public abstract void updateModel(BookDTO dto, @MappingTarget Book book);


    protected Author findAuthor(Long id) {
        if (id == null)
            return null;
        return authorRepository.findById(id).orElseThrow();
    }

    protected Author updateAuthor(Long id, Book book) {
        if (id != null) {
            return authorRepository.findById(id).orElseThrow();
        }

        return book.getAuthor();

    }
}

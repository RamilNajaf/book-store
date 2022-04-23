package com.ingressaca.bookstoretask.dto.mapper;

import com.ingressaca.bookstoretask.dto.BookDTO;
import com.ingressaca.bookstoretask.entity.Book;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring",uses ={AuthorMapper.class,AppUserMapper.class})
public interface BookMapper {

    BookDTO toDto(Book entity);
}

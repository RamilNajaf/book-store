package com.ingressaca.bookstoretask.service;


import com.ingressaca.bookstoretask.dto.BookDTO;
import com.ingressaca.bookstoretask.dto.mapper.BookMapper;
import com.ingressaca.bookstoretask.entity.Book;
import com.ingressaca.bookstoretask.repository.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BookService implements GenericService<BookDTO, Book> {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;


    public BookDTO findById(Long bookId) {
        Book book = bookRepository.findById(bookId).orElseThrow();
        return bookMapper.toDto(book);
    }

    public List<BookDTO> findAll() {
        return bookRepository.findAll().stream().map(book -> bookMapper.toDto(book)).collect(Collectors.toList());
    }

    public BookDTO save(BookDTO bookDTO) {
        Book book = bookMapper.toEntity(bookDTO);
        return bookMapper.toDto(bookRepository.save(book));
    }

    @Override
    public Optional<BookDTO> update(Long id, BookDTO dto) {
        return Optional
                .of(bookRepository.findById(id))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(
                        book ->
                        {
                            bookMapper.updateModel(dto, book);
                            return bookRepository.save(book);
                        }
                )
                .map(book -> bookMapper.toDto(book));
    }


    public void delete(Long bookId) {
        bookRepository.deleteById(bookId);
    }


}

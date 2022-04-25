package com.ingressaca.bookstoretask.service;


import com.ingressaca.bookstoretask.dto.BookDTO;
import com.ingressaca.bookstoretask.entity.AppUser;
import com.ingressaca.bookstoretask.entity.Book;
import com.ingressaca.bookstoretask.exception.ForbiddenException;
import com.ingressaca.bookstoretask.mapper.BookMapper;
import com.ingressaca.bookstoretask.repository.AppUserRepository;
import com.ingressaca.bookstoretask.repository.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BookService implements GenericService<BookDTO, Book> {

    private final BookRepository bookRepository;
    private final AppUserRepository appUserRepository;
    private final BookMapper bookMapper;


    public BookDTO findById(Long bookId) {
        Book book = bookRepository.findById(bookId).orElseThrow();
        return bookMapper.toDto(book);
    }

    public List<BookDTO> findAll() {
        return bookRepository.findAll().stream().map(bookMapper::toDto).collect(Collectors.toList());
    }

    public BookDTO save(BookDTO bookDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        AppUser appUser = appUserRepository.findByUsername(authentication.getName()).orElseThrow();
        Book book = bookMapper.toEntity(bookDTO);
        book.setPublisher(appUser);
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
                .map(bookMapper::toDto);
    }


    public void delete(Long bookId) {
        bookRepository.deleteById(bookId);
    }


    public void deleteByPublisher(Long bookId) {

        Book book = bookRepository.findById(bookId).orElseThrow();

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        AppUser appUser = appUserRepository.findByUsername(authentication.getName()).orElseThrow();


        if (book.getPublisher().getId().equals(appUser.getId())) {
            bookRepository.deleteById(bookId);
        } else {
            throw new ForbiddenException();
        }
    }


    public BookDTO updateByPublisher(Long bookId, BookDTO dto) {

        Book book = bookRepository.findById(bookId).orElseThrow();

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        AppUser appUser = appUserRepository.findByUsername(authentication.getName()).orElseThrow();


        if (book.getPublisher().getId().equals(appUser.getId())) {
            bookMapper.updateModel(dto, book);
            return bookMapper.toDto(bookRepository.save(book));
        } else {
            throw new ForbiddenException();
        }
    }

    public List<BookDTO> findSpecificBookWithFilter(String name, String price, Long auhtorId,Long publisherId) {

        List<BookDTO> bookDTOs = bookRepository.findSpecificBook( name, price, auhtorId, publisherId)
                .stream().map(bookMapper::toDto).collect(Collectors.toList());

        return  bookDTOs;
    }


}

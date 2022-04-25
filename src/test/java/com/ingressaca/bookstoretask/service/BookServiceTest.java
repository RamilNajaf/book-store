package com.ingressaca.bookstoretask.service;

import com.ingressaca.bookstoretask.dto.BookDTO;
import com.ingressaca.bookstoretask.entity.AppUser;
import com.ingressaca.bookstoretask.entity.Author;
import com.ingressaca.bookstoretask.entity.Book;
import com.ingressaca.bookstoretask.exception.ForbiddenException;
import com.ingressaca.bookstoretask.mapper.BookMapper;
import com.ingressaca.bookstoretask.repository.AppUserRepository;
import com.ingressaca.bookstoretask.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class BookServiceTest {

    private BookService bookService;
    private BookRepository bookRepository;
    private AppUserRepository appUserRepository;
    private BookMapper bookMapper;


    @BeforeEach
    public void setUp() {

        bookRepository = mock(BookRepository.class);
        appUserRepository = mock(AppUserRepository.class);
        bookMapper = mock(BookMapper.class);
        bookService = new BookService(bookRepository, appUserRepository, bookMapper);

    }

    @Test
    public void testFindByIdWhenBookExist() {
        Book book = new Book("name", "intro", "price", new Author(), new AppUser());

        BookDTO expected = bookMapper.toDto(book);
        when(bookRepository.findById(anyLong())).thenReturn(Optional.of(book));
        BookDTO result = bookService.findById(anyLong());

        assertEquals(result, expected);
    }


    @Test
    public void testFindByIdWhenBookNotExist() {
        when(bookRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(NoSuchElementException.class, () -> bookService.findById(anyLong()));
    }


    @Test
    public void testSave() {
        AppUser publisher = new AppUser();
        Author author = new Author();


        Book book = new Book("name", "intro", "price", author, publisher);
        BookDTO bookDTO = new BookDTO();

        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken("test", new ArrayList<>()));
        when(appUserRepository.findByUsername("test")).thenReturn(Optional.of(publisher));
        when(bookMapper.toEntity(bookDTO)).thenReturn(book);

        BookDTO result = bookService.save(bookDTO);
        assertEquals(result, bookMapper.toDto(book));

        verify(bookRepository, times(1)).save(book);
        verify(appUserRepository, times(1)).findByUsername("test");
        verifyNoMoreInteractions(bookRepository);
        verifyNoMoreInteractions(appUserRepository);
    }

    @Test
    public void testDeleteWhenBookExist() {

        AppUser publisher = new AppUser();
        Author author = new Author();
        Book book = new Book("name", "intro", "price", author, publisher);

        when(bookRepository.findById(anyLong())).thenReturn(Optional.of(book));
        doNothing().when(bookRepository).delete(book);

        bookService.delete(anyLong());
        verify(bookRepository).findById(anyLong());
        verify(bookRepository, times(1)).delete(book);
        verifyNoMoreInteractions(bookRepository);
    }

    @Test
    public void testUpdate() {
        AppUser publisher = new AppUser();
        publisher.setId(1l);
        Author author = new Author();

        Book book = new Book("name", "intro", "price", author, publisher);
        BookDTO bookDTO = new BookDTO();

        when(bookRepository.findById(anyLong())).thenReturn(Optional.of(book));
        doNothing().when(bookMapper).updateModel(bookDTO, book);
        when(bookRepository.save(book)).thenReturn(book);
        when(bookMapper.toDto(book)).thenReturn(bookDTO);

        BookDTO updateBook = bookService.update(anyLong(), bookDTO).get();

        assertEquals(updateBook, bookDTO);


    }


    @Test
    public void testFindAll() {
        List<Book> books = new ArrayList<>();

        IntStream stream = IntStream.range(1, 3);
        stream.forEach(range -> {
            books.add(new Book());
        });

        List<BookDTO> expected = books.stream().map(bookMapper::toDto).collect(Collectors.toList());
        when(bookRepository.findAll()).thenReturn(books);

        List<BookDTO> result = bookService.findAll();
        assertEquals(result, expected);
        verify(bookRepository, times(1)).findAll();
        verifyNoMoreInteractions(bookRepository);
    }

    @Test
    public void testDeleteByPublisherId() {

        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken("test", new ArrayList<>()));
        AppUser publisher = new AppUser();
        publisher.setId(1l);
        Author author = new Author();


        Book book = new Book("name", "intro", "price", author, publisher);
        when(bookRepository.findById(anyLong())).thenReturn(Optional.of(book));
        when(appUserRepository.findByUsername("test")).thenReturn(Optional.of(publisher));
        doNothing().when(bookRepository).deleteById(anyLong());

        bookService.deleteByPublisher(anyLong());

        verify(bookRepository, times(1)).deleteById(anyLong());
    }


    @Test
    public void testDeleteByPublisherIdWhenForbiddenException() {

        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken("test", new ArrayList<>()));
        AppUser publisher = new AppUser();
        publisher.setId(1l);
        Author author = new Author();

        Book book = new Book("name", "intro", "price", author, publisher);
        when(bookRepository.findById(anyLong())).thenReturn(Optional.of(book));
        when(appUserRepository.findByUsername("test")).thenReturn(Optional.of(new AppUser()));

        assertThrows(ForbiddenException.class, () -> bookService.deleteByPublisher(anyLong()));
        verify(bookRepository, times(0)).deleteById(anyLong());

    }


    @Test
    public void testUpdateByPublisherId() {

        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken("test", new ArrayList<>()));
        AppUser publisher = new AppUser();
        publisher.setId(1l);
        Author author = new Author();

        Book book = new Book("name", "intro", "price", author, publisher);

        BookDTO bookDTO = new BookDTO();

        when(bookRepository.findById(anyLong())).thenReturn(Optional.of(book));
        when(appUserRepository.findByUsername("test")).thenReturn(Optional.of(publisher));
        doNothing().when(bookMapper).updateModel(bookDTO, book);
        when(bookRepository.save(book)).thenReturn(book);
        when(bookMapper.toDto(book)).thenReturn(bookDTO);


        BookDTO result = bookService.updateByPublisher(anyLong(), bookDTO);

        assertEquals(result, bookDTO);
        verify(bookMapper, times(1)).updateModel(bookDTO, book);
        verify(bookMapper, times(1)).toDto(book);
        verify(bookRepository, times(1)).save(book);

    }


    @Test
    public void testUpdateByPublisherIdWhenForbiddenException() {

        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken("test", new ArrayList<>()));
        AppUser publisher = new AppUser();
        publisher.setId(1l);
        Author author = new Author();

        Book book = new Book("name", "intro", "price", author, publisher);

        BookDTO bookDTO = new BookDTO();

        when(bookRepository.findById(anyLong())).thenReturn(Optional.of(book));
        when(appUserRepository.findByUsername("test")).thenReturn(Optional.of(new AppUser()));


        assertThrows(ForbiddenException.class, () -> bookService.updateByPublisher(anyLong(), bookDTO));
        verify(bookMapper, times(0)).updateModel(any(), any());
        verify(bookMapper, times(0)).toDto(any());


    }

}

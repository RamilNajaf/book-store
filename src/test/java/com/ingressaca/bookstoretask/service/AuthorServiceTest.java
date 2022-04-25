package com.ingressaca.bookstoretask.service;

import com.ingressaca.bookstoretask.dto.AuthorDTO;
import com.ingressaca.bookstoretask.entity.Author;
import com.ingressaca.bookstoretask.mapper.AuthorMapper;
import com.ingressaca.bookstoretask.repository.AuthorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class AuthorServiceTest {

    private AuthorRepository authorRepository;
    private AuthorMapper authorMapper;
    private AuthorService authorService;

    @BeforeEach
    public void setUp() {
        authorRepository = mock(AuthorRepository.class);
        authorMapper = mock(AuthorMapper.class);
        authorService = new AuthorService(authorRepository, authorMapper);
    }


    @Test
    public void testFindByIdWhenAuthorExist() {
        Author author = new Author("name", "birtyhday");

        AuthorDTO expected = authorMapper.toDto(author);
        when(authorRepository.findById(anyLong())).thenReturn(Optional.of(author));
        AuthorDTO result = authorService.findById(anyLong());

        assertEquals(result, expected);
    }


    @Test
    public void testFindByIdWhenAuthorNotExist() {
        when(authorRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(NoSuchElementException.class, () -> authorService.findById(anyLong()));
    }


    @Test
    public void testSave() {
        Author author = new Author("name", "birtday");
        AuthorDTO authorDTO = new AuthorDTO();

        when(authorMapper.toEntity(authorDTO)).thenReturn(author);

        AuthorDTO result = authorService.save(authorDTO);
        assertEquals(result, authorMapper.toDto(author));

        verify(authorRepository, times(1)).save(author);
        verifyNoMoreInteractions(authorRepository);
    }

    @Test
    public void testDeleteWhenAuthorExist() {
        Author author = new Author("name", "birhDay");

        when(authorRepository.findById(anyLong())).thenReturn(Optional.of(author));
        doNothing().when(authorRepository).delete(author);

        authorService.delete(anyLong());
        verify(authorRepository).findById(anyLong());
        verify(authorRepository, times(1)).delete(author);
        verifyNoMoreInteractions(authorRepository);
    }

    @Test
    public void testUpdate() {
        Author author = new Author("name", "birthDay");
        AuthorDTO authorDTO = new AuthorDTO();

        when(authorRepository.findById(anyLong())).thenReturn(Optional.of(author));
        doNothing().when(authorMapper).updateModel(authorDTO, author);
        when(authorRepository.save(author)).thenReturn(author);
        when(authorMapper.toDto(author)).thenReturn(authorDTO);

        AuthorDTO updateAuthor = authorService.update(anyLong(), authorDTO).get();

        assertEquals(updateAuthor, authorDTO);

    }

    @Test
    public void testFindAll() {
        List<Author> authors = new ArrayList<>();

        IntStream stream = IntStream.range(1, 3);
        stream.forEach(range -> {
            authors.add(new Author());
        });

        List<AuthorDTO> expected = authors.stream().map(authorMapper::toDto).collect(Collectors.toList());
        when(authorRepository.findAll()).thenReturn(authors);

        List<AuthorDTO> result = authorService.findAll();
        assertEquals(result, expected);
        verify(authorRepository, times(1)).findAll();
        verifyNoMoreInteractions(authorRepository);
    }
}

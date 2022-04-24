
package com.ingressaca.bookstoretask.service;

import com.ingressaca.bookstoretask.dto.AuthorDTO;
import com.ingressaca.bookstoretask.entity.Author;
import com.ingressaca.bookstoretask.mapper.AuthorMapper;
import com.ingressaca.bookstoretask.repository.AuthorRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AuthorService implements GenericService<AuthorDTO, Author> {
    private  final AuthorRepository authorRepository;
    private  final AuthorMapper authorMapper;


    public AuthorDTO findById(Long authorId){
        Author author = authorRepository.findById(authorId).orElseThrow();
        return authorMapper.toDto(author);
    }

    public List<AuthorDTO> findAll(){
        return authorRepository.findAll().stream().map(author -> authorMapper.toDto(author)).collect(Collectors.toList());
    }
    public AuthorDTO save(AuthorDTO authorDTO){
        Author author  = authorMapper.toEntity(authorDTO);
        return authorMapper.toDto(authorRepository.save(author));
    }

    @Override
    public Optional<AuthorDTO> update(Long id, AuthorDTO dto) {
        return Optional
                .of(authorRepository.findById(id))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(
                       author ->
                        {
                            authorMapper.updateModel(dto,author);
                            return authorRepository.save(author);
                        }
                )
                .map(author -> authorMapper.toDto(author));
    }


    public void delete(Long authorId){
        authorRepository.deleteById(authorId);
    }
}

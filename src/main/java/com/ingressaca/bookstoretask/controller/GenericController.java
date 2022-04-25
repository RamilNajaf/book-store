package com.ingressaca.bookstoretask.controller;

import com.ingressaca.bookstoretask.dto.BaseDTO;
import com.ingressaca.bookstoretask.entity.BaseEntity;
import com.ingressaca.bookstoretask.service.GenericService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

public abstract class GenericController<T extends BaseDTO, E extends BaseEntity> {
    private final GenericService<T, E> genericService;

    public GenericController(GenericService<T, E> genericService) {
        this.genericService = genericService;
    }

    @GetMapping
    public ResponseEntity<List<T>> findAll() {
        final List<T> list = genericService.findAll();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<T> findById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(genericService.findById(id));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<T> update(@PathVariable Long id, @RequestBody T baseDTO) {
        Optional<T> optional = genericService.update(id, baseDTO);
        return optional.map((response) ->
                        ResponseEntity.ok().body(response)).orElseThrow();
    }

    @PostMapping
    public ResponseEntity<T> save(@RequestBody T baseDTO) throws URISyntaxException {
        T newData = genericService.save(baseDTO);
        return ResponseEntity
                .created(new URI(requestMappingValueOfChildClass()))
                .body(newData);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        genericService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    private String requestMappingValueOfChildClass() {
        return getClass().getAnnotation(RequestMapping.class).value()[0];
    }

}

package com.ingressaca.bookstoretask.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class Book {

    @Id
    private Long id;

}

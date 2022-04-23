package com.ingressaca.bookstoretask.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
public class Book {

    @Id
    private Long id;

    private String name;

    private String bookIntro;

    private String price;

    @ManyToOne
    private Author author;

    @ManyToOne
    private AppUser publisher;

}

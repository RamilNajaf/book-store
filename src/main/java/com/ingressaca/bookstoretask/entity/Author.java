package com.ingressaca.bookstoretask.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
public class Author extends BaseEntity {

    private String fullName;

    private String birthDay;
}

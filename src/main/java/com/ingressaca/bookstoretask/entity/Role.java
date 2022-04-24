package com.ingressaca.bookstoretask.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
public class Role extends BaseEntity{

    private String  name;

}

package com.ingressaca.bookstoretask.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class AppUserDTO  extends BaseDTO {

    private String name;

    private Set<RoleDTO> roles;
}

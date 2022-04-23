package com.ingressaca.bookstoretask.dto.mapper;

import com.ingressaca.bookstoretask.dto.RoleDTO;
import com.ingressaca.bookstoretask.entity.Role;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    RoleDTO toDto(Role entity);
}

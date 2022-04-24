package com.ingressaca.bookstoretask.dto.mapper;

import com.ingressaca.bookstoretask.dto.RoleDTO;
import com.ingressaca.bookstoretask.entity.Role;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import static org.mapstruct.NullValueCheckStrategy.ALWAYS;
import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    RoleDTO toDto(Role entity);

    Role toEntity(RoleDTO dto);

    @BeanMapping(nullValueCheckStrategy = ALWAYS,nullValuePropertyMappingStrategy = IGNORE)
    public abstract void updateModel(RoleDTO dto, @MappingTarget Role role);
}

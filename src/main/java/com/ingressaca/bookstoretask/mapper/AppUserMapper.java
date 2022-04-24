package com.ingressaca.bookstoretask.mapper;

import com.ingressaca.bookstoretask.dto.AppUserDTO;
import com.ingressaca.bookstoretask.entity.AppUser;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import static org.mapstruct.NullValueCheckStrategy.ALWAYS;
import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

@Mapper(componentModel = "spring",uses = RoleMapper.class)
public interface AppUserMapper {

    AppUserDTO toDto(AppUser entity);

    AppUser toEntity(AppUserDTO dto);

    @BeanMapping(nullValueCheckStrategy = ALWAYS,nullValuePropertyMappingStrategy = IGNORE)
    public abstract void updateModel(AppUserDTO dto, @MappingTarget AppUser appUser);
}

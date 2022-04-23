package com.ingressaca.bookstoretask.dto.mapper;

import com.ingressaca.bookstoretask.dto.AppUserDTO;
import com.ingressaca.bookstoretask.entity.AppUser;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring",uses = RoleMapper.class)
public interface AppUserMapper {
    AppUserDTO toDto(AppUser entity);
}

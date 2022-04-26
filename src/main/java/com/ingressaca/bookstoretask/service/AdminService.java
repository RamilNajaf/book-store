package com.ingressaca.bookstoretask.service;

import com.ingressaca.bookstoretask.dto.AppUserDTO;
import com.ingressaca.bookstoretask.entity.AppUser;
import com.ingressaca.bookstoretask.mapper.AppUserMapper;
import com.ingressaca.bookstoretask.repository.AppUserRepository;
import com.ingressaca.bookstoretask.security.model.Roles;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AdminService {

    private final AppUserRepository appUserRepository;
    private final AppUserMapper appUserMapper;

    public void addPublisherRoleToUser(Long userId) {
        AppUser appUser = appUserRepository.findById(userId).orElseThrow();

        if (!appUser.getRoleList().contains(Roles.PUBLISHER)) {
            appUser.setRoles(appUser.getRoles()+","+Roles.PUBLISHER);
            appUserRepository.save(appUser);
        }
    }


    public List<AppUserDTO> findAllUsers() {
        List<AppUser> appUsers = appUserRepository.findAll();
        return appUsers.stream().map(appUserMapper::toDto).collect(Collectors.toList());
    }
}

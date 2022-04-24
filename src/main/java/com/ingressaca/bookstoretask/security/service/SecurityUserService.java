package com.ingressaca.bookstoretask.security.service;

import com.ingressaca.bookstoretask.entity.AppUser;
import com.ingressaca.bookstoretask.entity.Role;
import com.ingressaca.bookstoretask.repository.AppUserRepository;
import com.ingressaca.bookstoretask.security.model.SecurityUser;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("securityUserService")
public class SecurityUserService
        implements UserDetailsService {

    private final AppUserRepository appUserRepository;


    public SecurityUserService(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser user = appUserRepository.findByUsername(username).orElseThrow();
        return buildSecurityUser(user);
    }

    private SecurityUser buildSecurityUser(AppUser user) {

        List<Role> userRoleList = new ArrayList(user.getRoles());

        return SecurityUser.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .authorities(userRoleList)
                .accountNonExpired(true)
                .accountNonLocked(true)
                .credentialsNonExpired(true)
                .enabled(true).build();
    }
}

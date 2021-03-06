package com.ingressaca.bookstoretask.security.service;

import com.ingressaca.bookstoretask.entity.AppUser;
import com.ingressaca.bookstoretask.repository.AppUserRepository;
import com.ingressaca.bookstoretask.security.model.SecurityUser;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
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
        AppUser user = appUserRepository.findByUsername(username).orElseThrow(() -> new BadCredentialsException(null));//not throw NoSuchElement
        return buildSecurityUser(user);
    }

    private SecurityUser buildSecurityUser(AppUser user) {

        List<GrantedAuthority> authorityList = new ArrayList<>();

        user.getRoleList().forEach(r -> {
            GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + r);
            authorityList.add(authority);
        });

        return SecurityUser.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .authorities(authorityList)
                .accountNonExpired(true)
                .accountNonLocked(true)
                .credentialsNonExpired(true)
                .enabled(true).build();
    }
}

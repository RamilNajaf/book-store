package com.ingressaca.bookstoretask.security.service;

import com.ingressaca.bookstoretask.dto.AppUserDTO;
import com.ingressaca.bookstoretask.entity.AppUser;
import com.ingressaca.bookstoretask.entity.Role;
import com.ingressaca.bookstoretask.mapper.AppUserMapper;
import com.ingressaca.bookstoretask.repository.AppUserRepository;
import com.ingressaca.bookstoretask.repository.RoleRepository;
import com.ingressaca.bookstoretask.security.model.dto.request.LoginRequest;
import com.ingressaca.bookstoretask.security.model.dto.request.SignUpRequest;
import com.ingressaca.bookstoretask.security.model.dto.response.TokenResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Service
public class AuthService {

    private final TokenService tokenService;

    private final BCryptPasswordEncoder encoder;

    private final AuthenticationManager authenticationManager;

    private final AppUserRepository userRepository;

    private final RoleRepository roleRepository;

    private final AppUserMapper appUserMapper;

    public AuthService(TokenService tokenService,
                       AuthenticationManager authenticationManager,
                       AppUserRepository userRepository,
                       BCryptPasswordEncoder encoder,
                       RoleRepository roleRepository, AppUserMapper appUserMapper) throws AuthenticationException {
        this.tokenService = tokenService;
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.encoder = encoder;
        this.roleRepository = roleRepository;
        this.appUserMapper = appUserMapper;
    }


    public TokenResponse createAuthenticationToken(LoginRequest request) {
        authenticate(request.getUsername(), request.getPassword());
        String token = tokenService.generateToken(request.getUsername());
        return new TokenResponse(token);
    }

    public void authenticate(String username, String password) {
        Objects.requireNonNull(username);
        Objects.requireNonNull(password);
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
    }


    public AppUserDTO signUp(SignUpRequest request) {
        String password = encoder.encode(request.getPassword());
        Set<Role> roles = new HashSet<>(roleRepository.findAll());

        AppUser appUser = new AppUser(request.getEmail(),request.getUsername(), password, roles);
        userRepository.save(appUser);
        return appUserMapper.toDto(appUser);
    }
}

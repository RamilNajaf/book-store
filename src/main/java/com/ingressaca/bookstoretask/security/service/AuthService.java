package com.ingressaca.bookstoretask.security.service;

import com.ingressaca.bookstoretask.dto.AppUserDTO;
import com.ingressaca.bookstoretask.entity.AppUser;
import com.ingressaca.bookstoretask.mapper.AppUserMapper;
import com.ingressaca.bookstoretask.repository.AppUserRepository;
import com.ingressaca.bookstoretask.security.model.dto.request.LoginRequest;
import com.ingressaca.bookstoretask.security.model.dto.request.SignUpRequest;
import com.ingressaca.bookstoretask.security.model.dto.response.TokenResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;
import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Objects;

@Service
public class AuthService {

    private final TokenService tokenService;

    private final BCryptPasswordEncoder encoder;

    private final AuthenticationManager authenticationManager;

    private final AppUserRepository userRepository;

    private final AppUserMapper appUserMapper;

    public AuthService(TokenService tokenService,
                       AuthenticationManager authenticationManager,
                       AppUserRepository userRepository,
                       BCryptPasswordEncoder encoder,
                       AppUserMapper appUserMapper) throws AuthenticationException {
        this.tokenService = tokenService;
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.encoder = encoder;
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

    public AppUserDTO getAuthenticatedUser(HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        AppUser appUser = userRepository.findByUsername(principal.getName()).orElseThrow();
        AppUserDTO userDto = appUserMapper.toDto(appUser);
        return userDto;
    }


    public AppUserDTO signUp(SignUpRequest request) {
        String password = encoder.encode(request.getPassword());
        AppUser appUser = new AppUser(request.getEmail(), request.getUsername(), password, "");
        userRepository.save(appUser);
        return appUserMapper.toDto(appUser);
    }
}

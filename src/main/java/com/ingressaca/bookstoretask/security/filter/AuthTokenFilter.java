package com.ingressaca.bookstoretask.security.filter;

import com.ingressaca.bookstoretask.repository.AppUserRepository;
import com.ingressaca.bookstoretask.security.service.TokenService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AuthTokenFilter extends OncePerRequestFilter {

    private final static String HEADER_KEY = "Authorization";
    private final static String TOKEN_PREFIX = "Bearer ";

    private final AppUserRepository userRepository;
    private final TokenService tokenService;
    private final UserDetailsService userDetailsService;


    public AuthTokenFilter(AppUserRepository userRepository, TokenService tokenService, @Qualifier("securityUserService") UserDetailsService userDetailsService) {
        this.userRepository = userRepository;
        this.tokenService = tokenService;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        final String authHeader = request.getHeader(HEADER_KEY);

        String username = null;
        String token = null;
        Authentication auth
                = SecurityContextHolder.getContext().getAuthentication();


        if (authHeader != null && authHeader.contains(TOKEN_PREFIX)) {
            token = authHeader.substring(7);

            try {
                username = tokenService.getUsernameFromToken(token);
                //try catch block is a must here
            } catch (Exception e) {

            }
        }

        if (username != null && token != null && auth == null) {
            if (tokenService.tokenValidate(token)) {

                UserDetails userDetails
                        = userDetailsService.loadUserByUsername(username);

                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(
                                userDetails,
                                null,
                                userDetails.getAuthorities());

                authentication
                        .setDetails(
                                new WebAuthenticationDetailsSource()
                                        .buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        filterChain.doFilter(request, response);

    }
}

package com.ingressaca.bookstoretask.security.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ingressaca.bookstoretask.security.model.dto.response.EntryPointResponse;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Configuration
public class EntryPointConfig implements AuthenticationEntryPoint {

    private final EntryPointResponse entryPointResponse;


    public EntryPointConfig(EntryPointResponse entryPointResponse) {
        this.entryPointResponse = entryPointResponse;
    }

    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException {
        httpServletResponse.setContentType("application/json; charset=UTF-8");
        httpServletResponse.setCharacterEncoding("UTF-8");


        PrintWriter out = httpServletResponse.getWriter();
        out.write(new ObjectMapper().writeValueAsString(entryPointResponse));

        httpServletResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
    }
}

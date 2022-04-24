package com.ingressaca.bookstoretask.security.model.dto.response;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class TokenResponse {
    String token;

    public TokenResponse(String token) {
        this.token = token;
    }
}

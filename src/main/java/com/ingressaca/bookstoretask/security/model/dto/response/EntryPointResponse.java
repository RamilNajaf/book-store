package com.ingressaca.bookstoretask.security.model.dto.response;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class EntryPointResponse {
    private String title = "Giriş qadağandır";
    private String details = "Bu səhifəyə giriş üçün token əldə etməlisiniz";
}

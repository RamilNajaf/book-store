package com.ingressaca.bookstoretask.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Map;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiErrorDTO {
    int status;
    String path;
    String timestamp;
    String title;
    Map<String, String> validationErr;

    public ApiErrorDTO(int status, String message, String path) {
        this.title = message;
        this.status = status;
        this.path = path;
        this.timestamp = LocalDateTime.now().toString();
    }
}
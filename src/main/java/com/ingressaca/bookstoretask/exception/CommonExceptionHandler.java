package com.ingressaca.bookstoretask.exception;

import com.ingressaca.bookstoretask.dto.ApiErrorDTO;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class CommonExceptionHandler {

    private final MessageSource messageSource;


    public CommonExceptionHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }


    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<ApiErrorDTO> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception, HttpServletRequest request, Locale locale) {

        ApiErrorDTO error = new ApiErrorDTO(422, messageSource.getMessage("methodArgumentNotValid" , null, locale), request.getServletPath());

        BindingResult result = exception.getBindingResult();
        Map<String, String> map = new HashMap<>();

        result.getFieldErrors().forEach(fieldError -> map.put(fieldError.getField(), fieldError.getDefaultMessage()));
        error.setValidationErr(map);

        return new ResponseEntity<>(error, HttpStatus.UNPROCESSABLE_ENTITY);
    }


    @ExceptionHandler(value = NoSuchElementException.class)
    public ResponseEntity<ApiErrorDTO> handleUserNotFoundException(HttpServletRequest request, Locale locale) {
        ApiErrorDTO error = new ApiErrorDTO(404, messageSource.getMessage("noSuchElement", null, locale), request.getServletPath());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = BadCredentialsException.class)
    public ResponseEntity<ApiErrorDTO> handleBadCredentialsException(HttpServletRequest request, Locale locale) {
        ApiErrorDTO error = new ApiErrorDTO(404, messageSource.getMessage("badCredentials", null, locale), request.getServletPath());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }


}

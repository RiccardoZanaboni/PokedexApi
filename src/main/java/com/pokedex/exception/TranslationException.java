package com.pokedex.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class TranslationException extends RuntimeException {
    public TranslationException(String message) {
        super(message);
    }
}

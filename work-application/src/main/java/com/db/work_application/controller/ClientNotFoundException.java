package com.db.work_application.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import static java.lang.String.format;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ClientNotFoundException extends RuntimeException {
    public ClientNotFoundException(long id) {
        super(format("Client with id %d not found!", id));
    }
}

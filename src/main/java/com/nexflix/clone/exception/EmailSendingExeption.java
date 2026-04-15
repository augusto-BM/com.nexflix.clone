package com.nexflix.clone.exception;

public class EmailSendingExeption extends RuntimeException {
    public EmailSendingExeption(String message, Throwable cause) {
        super(message, cause);
    }
}

package com.nexflix.clone.exception;

public class AccountDesactivatedExeption extends RuntimeException {
    public AccountDesactivatedExeption(String message) {
        super(message);
    }
}

package com.yusufsezer.exception;

public class AuthorNotFound
        extends RuntimeException {

    public AuthorNotFound() {
        super();
    }

    public AuthorNotFound(String message) {
        super(message);
    }

}

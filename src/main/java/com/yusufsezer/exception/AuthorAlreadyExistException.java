package com.yusufsezer.exception;

public class AuthorAlreadyExistException
        extends RuntimeException {

    public AuthorAlreadyExistException() {
        super();
    }

    public AuthorAlreadyExistException(String message) {
        super(message);
    }

}

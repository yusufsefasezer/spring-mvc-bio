package com.yusufsezer.exception;

public class PageNotFound
        extends RuntimeException {

    public PageNotFound() {
        super();
    }

    public PageNotFound(String message) {
        super(message);
    }

}

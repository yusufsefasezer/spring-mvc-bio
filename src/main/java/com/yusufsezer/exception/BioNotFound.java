package com.yusufsezer.exception;

public class BioNotFound
        extends RuntimeException {

    public BioNotFound() {
        super();
    }

    public BioNotFound(String message) {
        super(message);
    }

}

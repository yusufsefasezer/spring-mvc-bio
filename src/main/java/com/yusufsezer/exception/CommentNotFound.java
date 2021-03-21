package com.yusufsezer.exception;

public class CommentNotFound
        extends RuntimeException {

    public CommentNotFound() {
        super();
    }

    public CommentNotFound(String message) {
        super(message);
    }

}

package com.hodolog.hodolog.api.exception;

public class PostNotFound extends RuntimeException{

    private static String MESSAGE = "존재하지 않는 게시 글입니다.";

    public PostNotFound() {
        super(MESSAGE);
    }

    public PostNotFound(Throwable cause) {
        super(MESSAGE, cause);
    }
}
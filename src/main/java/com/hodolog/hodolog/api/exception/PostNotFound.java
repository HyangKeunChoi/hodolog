package com.hodolog.hodolog.api.exception;

/*
 * 404
 */
public class PostNotFound extends HodologException {

    private static String MESSAGE = "존재하지 않는 게시 글입니다.";

    public PostNotFound() {
        super(MESSAGE);
    }

    public PostNotFound(Throwable cause) {
        super(MESSAGE, cause);
    }

    @Override
    public int getStatusCode() {
        return 404;
    }
}

package com.hodolog.hodolog.api.exception;

import lombok.Getter;

/*
 * status -> 400
 */
@Getter
public class InvalidRequest extends HodologException {

    private static final String MESSAGE = "존재하지 않는 글입니다.";

  /*  public String fileldName;
    public String message;*/

    public InvalidRequest() {
        super(MESSAGE);
    }

    /*public InvalidRequest(String fieldName, String message) {
        super(MESSAGE);
        this.fileldName = fieldName;
        this.message = message;
    }*/

    public InvalidRequest(String fieldName, String message) {
        super(MESSAGE);
        addValidation(fieldName, message);
    }

    @Override
    public int getStatusCode() {
        return 400;
    }
}

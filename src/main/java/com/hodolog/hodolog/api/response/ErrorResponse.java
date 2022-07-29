package com.hodolog.hodolog.api.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;

/*
 * {
 *      "code" : "400",
 *      "mesage" : "잘못된 요청입니다."
 *      "validation" : {
 *              "title" : "값을 입력해 주세요"
 *      }
 * }
 */
@Getter
@JsonInclude(value = JsonInclude.Include.NON_EMPTY) /*비어 있지 않는 값만 응답으로 나감*/
public class ErrorResponse {
    private final String code;
    private final String message;
    private final Map<String, String> validation;

    @Builder
    public ErrorResponse(String code, String message, Map<String, String> validation) {
        this.code = code;
        this.message = message;
        this.validation = validation;
    }

    public void addValidation(String field, String errorMessage) {
        this.validation.put(field, errorMessage);
    }
}

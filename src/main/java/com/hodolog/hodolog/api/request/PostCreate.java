package com.hodolog.hodolog.api.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@Setter
@Getter
@ToString
public class PostCreate {

    // not blank일때의 에러메시지 - 빈값이거나 null일때 체크 해주는 어노테이션
    @NotBlank(message = "타이틀을 입력해 주세요.")
    private String title;

    @NotBlank(message = "컨텐츠를 입력해 주세요")
    private String content;
}

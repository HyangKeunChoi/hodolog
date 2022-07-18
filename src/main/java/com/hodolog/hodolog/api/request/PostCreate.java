package com.hodolog.hodolog.api.request;

import lombok.Builder;
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

    public PostCreate() {
    }

    @Builder // 클래스에 달면 제대로 작동 안할 수 있어서 생성자에 다는것 추천
    public PostCreate(String title, String content) {
        this.title = title;
        this.content = content;
    }

    // 빌더패턴의 장점
    // 1. 가독성이 좋다. (값 생성에 대한 유연함)
    // 2. 필요한 값만 받을 수 있다. // -> 생성자 오버로딩을 여러개 할 필요가 없다.
    // 3. 객체의 불변성
}

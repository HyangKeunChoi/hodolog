package com.hodolog.hodolog.api.controller;

import com.hodolog.hodolog.api.request.PostCreate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Map;

@Slf4j
@RestController
public class PostController {

    // SSR -> jsp, thymeleaf, mustache, freemarker
    // SPA -> vue, react
    // vue + SSR = nuxt
    // react + SSR = next

    /*@PostMapping("/posts")
    public String post(@RequestParam String title, @RequestParam String content) {
        log.info("title={}, content={}", title, content);
        return "Hello World";
    }*/

    @PostMapping("/posts")
    public Map<String, String> post(@RequestBody @Valid PostCreate params) throws Exception {
        // 데이터를 검증하는 이유

        // 1. client 개발자가 깜빡할 수 있다. 실수로 값을 안보낼 수 있다.
        // 2. client bug로 인해 값이 누락될 수 있다.
        // 3. 외부에 나쁜 사람이 값을 임의로 조작해서 보낼 수 있다.
        // 4. DB에 값을 저장할 때 의도치 않은 오류가 발생할 수 있다.
        // 5. 서버 개발자의 편안함을 위해서

        // 이런 형태의 검증은 안좋다.
        // 1. 빡세다(노가다)
        // 2. 무언가 3번이상 반복작업을 할때 내가 뭔가 잘못하고 있는건 아닌지 의심
        // 3. 누락 가능성
        // 4. 생각보다 검증해야할 게 많다. (꼼꼼하지 않을 수 있다.)
        // 5. 뭔가 개발자 스럽지 않다. -> 간지가 나지 않는다.
        /*var title = params.getTitle();
        if(title == null || "".equals(title)) {
            throw new Exception("타이틀이 없어요");
        }

        var content = params.getContent();
        if(content == null || "".equals(content)) {
            throw new Exception("컨텐츠가 없어요");
        }*/

        // 문제점
        // 1. 매번 메서드마다 값을 검증 해야한다.
        // > 개발자가 까먹을 수 있다.
        // > 검증 부분에서 버그가 발생할 여지가 높다.
        // > 지겹다
        // 2. 응답값에 hashmap -> 응답 클래스를 만드는게 좋다.
        // 3. 여러개의 에러처리 힘듦 - 현재는 첫번째 에러만 처리( - fieldErrorList.get(0) )
        // 4. 세 번이상의 반복적인 작업은 피해야 한다.
        /*if(result.hasErrors()) {
            List<FieldError> fieldErrorList = result.getFieldErrors();
            FieldError firstFieldError = fieldErrorList.get(0);
            String fieldName = firstFieldError.getField(); // title
            String errorMessage = firstFieldError.getDefaultMessage(); // ..에러메시지

            Map<String, String> error = new HashMap<>();
            error.put(fieldName, errorMessage);
            return error;
        }*/
        return Map.of();
    }

    // HTTP Method
    // GET, POST, PUT, PATCH, DELETE, HEAD, TRACE, CONNECT
    // 글등록
    // POST METHOD

}

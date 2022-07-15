package com.hodolog.hodolog.api.controller;

import com.hodolog.hodolog.api.request.PostCreate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
    public String post(@RequestBody PostCreate params) {
        log.info("params={}", params.toString());
        return "Hello World";
    }

    // HTTP Method
    // GET, POST, PUT, PATCH, DELETE, HEAD, TRACE, CONNECT
    // 글등록
    // POST METHOD

}

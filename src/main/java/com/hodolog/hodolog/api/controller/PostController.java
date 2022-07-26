package com.hodolog.hodolog.api.controller;

import com.hodolog.hodolog.api.request.PostCreate;
import com.hodolog.hodolog.api.request.PostEdit;
import com.hodolog.hodolog.api.request.PostSearch;
import com.hodolog.hodolog.api.response.PostResponse;
import com.hodolog.hodolog.api.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

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
    public void post(@RequestBody @Valid PostCreate request) throws Exception {
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

        // 넘어온 데이터를 DB에 저장
        // Case1. 저장한 데이터 Entity를 내려주기
        // Case2. 저장한 데이터의 Id를 내려주기
        //      Client에서는 수신한 id를 글 조회 API를 통해서 데이터를 수신받음
        // Case3. 응답 필요 없음 -> 클라이언트에서 모든 POST데이터 context를 관리함
        postService.write(request);
        // return Map.of("postId", postId);
    }

    // HTTP Method
    // GET, POST, PUT, PATCH, DELETE, HEAD, TRACE, CONNECT
    // 글등록
    // POST METHOD

    /*
     * /posts -> 글 전체 조회(검색 + 페이징)
     * /posts/{postId} -> 글 한개 조회
     */
    @GetMapping("/posts/{postId}")
    public PostResponse get(@PathVariable(name = "postId") Long postId) {
        return postService.get(postId);
    }

    // 조회 API
    // 지난 시간 - 단건 조회 API(1개의 글post를 가져오는 기능)
    // 이번 시간 - 여러개의 글을 조회 API - 게시글 목록
    // /posts

    @GetMapping("/posts")
    public List<PostResponse> getList(@ModelAttribute PostSearch postSearch) {
        // Pageable page : 웹 요청으로 넘어올때 페이징 인덱스가 0이 아닌 1로 시작 하기 위한 처리 + 기본값 5개씩 처리 (application.xml에 따라서)
        return postService.getList(postSearch);
    }

    @PatchMapping("/posts/{postId}")
    public void edit(@PathVariable Long postId, @RequestBody @Valid PostEdit request) {
        postService.edit(postId, request);
    }

    @DeleteMapping("/posts/{postId}")
    public void delete(@PathVariable Long postId) {
        postService.delete(postId);
    }

    @DeleteMapping("/posts/{postId}")
    public void delete(@PathVariable Long postId) {
        postService.delete(postId);
    }
}

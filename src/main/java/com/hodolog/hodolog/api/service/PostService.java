package com.hodolog.hodolog.api.service;

import com.hodolog.hodolog.api.domain.Post;
import com.hodolog.hodolog.api.domain.PostEditor;
import com.hodolog.hodolog.api.repository.PostRepository;
import com.hodolog.hodolog.api.request.PostCreate;
import com.hodolog.hodolog.api.request.PostEdit;
import com.hodolog.hodolog.api.request.PostSearch;
import com.hodolog.hodolog.api.response.PostResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public void write(PostCreate postCreate) {

        // postCreate -> Entity형태로 변환
        // 추천하지 않는방법 - 외부에 오픈하지 않는 방식으로 변경
        /*Post post = new Post();
        post.title = postCreate.getTitle();
        post.content = postCreate.getContent();*/

        Post post = Post.builder()
                .title(postCreate.getTitle())
                .content(postCreate.getContent())
                .build();

        postRepository.save(post);
    }

    public PostResponse get(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 글입니다."));


        return PostResponse.builder()
                .id(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .build();
    }

    // 문제점 글이 너무 많은 경우 -> 비용이 너무 많이 든다.
    //    // 글이 -> 100,000,00 -> DB글 모두 조회하는 경우 -> DB가 뻗을 수 있다.
    //    // DB -> 애플리케이션 서버로 전달하는 시간, 트래픽비용 등이 많이 발생할 수 있다.
    public List<PostResponse> getList(PostSearch postSearch) {
        // 파라미터로 받기때문에 필요없는 코드가 되었다.
        // Pageable pageable = PageRequest.of(page, 5, Sort.by(Sort.Direction.DESC, "id"));

        return postRepository.getList(postSearch)
                .stream().map(PostResponse::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public void edit(Long id, PostEdit postEdit) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 글입니다."));

        // 값을 변경할떄 다른값으로 매핑시키는 위험을 방지하기 위해
        // 빌더 패턴을 이용한다.
        PostEditor.PostEditorBuilder editorBuilder = post.toEditor();

        // TODO : 이해하기 어려움
        // 필요한 값만 변경한다.
        // 만약 content가 null로 넘어오면
        // null로 업데이트가 되는게 아니라
        // 기존 값 그대로 유지한다.
        PostEditor postEditor = editorBuilder.title(postEdit.getTitle())
                .content(postEdit.getContent())
                .build();

        post.edit(postEditor);
    }
}

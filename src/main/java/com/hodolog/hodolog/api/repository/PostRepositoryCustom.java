package com.hodolog.hodolog.api.repository;

import com.hodolog.hodolog.api.domain.Post;
import com.hodolog.hodolog.api.request.PostSearch;

import java.util.List;

public interface PostRepositoryCustom {

    List<Post> getList(PostSearch postSearch);
}

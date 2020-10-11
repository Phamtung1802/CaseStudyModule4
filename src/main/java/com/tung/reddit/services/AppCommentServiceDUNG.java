package com.tung.reddit.services;

import com.tung.reddit.models.AppComment;
import com.tung.reddit.models.AppPost;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service

public interface AppCommentServiceDUNG {
    Iterable<AppComment> getAllComment();
    AppComment getCommentById(long id);
    AppComment save(AppComment comment);
    void remove(AppComment comment);
    Page<AppComment> getAllCommentByPost(AppPost post, Pageable pageable);
    Long countAllByPost (AppPost post);
}

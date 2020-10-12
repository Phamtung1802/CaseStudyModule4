package com.tung.reddit.services.impl;


import com.tung.reddit.models.AppComment;
import com.tung.reddit.models.AppPost;
import org.springframework.stereotype.Service;

@Service
public interface AppCommentService {
    public Iterable<AppComment> findAllByPost(AppPost appPost);
    public void save(AppComment appComment);

}
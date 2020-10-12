package com.tung.reddit.services.impl;

import com.tung.reddit.models.AppPost;
import org.springframework.stereotype.Service;

@Service
public interface AppPostService {
    public Iterable<AppPost> findAll();
    public AppPost save(AppPost appPost);
    public Iterable<AppPost> findAllByPostNameContains(String string);
    public AppPost findByPostID(long id);
    public void deleteAppPostByPostId(Long id);



}

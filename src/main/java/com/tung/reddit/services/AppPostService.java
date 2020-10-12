package com.tung.reddit.services;

import com.tung.reddit.models.AppPost;
import com.tung.reddit.models.AppRole;
import org.springframework.stereotype.Service;

@Service
public interface AppPostService {
    public Iterable<AppPost> findAll();
    public AppPost save(AppPost appPost);
    public Iterable<AppPost> findAllByPostNameContains(String string);
    public AppPost findByPostID(long id);
    public void deleteAppPostByPostId(Long id);



}

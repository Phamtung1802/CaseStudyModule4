package com.tung.reddit.services.impl;

import com.tung.reddit.models.AppComment;
import com.tung.reddit.models.AppPost;
import com.tung.reddit.services.AppCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repositories.AppCommentRepository;
import repositories.AppPostRepository;

@Service
public class AppCommentServiceImpl implements AppCommentService {
    @Autowired
    AppCommentRepository appCommentRepository;

    @Override
    public Iterable<AppComment> findAllByPost(AppPost appPost){
        return appCommentRepository.findAllByPost(appPost);
    }

    @Override
    public void save(AppComment appComment) {
        appCommentRepository.save(appComment);
    }

}
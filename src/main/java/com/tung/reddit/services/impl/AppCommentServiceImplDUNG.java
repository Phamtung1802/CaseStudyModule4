package com.tung.reddit.services.impl;

import com.tung.reddit.models.AppComment;
import com.tung.reddit.models.AppPost;
import com.tung.reddit.repository.AppCommentRepository;
import com.tung.reddit.services.AppCommentServiceDUNG;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component
public class AppCommentServiceImplDUNG implements AppCommentServiceDUNG {
    @Autowired
    AppCommentRepository appCommentRepository;


    @Override
    public Iterable<AppComment> getAllComment() {
        return appCommentRepository.findAll();
    }

    @Override
    public AppComment getCommentById(long id) {
        return appCommentRepository.findById(id).orElse(null);
    }

    @Override
    public AppComment save(AppComment comment) {
        return appCommentRepository.save(comment);
    }

    @Override
    public void remove(AppComment comment) {
        if (comment != null)
        appCommentRepository.delete(comment);
    }

    @Override
    public Page<AppComment> getAllCommentByPost(AppPost post, Pageable pageable) {
        return appCommentRepository.getAllByPost(post, pageable);
    }

    @Override
    public Long countAllByPost(AppPost post) {
        return appCommentRepository.countAllByPost(post);
    }
}
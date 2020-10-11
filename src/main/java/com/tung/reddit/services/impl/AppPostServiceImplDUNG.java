package com.tung.reddit.services.impl;

import com.tung.reddit.models.AppPost;
import com.tung.reddit.models.AppUser;
import com.tung.reddit.models.Status;
import com.tung.reddit.repository.AppPostRepositoryDUNG;
import com.tung.reddit.services.AppPostServiceDUNG;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service

public class AppPostServiceImplDUNG implements AppPostServiceDUNG {

    @Autowired
    private AppPostRepositoryDUNG appPostRepositoryDUNG;

    @Override
    public Page<AppPost> getAllPost(Pageable pageable) {
        return appPostRepositoryDUNG.findAll(pageable);
    }

    @Override
    public AppPost getPostById(long id) {
        return appPostRepositoryDUNG.findById(id).orElse(null);
    }

    @Override
    public AppPost save(AppPost post) {
        return appPostRepositoryDUNG.save(post);
    }

    @Override
    public void remove(AppPost post) {
        appPostRepositoryDUNG.delete(post);
    }

    @Override
    public Page<AppPost> getAllPostByUser(AppUser user, Pageable pageable) {
        return appPostRepositoryDUNG.getAllByAppUser(user, pageable);
    }

    @Override
    public Page<AppPost> getAllPostByStatus(Status status, Pageable pageable) {
        return appPostRepositoryDUNG.getAllByStatus(status,pageable);
    }

    @Override
    public void setStatusForPost(Long statusID, Long postID) {
            appPostRepositoryDUNG.setStatusForPost(statusID, postID);
    }
}

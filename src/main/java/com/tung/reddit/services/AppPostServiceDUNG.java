package com.tung.reddit.services;

import com.tung.reddit.models.AppPost;
import com.tung.reddit.models.AppUser;
import com.tung.reddit.models.AppStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AppPostServiceDUNG {
    Page<AppPost> getAllPost(Pageable pageable);
    AppPost getPostById(long id);
    AppPost save(AppPost post);
    void remove(AppPost post);
    Page<AppPost> getAllPostByUser(AppUser user, Pageable pageable);
    Page<AppPost> getAllPostByStatus(AppStatus status, Pageable pageable);
    void setStatusForPost(Long statusID,Long postID);

    Page<AppPost> findAllPostByUserLiked(Long appUserId, Pageable pageable);

}

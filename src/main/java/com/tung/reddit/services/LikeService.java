package com.tung.reddit.services;

import com.tung.reddit.models.AppPost;
import com.tung.reddit.models.AppUser;

import java.util.List;

public interface LikeService {
    Iterable<AppLike> findAllByPost(AppPost post);
    Iterable<AppLike> findAllByUser(AppUser user);
    void save(AppLike like);
    void remove(AppLike like);
    boolean existsByAppUserAndPost(AppUser user, AppPost post);
    Long countAllByPost (AppPost post);
    AppLike getByAppUserAndPost (AppUser user, AppPost post);
    List<Long> getListLikedUserIds();
}

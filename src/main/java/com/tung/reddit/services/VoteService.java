package com.tung.reddit.services;

import com.tung.reddit.models.AppPost;
import com.tung.reddit.models.AppUser;
import com.tung.reddit.models.AppVote;

public interface VoteService {
    Iterable<AppVote> findAllByPost(AppPost post);
    Iterable<AppVote> findAllByUser(AppUser user);
    void save(AppVote vote);
    void remove(AppVote vote);
    boolean existsByAppUserAndAndPost(AppUser user, AppPost post);
    Long sumOfValues (AppPost post);
    AppVote getByAppUserAndAndPost (AppUser user, AppPost post);
}

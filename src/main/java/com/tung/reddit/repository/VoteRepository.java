package com.tung.reddit.repository;

import com.tung.reddit.models.AppPost;
import com.tung.reddit.models.AppUser;
import com.tung.reddit.models.AppVote;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface VoteRepository extends CrudRepository<AppVote,Long> {
    Iterable<AppVote> findAllByPost(AppPost post);
    Iterable<AppVote> findAllByAppUser(AppUser user);
    boolean existsByAppUserAndAndPost(AppUser user, AppPost post);

    @Query(value = "select sum(value) from votes where post_id = ?1", nativeQuery = true)
    Long sumOfValues (Long postId);

    AppVote getByAppUserAndAndPost (AppUser user, AppPost post);
}

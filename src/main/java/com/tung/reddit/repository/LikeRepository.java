package com.tung.reddit.repository;

import com.tung.reddit.models.AppPost;
import com.tung.reddit.models.AppUser;
import com.tung.reddit.models.AppLike;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface LikeRepository extends CrudRepository<AppLike,Long> {
    Iterable<AppLike> findAllByPost(AppPost post);
    Iterable<AppLike> findAllByAppUser(AppUser user);
    boolean existsByAppUserAndPost(AppUser user, AppPost post);
    Long countAllByPost (AppPost post);
    AppLike getByAppUserAndPost (AppUser user, AppPost post);
    @Query(value = "select app_user_id from likes",nativeQuery = true)
    List<Long> getListLikedUserIds();
}

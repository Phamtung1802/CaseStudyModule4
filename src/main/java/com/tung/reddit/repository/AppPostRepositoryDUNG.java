package com.tung.reddit.repository;

import com.tung.reddit.models.AppPost;
import com.tung.reddit.models.AppUser;
import com.tung.reddit.models.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface AppPostRepositoryDUNG extends PagingAndSortingRepository<AppPost, Long> {
    Page<AppPost> getAllByAppUser(AppUser appUser, Pageable pageable);

    Page<AppPost> getAllByStatus(Status status, Pageable pageable);

    @Query(value = "update post p set p.status_id = ?1 where p.id = ?2", nativeQuery = true)
    void setStatusForPost(Long statusID, Long postID);

    @Query(value = "select * from post where post.id in (select likes.post_id from likes where likes.app_user_id = ?1)", nativeQuery = true)
    Page <AppPost> findAllPostByUserLiked(Long appUserId, Pageable pageable);


}

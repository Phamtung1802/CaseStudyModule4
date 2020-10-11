package com.tung.reddit.repository;

import com.tung.reddit.models.AppComment;
import com.tung.reddit.models.AppPost;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
@Component
public interface AppCommentRepository extends CrudRepository<AppComment, Long> {
    Page<AppComment> getAllByPost(AppPost post, Pageable pageable);
    Long countAllByPost (AppPost post);
}
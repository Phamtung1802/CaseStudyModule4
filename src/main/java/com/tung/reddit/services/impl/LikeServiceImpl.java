package com.tung.reddit.services.impl;

import com.tung.reddit.models.AppLike;
import com.tung.reddit.models.AppPost;
import com.tung.reddit.models.AppUser;
import com.tung.reddit.repository.LikeRepository;
import com.tung.reddit.services.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Component
public class LikeServiceImpl implements LikeService {

    @Autowired
    private LikeRepository likeRepository;

    @Override
    public Iterable<AppLike> findAllByPost(AppPost post) {
        return likeRepository.findAllByPost(post);
    }

    @Override
    public Iterable<AppLike> findAllByUser(AppUser user) {
        return likeRepository.findAllByAppUser(user);
    }

    @Override
    public void save(AppLike like) {
        likeRepository.save(like);
    }

    @Override
    public void remove(AppLike like) {
        likeRepository.delete(like);
    }

    @Override
    public boolean existsByAppUserAndPost(AppUser user, AppPost post) {
        return likeRepository.existsByAppUserAndPost(user, post);
    }

    @Override
    public Long countAllByPost(AppPost post) {
        return likeRepository.countAllByPost(post);
    }

    @Override
    public AppLike getByAppUserAndPost(AppUser user, AppPost post) {
        return likeRepository.getByAppUserAndPost(user, post);
    }

    @Override
    public List<Long> getListLikedUserIds() {
        return likeRepository.getListLikedUserIds();
    }
}

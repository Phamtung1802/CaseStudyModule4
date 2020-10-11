package com.tung.reddit.services.impl;

import com.tung.reddit.models.AppPost;
import com.tung.reddit.models.AppUser;
import com.tung.reddit.models.AppVote;
import com.tung.reddit.repository.VoteRepository;
import com.tung.reddit.services.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component
public class VoteServiceImpl implements VoteService {

    @Autowired
    private VoteRepository voteRepository;

    @Override
    public Iterable<AppVote> findAllByPost(AppPost post) {
        return voteRepository.findAllByPost(post);
    }

    @Override
    public Iterable<AppVote> findAllByUser(AppUser user) {
        return voteRepository.findAllByAppUser(user);
    }

    @Override
    public void save(AppVote vote) {
       voteRepository.save(vote);
    }

    @Override
    public void remove(AppVote vote) {
        voteRepository.delete(vote);
    }

    @Override
    public boolean existsByAppUserAndAndPost(AppUser user, AppPost post) {
        return voteRepository.existsByAppUserAndAndPost(user, post);
    }

    @Override
    public Long sumOfValues(AppPost post) {
        return null;
    }


    @Override
    public AppVote getByAppUserAndAndPost(AppUser user, AppPost post) {
        return voteRepository.getByAppUserAndAndPost(user, post);
    }
}

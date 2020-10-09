package com.tung.reddit.services;

import com.tung.reddit.models.AppPost;
import com.tung.reddit.models.AppRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repositories.AppPostRepository;
import repositories.AppRoleRepository;

@Service
public class AppPostServiceImpl implements AppPostService{
    @Autowired
    AppPostRepository appPostRepository;

    @Override
    public Iterable<AppPost> findAll() {
        return appPostRepository.findAll();
    }

    @Override
    public AppPost save(AppPost appPost){
       return appPostRepository.save(appPost);
    }

    @Override
    public Iterable<AppPost> findAllByPostNameContains(String string) {
        return appPostRepository.findAllByPostNameContains(string);
    }

}

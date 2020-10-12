package com.tung.reddit.services.impl;

import com.tung.reddit.models.AppRole;
import com.tung.reddit.services.AppRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repositories.AppRoleRepository;

@Service
public class AppRoleServiceImpl implements AppRoleService {
    @Autowired
    AppRoleRepository appRoleRepository;

    @Override
    public Iterable<AppRole> findAll() {
        return appRoleRepository.findAll();
    }

}
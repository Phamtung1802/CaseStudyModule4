package com.example.demo.services;

import com.example.demo.models.AppRole;
import com.example.demo.models.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repositories.AppRoleRepository;
import repositories.AppUserRepository;

@Service
public class AppRoleServiceImpl implements AppRoleService{
    @Autowired
    AppRoleRepository appRoleRepository;

    @Override
    public Iterable<AppRole> findAll() {
        return appRoleRepository.findAll();
    }

}

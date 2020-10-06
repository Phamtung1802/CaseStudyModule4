package com.example.demo.services;

import com.example.demo.models.AppRole;
import com.example.demo.models.AppUser;
import org.springframework.stereotype.Service;

@Service
public interface AppRoleService {
    public Iterable<AppRole> findAll();

}

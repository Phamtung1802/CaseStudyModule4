package com.tung.reddit.services.impl;

import com.tung.reddit.models.AppRole;
import org.springframework.stereotype.Service;

@Service
public interface AppRoleService {
    public Iterable<AppRole> findAll();

}

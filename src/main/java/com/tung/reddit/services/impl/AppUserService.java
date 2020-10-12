package com.tung.reddit.services.impl;

import com.tung.reddit.models.AppUser;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface AppUserService {
    public Optional<AppUser> findByUsername(String username);
    public Iterable<AppUser> findAll();
    AppUser save(AppUser user);
    public void delete(AppUser user);
    AppUser findByUserId(long l);
    public AppUser findFirstByUsername(String username);

}

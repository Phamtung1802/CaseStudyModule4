package com.tung.reddit.services;

import com.tung.reddit.models.AppUser;

import java.util.Optional;

public interface AppUserServiceDUNG {
    Iterable<AppUser> getAllUser();
    AppUser getUserById(Long id);
    AppUser save(AppUser user);
    void remove(AppUser user);
     AppUser getUserByUserName(String userName);

}

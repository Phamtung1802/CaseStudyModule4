package com.example.demo.services;

import com.example.demo.models.AppUser;
import org.springframework.stereotype.Service;

@Service
public interface AppUserService {
    public AppUser findByUsername(String username);
    public Iterable<AppUser> findAll();
    AppUser save(AppUser user);
    public void delete(AppUser user);

}

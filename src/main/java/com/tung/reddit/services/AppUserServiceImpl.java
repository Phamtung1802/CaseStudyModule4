package com.tung.reddit.services;

import com.tung.reddit.models.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import repositories.AppUserRepository;

@Service
public class AppUserServiceImpl implements AppUserService, UserDetailsService {
    @Autowired
    AppUserRepository appUserRepository;

    @Override
    public AppUser findByUsername(String username) {
        return appUserRepository.findByUsername(username);
    }

    @Override
    public Iterable<AppUser> findAll() {
        return appUserRepository.findAll();
    }

    @Override
    public AppUser save(AppUser user) {
        appUserRepository.save(user);
        return user;
    }

    @Override
    public void delete(AppUser user) {
        appUserRepository.delete(user);
    }

    @Override
    public AppUser findByUserId(long l) {
        return appUserRepository.findByUserId(l);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return (UserDetails) appUserRepository.findByUsername(username);
    }
}

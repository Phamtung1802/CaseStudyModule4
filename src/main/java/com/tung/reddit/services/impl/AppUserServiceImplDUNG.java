package com.tung.reddit.services.impl;

import com.tung.reddit.models.AppRole;
import com.tung.reddit.models.AppUser;
import com.tung.reddit.services.AppUserServiceDUNG;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import com.tung.reddit.repository.AppUserRepositoryDUNG;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
@Service
@Component
public class AppUserServiceImplDUNG implements AppUserServiceDUNG, UserDetailsService {
    @Autowired
    private AppUserRepositoryDUNG appUserRepositoryDUNG;

    @Override
    public Iterable<AppUser> getAllUser() {
        return appUserRepositoryDUNG.findAll();
    }

    @Override
    public AppUser getUserById(Long id) {
        return appUserRepositoryDUNG.findById(id).orElse(null);
    }

    @Override
    public AppUser save(AppUser user) {
        return appUserRepositoryDUNG.save(user);
    }

    @Override
    public void remove(AppUser user) {
        appUserRepositoryDUNG.delete(user);
    }

    @Override
    public AppUser getUserByUserName(String userName) {
        return appUserRepositoryDUNG.findAppUserByUsername(userName);
    }




    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser = appUserRepositoryDUNG.findAppUserByUsername(username);
        if (appUser == null)
            throw new UsernameNotFoundException(username);
        List<AppRole> appRoles = new ArrayList<>();
        appRoles.add(appUser.getRole());
        User user = new User(appUser.getUsername(), appUser.getPassword(), true,true,true,true,
                Arrays.asList((new SimpleGrantedAuthority("ROLE_USER"))));
        return user;
    }
}


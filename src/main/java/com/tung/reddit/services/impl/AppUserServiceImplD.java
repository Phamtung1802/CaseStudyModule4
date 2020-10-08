package com.tung.reddit.services.impl;

import com.tung.reddit.models.AppRole;
import com.tung.reddit.models.AppUser;
import com.tung.reddit.services.AppUserServiceD;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import repositories.AppUserRepositoryD;


import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Component

public class AppUserServiceImplD implements AppUserServiceD,UserDetailsService {

    @Autowired
    private AppUserRepositoryD appUserRepositoryD;

    @Override
    public Iterable<AppUser> getAllUser() {
        return appUserRepositoryD.findAll();
    }

    @Override
    public AppUser getUserById(Long id) {
        return appUserRepositoryD.findById(id).orElse(null);
    }

    @Override
    public AppUser save(AppUser user) {
        return appUserRepositoryD.save(user);
    }

    @Override
    public void remove(AppUser user) {
        appUserRepositoryD.delete(user);
    }

    @Override
    public Optional<AppUser> getUserByUserName(String userName) {
        return appUserRepositoryD.findAppUsersByUsername(userName);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<AppUser> optionalAppUser = appUserRepositoryD.findAppUsersByUsername(username);
        List<AppRole> roles = new ArrayList<>();

        if (optionalAppUser.isPresent()) {
            AppUser appUser = optionalAppUser.get();
            roles.add(appUser.getRole());
            User user = new User(appUser.getUsername(), appUser.getPassword(), roles);
            return user;
        } else {
            throw new UsernameNotFoundException(MessageFormat.format("User with email {0} cannot be found.", username));
        }
    }
}

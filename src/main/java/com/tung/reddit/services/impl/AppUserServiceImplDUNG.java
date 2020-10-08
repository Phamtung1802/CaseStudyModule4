package com.tung.reddit.services.impl;

import com.tung.reddit.models.AppRole;
import com.tung.reddit.models.AppUser;
import com.tung.reddit.services.AppUserServiceDUNG;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import repositories.AppUserRepositoryDUNG;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
    public Optional<AppUser> getUserByUserName(String userName) {
        return appUserRepositoryDUNG.findAppUserByUsername(userName);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<AppUser> optionalUser = appUserRepositoryDUNG.findAppUserByUsername(username);
        List<AppRole> roles = new ArrayList<>();

        if (optionalUser.isPresent()) {
            AppUser appUser = optionalUser.get();
            roles.add(appUser.getRole());
            User user = new User(appUser.getUsername(),appUser.getPassword(),roles);
            return user;
        }
        else {
            throw new UsernameNotFoundException(MessageFormat.format("User with email {0} cannot be found.", username));
        }
    }
}

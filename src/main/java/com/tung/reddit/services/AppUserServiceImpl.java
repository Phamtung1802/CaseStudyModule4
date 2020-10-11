package com.tung.reddit.services;

import com.tung.reddit.models.AppRole;
import com.tung.reddit.models.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import repositories.AppUserRepository;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AppUserServiceImpl implements AppUserService, UserDetailsService {
    @Autowired
    AppUserRepository appUserRepository;

    @Override
    public Optional<AppUser> findByUsername(String username) {
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
    public AppUser findFirstByUsername(String username) {
        return appUserRepository.findFirstByUsername(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<AppUser> optionalAppUser = appUserRepository.findByUsername(username);
        List<AppRole> roles = new ArrayList<>();
        if (optionalAppUser.isPresent()) {
            AppUser appUser = optionalAppUser.get();
            roles.add(appUser.getRole());
            User user = new User(appUser.getUsername(), appUser.getPassword(), roles);
            if(appUser.isEnabled()==false){
                throw new UsernameNotFoundException(MessageFormat.format("Banned User", username));
            }
            return user;
        } else {
            throw new UsernameNotFoundException(MessageFormat.format("User with email {0} cannot be found.", username));
        }
    }
}

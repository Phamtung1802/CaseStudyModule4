package com.tung.reddit.controllers;

import com.tung.reddit.models.AppRole;
import com.tung.reddit.models.AppUser;
import com.tung.reddit.services.AppRoleService;
import com.tung.reddit.services.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.ModelAndView;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class HomeController {
    @Autowired
    private AppRoleService appRoleServiceImpl;

    @Autowired
    private AppUserService appUserServiceImpl;

    @GetMapping("/")
    public ModelAndView  home() {
        ModelAndView modelAndView = new ModelAndView("/index");
        return modelAndView;
    }

    @GetMapping( value = "/login")
    public ModelAndView login() {
        ModelAndView modelAndView = new ModelAndView("/index");
        return modelAndView;
    }

    @GetMapping(value = "/create")
    public ModelAndView createUser() {
        ModelAndView modelAndView = new ModelAndView("/create");
        return modelAndView;
    }

    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE)

    public ResponseEntity<AppUser> createUserPost(@RequestBody AppUser appUser) {
        Instant time=LocalDateTime.ofInstant(Instant.now(), ZoneOffset.UTC).toInstant(ZoneOffset.UTC);
        appUser.setCreated(time);
        appUser.setEnabled(true);
        AppRole role= new AppRole();
        role.setId(3L);
        appUser.setRole(role);
        AppUser savedUser = appUserServiceImpl.save(appUser);
        ResponseEntity<AppUser> response= new ResponseEntity<AppUser>(savedUser, HttpStatus.OK);
        return response;
    }

}

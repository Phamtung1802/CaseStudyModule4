package com.tung.reddit.controllers;

import com.tung.reddit.models.AppRole;
import com.tung.reddit.models.AppUser;
import com.tung.reddit.services.AppRoleService;
import com.tung.reddit.services.AppRoleServiceDUNG;
import com.tung.reddit.services.AppUserService;
import com.tung.reddit.services.AppUserServiceDUNG;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.ModelAndView;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class HomeControllerDUNG {
    @Autowired
    private AppRoleServiceDUNG appRoleServiceDUNGImpl;

    @Autowired
    private AppUserServiceDUNG appUserServiceDUNGImpl;

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
        modelAndView.addObject("user", new AppUser());
        return modelAndView;
    }

    @PostMapping("/create")
    public String createAppUser(AppUser appUser) {
        Instant time=LocalDateTime.ofInstant(Instant.now(), ZoneOffset.UTC).toInstant(ZoneOffset.UTC);
        appUser.setCreated(time);
        appUser.setEnabled(true);
        AppRole appRole = appRoleServiceDUNGImpl.getRoleByName("ROLE_PREMIUM_USER");
        appUser.setRole(appRole);
        appUserServiceDUNGImpl.save(appUser);
        return "redirect:/";
    }

}

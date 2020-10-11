package com.tung.reddit.controllers;

import com.tung.reddit.models.AppRole;
import com.tung.reddit.models.AppUser;
import com.tung.reddit.services.AppRoleServiceDUNG;
import com.tung.reddit.services.AppUserServiceDUNG;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Controller

public class HomeControllerDUNG {

    @Autowired
    private AppRoleServiceDUNG appRoleServiceImplDUNG;

    @Autowired
    private AppUserServiceDUNG appUserServiceImplDUNG;

    @ModelAttribute("user")
    private AppUser getPrincipal() {
        AppUser appUser = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            appUser = appUserServiceImplDUNG.getUserByUserName(((UserDetails)principal).getUsername());
        }
        return appUser;
    }

    @GetMapping({"/","/home"})
    public ModelAndView  home() {
        ModelAndView modelAndView = new ModelAndView("/index");
        return modelAndView;
    }

    @GetMapping("/login")
    public ModelAndView login() {
        ModelAndView modelAndView = new ModelAndView("/index");
        return modelAndView;
    }

    @GetMapping( "/create-account")
    public ModelAndView createUser() {
        ModelAndView modelAndView = new ModelAndView("/account/create");
        modelAndView.addObject("newUser", new AppUser());
        return modelAndView;
    }

    @PostMapping("/create-account")
    public ModelAndView createAppUser( @ModelAttribute("user") AppUser appUser) {
        Instant time=LocalDateTime.ofInstant(Instant.now(), ZoneOffset.UTC).toInstant(ZoneOffset.UTC);
        appUser.setCreated(time);
        appUser.setEnabled(true);
        AppRole appRole = appRoleServiceImplDUNG.getRoleByName("ROLE_USER");
        appUser.setRole(appRole);
        appUserServiceImplDUNG.save(appUser);
        return new ModelAndView("/account/create");
    }

    @GetMapping("/Access_Denied")
    public String accessDeniedPage(ModelMap model) {
        model.addAttribute("user", getPrincipal().getUsername());
        return "accessDenied";
    }

}

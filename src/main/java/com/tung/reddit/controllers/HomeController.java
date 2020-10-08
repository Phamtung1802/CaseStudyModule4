package com.tung.reddit.controllers;

import com.tung.reddit.services.AppRoleService;
import com.tung.reddit.services.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

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


}

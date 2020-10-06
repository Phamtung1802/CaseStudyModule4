package com.example.demo.controllers;

import com.example.demo.models.AppRole;
import com.example.demo.models.AppUser;
import com.example.demo.services.AppRoleService;
import com.example.demo.services.AppUserService;
import org.dom4j.rule.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.awt.*;
import java.io.File;
import java.io.IOException;

@Controller
public class IndexController {
    @Autowired
    private AppRoleService appRoleServiceImpl;

    @Autowired
    private AppUserService appUserServiceImpl;

    @GetMapping("/")
    public ModelAndView index(){
        ModelAndView mov=new ModelAndView("/index");
        return mov;
    }

    @GetMapping(path = "/create")
    public ModelAndView createUser() throws IOException {
        ModelAndView mov=new ModelAndView("/create");
        return mov;
    }

    @PostMapping(path = "/create",consumes = {"application/json", MediaType.APPLICATION_JSON_VALUE},produces = MediaType.APPLICATION_JSON_VALUE)
    public AppUser createUserPost(@RequestBody AppUser appUser) throws IOException {
        System.out.println(appUser);
        AppUser savedUser = appUserServiceImpl.save(appUser);
        return savedUser;
    }

}

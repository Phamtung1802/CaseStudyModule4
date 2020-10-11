package com.tung.reddit.controllers;

import com.tung.reddit.models.AppRole;
import com.tung.reddit.models.AppUser;
import com.tung.reddit.services.AppRoleServiceDUNG;
import com.tung.reddit.services.AppUserServiceDUNG;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Controller
@RequestMapping( "admin")


public class AppAdminControllerDUNG {

    @Autowired
    private AppRoleServiceDUNG appRoleServiceDUNGImpl;

    @Autowired
    private AppUserServiceDUNG appUserServiceDUNGImpl;

    @ModelAttribute("user")
    private AppUser getPrincipal(){
        AppUser appUser = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            appUser = appUserServiceDUNGImpl.getUserByUserName(((UserDetails)principal).getUsername());
        }
        return appUser;
    }

    @GetMapping("/create")
    public ModelAndView showCreate() {
        ModelAndView modelAndView = new ModelAndView("/account/create");
        modelAndView.addObject("user", new AppUser());
        return modelAndView;

    }

    @PostMapping("/create")
    public String createAdmin(AppUser appUser, Model model) {
        Instant time=LocalDateTime.ofInstant(Instant.now(), ZoneOffset.UTC).toInstant(ZoneOffset.UTC);
        appUser.setCreated(time);
        appUser.setEnabled(true);
        AppRole appRole = appRoleServiceDUNGImpl.getRoleByName("ROLE_ADMIN");
        appUser.setRole(appRole);
        appUserServiceDUNGImpl.save(appUser);
        model.addAttribute("user",appUser);
        return "redirect:/";

    }

    @GetMapping("/app-user")
    public ModelAndView listUser() {
        ModelAndView modelAndView = new ModelAndView("/account/list");
        Iterable<AppUser> userList = appUserServiceDUNGImpl.getAllUser();
        modelAndView.addObject("userList", userList);
        return modelAndView;
    }

    @GetMapping("/edit")
    public ModelAndView show() {
        ModelAndView modelAndView = new ModelAndView("/account/edit");
        modelAndView.addObject("user",getPrincipal());
        return modelAndView;
    }

    @PostMapping("/edit")
    public String editUser(AppUser user, Model model) {
        Instant time= LocalDateTime.ofInstant(Instant.now(), ZoneOffset.UTC).toInstant(ZoneOffset.UTC);
        user.setCreated(time);
        AppRole appRole = appRoleServiceDUNGImpl.getRoleByName("ROLE_ADMIN");
        user.setRole(appRole);
        appUserServiceDUNGImpl.save(user);
        model.addAttribute("user", user);
        return "redirect:/admin/app-user";
    }

    @GetMapping("/app-user/edit/{id}")
    public ModelAndView show(@PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView("/account/edit");
        AppUser appUser = appUserServiceDUNGImpl.getUserById(id);
        modelAndView.addObject("user", appUser);
        return modelAndView;
    }

    @GetMapping("/app-user/delete/{id}")
    public ModelAndView deleteUser(@PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView("/account/list");
        appUserServiceDUNGImpl.remove(appUserServiceDUNGImpl.getUserById(id));
        Iterable<AppUser> users = appUserServiceDUNGImpl.getAllUser();
        modelAndView.addObject("userList", users);
        return modelAndView;
    }

}

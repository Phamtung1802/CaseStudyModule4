package com.tung.reddit.controllers.AppUserController;

import com.tung.reddit.models.AppRole;
import com.tung.reddit.models.AppUser;
import com.tung.reddit.services.AppRoleServiceDUNG;
import com.tung.reddit.services.AppUserServiceDUNG;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;

@Controller
@RequestMapping(value = "/admin1")
//@Secured({"ROLE_ADMIN","ROLE_USER","ROLE_PREMIUM_USER"})

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
            appUser = appUserServiceDUNGImpl.getUserByUserName(((UserDetails)principal).getUsername()).orElse(null);
        }
        return appUser;
    }

    @GetMapping("/list")
    public ModelAndView listUser() {
        Iterable<AppUser> appUsers = appUserServiceDUNGImpl.getAllUser();
        ModelAndView modelAndView = new ModelAndView("/account/list");
        modelAndView.addObject("appUser", appUsers);
        return modelAndView;
    }

    @GetMapping("/edit")
    public ModelAndView show() {
        ModelAndView modelAndView = new ModelAndView("account/edit");
        modelAndView.addObject("user",getPrincipal());
        return modelAndView;
    }

    @PostMapping("/edit")
    public String editUser(AppUser appUser, Model model) {
        Instant time= LocalDateTime.ofInstant(Instant.now(), ZoneOffset.UTC).toInstant(ZoneOffset.UTC);
        appUser.setCreated(time);
        AppRole appRole = appRoleServiceDUNGImpl.getRoleByName("ROLE_PREMIUM_USER");
        appUser.setRole(appRole);
        appUserServiceDUNGImpl.save(appUser);
        model.addAttribute("user", appRole);
        return "redirect:/admin1/list";
    }

    @GetMapping("/app-user/edit/{id}")
    public ModelAndView show(@PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView("account/edit");
        AppUser appUser = appUserServiceDUNGImpl.getUserById(id);
        modelAndView.addObject("user", appUser);
        return modelAndView;
    }

    @GetMapping("/app-user/delete/{id}")
    public ModelAndView deleteUser(@PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView("/account/list");
        appUserServiceDUNGImpl.remove(appUserServiceDUNGImpl.getUserById(id));
        Iterable<AppUser> users = appUserServiceDUNGImpl.getAllUser();
        modelAndView.addObject("appUser", users);
        return modelAndView;
    }

}

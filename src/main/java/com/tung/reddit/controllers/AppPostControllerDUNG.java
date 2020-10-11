package com.tung.reddit.controllers;

import com.tung.reddit.models.AppUser;
import com.tung.reddit.services.AppPostServiceDUNG;
import com.tung.reddit.services.AppUserServiceDUNG;
import com.tung.reddit.services.StatusServiceDUNG;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("post")
@CrossOrigin("*")

public class AppPostControllerDUNG {

    @Autowired
    private AppPostServiceDUNG appPostServiceDUNG;

    @Autowired
    private StatusServiceDUNG statusServiceDUNG;

    @Autowired
    private AppUserServiceDUNG appUserServiceDUNG;

    @ModelAttribute("user")
    private AppUser getPrincipal() {
        AppUser appUser = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            appUser = appUserServiceDUNG.getUserByUserName(((UserDetails) principal).getUsername());
        }
        return appUser;
    }


}

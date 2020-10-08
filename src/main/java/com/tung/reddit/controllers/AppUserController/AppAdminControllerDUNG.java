package com.tung.reddit.controllers.AppUserController;

import com.tung.reddit.models.AppUser;
import com.tung.reddit.services.AppRoleService;
import com.tung.reddit.services.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin1")
@Secured({"ROLE_ADMIN","ROLE_USER","ROLE_PREMIUM_USER"})

public class AppAdminControllerDUNG {

    @Autowired
    private AppRoleService appRoleServiceImpl;

    @Autowired
    private AppUserService appUserServiceImpl;

    @ModelAttribute("user")
    private AppUser getPrincipal(){
        AppUser appUser = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            appUser = appUserServiceImpl.findByUsername(((UserDetails)principal).getUsername()).orElse(null);
        }
        return appUser;
    }

    @GetMapping("/list")
    public ModelAndView listUser() {
        Iterable<AppUser> appUsers = appUserServiceImpl.findAll();
        ModelAndView modelAndView = new ModelAndView("/list-user-DUNG");
        modelAndView.addObject("appUser", appUsers);
        return modelAndView;
    }

}

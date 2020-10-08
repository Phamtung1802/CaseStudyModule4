package com.tung.reddit.controllers.AppUserController;

import com.tung.reddit.models.AppRole;
import com.tung.reddit.models.AppUser;
import com.tung.reddit.services.AppRoleServiceDUNG;
import com.tung.reddit.services.AppUserServiceDUNG;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping(value ="/account" )
//@Secured({"ROLE_USER","ROLE_PREMIUM_USER"})
public class AppUserControllerDUNG {

    @Autowired
    private AppUserServiceDUNG appUserServiceImplDUNG;

    @Autowired
    private AppRoleServiceDUNG appRoleServiceImplDUNG;

    @ModelAttribute("user")
    private AppUser getPrincipal(){
        AppUser appUser = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            appUser = appUserServiceImplDUNG.getUserByUserName(((UserDetails)principal).getUsername()).orElse(null);
        }
        return appUser;
    }

    @GetMapping("/edit")
    public ModelAndView show() {
        ModelAndView modelAndView = new ModelAndView("account/edit");
        modelAndView.addObject("user", getPrincipal());
        return modelAndView;
    }

    @PostMapping("/edit")
    public String editAppUser(AppUser appUser, Model model) {
       AppRole appRole = appRoleServiceImplDUNG.getRoleByName("ROLE_PREMIUM_USER");
       appUser.setRole(appRole);
       appUserServiceImplDUNG.save(appUser);
       model.addAttribute("user", appUser);
       return "redirect:/account/list";
    }
}

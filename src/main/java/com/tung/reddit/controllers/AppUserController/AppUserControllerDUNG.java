package com.tung.reddit.controllers.AppUserController;

import com.tung.reddit.models.AppRole;
import com.tung.reddit.models.AppUser;
import com.tung.reddit.services.AppRoleServiceDUNG;
import com.tung.reddit.services.AppUserServiceDUNG;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.*;



@Controller
@RequestMapping("account")
@CrossOrigin("*")
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
            appUser = appUserServiceImplDUNG.getUserByUserName(((UserDetails)principal).getUsername());
        }
        return appUser;
    }

    @GetMapping("/edit")
    public String show() {
        return "/account/edit";
    }

    @PostMapping("/edit")
   public String editUser(@ModelAttribute("user") AppUser user) {
        appUserServiceImplDUNG.save(user);
        return "/account/edit";
    }

    @GetMapping("/password")
    public String showPass() {
        return "/account/password";
    }

    @PostMapping("/password")
    public String passwordForm(@RequestParam("newPass") String newPass) {
        AppUser appUser = getPrincipal();
        if (appUser != null) {
            appUser.setPassword(newPass);
            appUserServiceImplDUNG.save(appUser);
        }
        return "/account/password";
    }

}

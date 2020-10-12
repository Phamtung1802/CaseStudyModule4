package com.tung.reddit.controllers.AppUserController;

import com.tung.reddit.services.AppRoleService;
import com.tung.reddit.services.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(path = "/admin")
@Secured({"ROLE_ADMIN"})
public class AppAdminController {
    @Autowired
    private AppRoleService appRoleServiceImpl;

    @Autowired
    private AppUserService appUserServiceImpl;

    @GetMapping("/hello")
    public ModelAndView index(){
        return new ModelAndView("/index");
    }

}

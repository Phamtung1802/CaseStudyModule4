package com.tung.reddit.controllers.AppUserController;

import com.tung.reddit.services.AppRoleService;
import com.tung.reddit.services.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(path = "/user")
@Secured({"ROLE_USER","ROLE_PREMIUM_USER"})
public class AppUserController {
    @Autowired
    private AppRoleService appRoleServiceImpl;

    @Autowired
    private AppUserService appUserServiceImpl;


}

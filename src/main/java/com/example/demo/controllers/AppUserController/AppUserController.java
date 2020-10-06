package com.example.demo.controllers.AppUserController;

import com.example.demo.models.AppRole;
import com.example.demo.models.AppUser;
import com.example.demo.services.AppRoleService;
import com.example.demo.services.AppUserService;
import net.minidev.json.parser.JSONParser;
import org.dom4j.rule.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.awt.*;
import java.io.File;
import java.io.IOException;

@Controller
@RequestMapping(path = "/user")
@Secured({"ROLE_USER","ROLE_PREMIUM_USER","ROLE_ADMIN","ROLE_MODERATOR"})
public class AppUserController {
    @Autowired
    private AppRoleService appRoleServiceImpl;

    @Autowired
    private AppUserService appUserServiceImpl;

}

package com.tung.reddit.controllers;

import com.tung.reddit.models.AppPost;
import com.tung.reddit.models.AppRole;
import com.tung.reddit.models.AppUser;
import com.tung.reddit.services.AppPostServiceImpl;
import com.tung.reddit.services.AppRoleService;
import com.tung.reddit.services.AppUserService;
import org.dom4j.rule.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.management.relation.Role;
import javax.swing.text.html.HTML;
import javax.swing.text.html.HTMLDocument;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Controller
public class IndexController {
    @Autowired
    private AppRoleService appRoleServiceImpl;

    @Autowired
    private AppUserService appUserServiceImpl;

    @Autowired
    private AppPostServiceImpl appPostServiceImpl;


    @GetMapping("/")
    public ModelAndView index(){
        ModelAndView mov=new ModelAndView("index");
        Iterable<AppPost> appPosts=appPostServiceImpl.findAll();
        mov.addObject("appPosts",appPosts);
        return mov;
    }

    @GetMapping(path = "/create")
    public ModelAndView createUser() throws IOException {
        ModelAndView mov=new ModelAndView("Fragment :: createUser");
        return mov;
    }

    @GetMapping(path = "/login")
    public ModelAndView loginRedirect() throws IOException {
        ModelAndView mov=new ModelAndView("/index");
        return mov;
    }

    @GetMapping(path = "/forum")
    public ModelAndView ShowForum() throws IOException {
        ModelAndView mov=new ModelAndView("Fragment :: posts");
        Iterable<AppPost> appPosts=appPostServiceImpl.findAll();
        mov.addObject("appPosts",appPosts);
        return mov;
    }

    @PostMapping(path = "/search",consumes = "text/plain")
    public ModelAndView search(@RequestBody String string) throws IOException {
        ModelAndView mov=new ModelAndView("Fragment :: posts");
        Iterable<AppPost> appPosts=appPostServiceImpl.findAllByPostNameContains(string);
        mov.addObject("appPosts",appPosts);
        System.out.println("post");
        return mov;
    }


    @PostMapping(path = "/create",consumes = {"application/json", MediaType.APPLICATION_JSON_VALUE},produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AppUser> createUserPost(@RequestBody AppUser appUser) throws IOException {
        Instant time=LocalDateTime.ofInstant(Instant.now(), ZoneOffset.UTC).toInstant(ZoneOffset.UTC);
        appUser.setCreated(time);
        appUser.setEnabled(true);
        AppRole role= new AppRole();
        role.setId(3L);
        appUser.setRole(role);
        AppUser savedUser = appUserServiceImpl.save(appUser);
        ResponseEntity<AppUser> response= new ResponseEntity<AppUser>(savedUser,HttpStatus.OK);
        return response;
    }

}

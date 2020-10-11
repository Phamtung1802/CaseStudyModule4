//package com.tung.reddit.controllers;
//
//import com.tung.reddit.models.AppRole;
//import com.tung.reddit.models.AppUser;
//import com.tung.reddit.services.AppRoleService;
//import com.tung.reddit.services.AppUserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.servlet.ModelAndView;
//
//import java.io.IOException;
//import java.time.Instant;
//import java.time.LocalDateTime;
//import java.time.ZoneOffset;
//
//
//@Controller
//public class IndexController {
//    @Autowired
//    private AppRoleService appRoleServiceImpl;
//
//    @Autowired
//    private AppUserService appUserServiceImpl;
//
//    @GetMapping("/")
//    public ModelAndView index(){
//        ModelAndView mov=new ModelAndView("/index");
//        return mov;
//    }
//
//    @GetMapping(path = "/create")
//    public ModelAndView createUser() throws IOException {
//        ModelAndView mov=new ModelAndView("/create");
//        return mov;
//    }
//
//    @GetMapping(path = "/login")
//    public ModelAndView loginRedirect() throws IOException {
//        ModelAndView mov=new ModelAndView("/index");
//        return mov;
//    }
//
//    @PostMapping(path = "/create",consumes = {"application/json",
//            MediaType.APPLICATION_JSON_VALUE},
//            produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<AppUser> createUserPost(@RequestBody AppUser appUser) throws IOException {
//        Instant time=LocalDateTime.ofInstant(Instant.now(), ZoneOffset.UTC).toInstant(ZoneOffset.UTC);
//        appUser.setCreated(time);
//        appUser.setEnabled(true);
//        AppRole role= new AppRole();
//        role.setId(3L);
//        appUser.setRole(role);
//        AppUser savedUser = appUserServiceImpl.save(appUser);
//        ResponseEntity<AppUser> response= new ResponseEntity<AppUser>(savedUser,HttpStatus.OK);
//        return response;
//    }
//
//}
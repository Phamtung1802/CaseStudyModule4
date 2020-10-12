package com.tung.reddit.controllers;

import com.tung.reddit.models.AppComment;
import com.tung.reddit.models.AppPost;
import com.tung.reddit.models.AppRole;
import com.tung.reddit.models.AppUser;
import com.tung.reddit.services.*;
import org.dom4j.rule.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.trace.http.HttpTrace;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import repositories.AppCommentRepository;

import javax.management.relation.Role;
import javax.swing.text.html.HTML;
import javax.swing.text.html.HTMLDocument;
import java.io.IOException;
import java.security.Principal;
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

    @Autowired
    private AppCommentService appCommentServiceImpl;


    @GetMapping("/")
    public ModelAndView index(){
        ModelAndView mov=new ModelAndView("index");
        Iterable<AppPost> appPosts=appPostServiceImpl.findAll();
        mov.addObject("appPosts",appPosts);
        return mov;
    }
    @PostMapping("/")
    public ModelAndView indexPostMethod(){
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
        ModelAndView mov=new ModelAndView("redirect:/");
        return mov;
    }

    @GetMapping(path = "/forum")
    public ModelAndView ShowForum() throws IOException {
        ModelAndView mov=new ModelAndView("Fragment :: posts");
        Iterable<AppPost> appPosts=appPostServiceImpl.findAll();
        mov.addObject("appPosts",appPosts);
        return mov;
    }

    @PostMapping(path = "/search")
    public ModelAndView search(@RequestBody AppPost appPost) throws IOException {
        ModelAndView mov=new ModelAndView("Fragment :: posts");
        Iterable<AppPost> appPosts=appPostServiceImpl.findAllByPostNameContains(appPost.getPostName());
        mov.addObject("appPosts",appPosts);
        return mov;
    }

    @GetMapping(path="/upgrade")
    public ModelAndView search(Principal principal) throws IOException {
        ModelAndView mov=new ModelAndView("redirect:/");
        AppUser appUser= appUserServiceImpl.findFirstByUsername(principal.getName());
        AppRole role=new AppRole();
        role.setId(4L);
        appUser.setRole(role);
        appUserServiceImpl.save(appUser);
        return mov;
    }


    @GetMapping(path = "**/changePassword")
    @Secured({"ROLE_USER","ROLE_PREMIUM_USER","ROLE_ADMIN","ROLE_MODERATOR"})
    public ModelAndView changePassword() throws IOException {
        ModelAndView mov=new ModelAndView("Fragment :: changePassword");
        return mov;
    }

    @PatchMapping(path = "**/changePassword",consumes = {"multipart/form-data"})
    @Secured({"ROLE_USER","ROLE_PREMIUM_USER","ROLE_ADMIN","ROLE_MODERATOR"})
    public ModelAndView changePasswordSubmit(Principal principal,@RequestPart("oldPassword") String oldPassword,@RequestPart("newPassword") String newPassword) throws IOException {
        ModelAndView mov= new ModelAndView("/index");
        AppUser appUser= appUserServiceImpl.findFirstByUsername(principal.getName());
        String password=appUser.getPassword();
        System.out.println(oldPassword);
        if (password.equals(oldPassword)){
            appUser.setPassword(newPassword);
            appUserServiceImpl.save(appUser);
            mov.setViewName ("Fragment :: changePasswordSuccess");
        }else {
            mov.setViewName("Fragment :: wrongPassword");
        }
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
        System.out.println("created");
        return response;
    }
    @GetMapping(path = "/post/{id}")
    public ModelAndView search(@PathVariable long id) throws IOException {
        ModelAndView mov=new ModelAndView("PostDetails");
        AppPost post=appPostServiceImpl.findByPostID(id);
        Iterable<AppComment> comments=appCommentServiceImpl.findAllByPost(post);
        mov.addObject("post",post);
        mov.addObject("comments",comments);
        return mov;
    }

    @PostMapping(path = "/post/{id}")
    @Secured({"ROLE_USER","ROLE_PREMIUM_USER","ROLE_ADMIN","ROLE_MODERATOR"})
    public ModelAndView postComment(@PathVariable long id, @RequestBody AppComment appComment, Principal principal) throws IOException {
        ModelAndView mov=new ModelAndView("redirect:/post/"+id);
        Instant time=LocalDateTime.ofInstant(Instant.now(), ZoneOffset.UTC).toInstant(ZoneOffset.UTC);
        AppUser appUser=appUserServiceImpl.findFirstByUsername(principal.getName());
        AppPost post=appPostServiceImpl.findByPostID(id);
        appComment.setPost(post);
        appComment.setUser(appUser);
        appComment.setCreatedDate(time);
        appCommentServiceImpl.save(appComment);
        Iterable<AppComment> comments=appCommentServiceImpl.findAllByPost(post);
        mov.addObject("post",post);
        mov.addObject("comments",comments);
        return mov;
    }

    @GetMapping(path = "/delete_post/{id}")
    @Secured({"ROLE_ADMIN","ROLE_MODERATOR"})
    @Transactional
    public ModelAndView deletePost(@PathVariable long id) throws IOException {
        ModelAndView mov=new ModelAndView("redirect:/");
        appPostServiceImpl.deleteAppPostByPostId(id);
        return mov;
    }
}

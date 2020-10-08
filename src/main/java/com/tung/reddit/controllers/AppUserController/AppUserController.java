package com.tung.reddit.controllers.AppUserController;

import com.tung.reddit.models.AppPost;
import com.tung.reddit.models.AppUser;
import com.tung.reddit.services.AppPostServiceImpl;
import com.tung.reddit.services.AppRoleService;
import com.tung.reddit.services.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.*;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotBlank;
import java.net.http.HttpRequest;
import java.security.Principal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

@Controller
@RequestMapping(path = "/user")
@Secured({"ROLE_USER","ROLE_PREMIUM_USER","ROLE_ADMIN"})
public class AppUserController {
    @Autowired
    private AppRoleService appRoleServiceImpl;

    @Autowired
    private AppPostServiceImpl appPostServiceImpl;


    @Autowired
    private AppUserService appUserServiceImpl;

    @GetMapping("/{id}")
    public ModelAndView index(@PathVariable String id){
        return new ModelAndView("/index");
    }

    @GetMapping("/createPost")
    public ModelAndView createPost(HttpServletResponse response){
        ModelAndView mov=new ModelAndView("Fragment :: createPost");
        return mov;
    }

    @PostMapping("/createPost")
    public ModelAndView createNewPost(@RequestBody AppPost appPost, Principal principal){
        AppUser user= appUserServiceImpl.findFirstByUsername(principal.getName());
        Instant time= LocalDateTime.ofInstant(Instant.now(), ZoneOffset.UTC).toInstant(ZoneOffset.UTC);
        appPost.setCreatedDate(time);
        appPost.setUser(user);
        AppPost savedPost=appPostServiceImpl.save(appPost);
        appPost.setUrl("/post/"+savedPost.getPostId());
        appPostServiceImpl.save(appPost);
        System.out.println("saving new Post");
        return new ModelAndView("redirect:/");
    }


}

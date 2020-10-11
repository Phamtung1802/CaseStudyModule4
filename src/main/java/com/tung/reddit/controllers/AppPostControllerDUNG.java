package com.tung.reddit.controllers;

import com.tung.reddit.models.AppComment;
import com.tung.reddit.models.AppPost;
import com.tung.reddit.models.AppUser;
import com.tung.reddit.models.AppVote;
import com.tung.reddit.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;
import java.util.Date;

@Controller
@RequestMapping("post")
@CrossOrigin("*")

public class AppPostControllerDUNG {

    @Autowired
    private AppCommentServiceDUNG appCommentServiceDUNG;

    @Autowired
    private LikeService likeService;

    @Autowired
    private VoteService voteService;

    @Autowired
    private AppPostServiceDUNG appPostServiceDUNG;

    @Autowired
    private StatusServiceDUNG statusServiceDUNG;

    @Autowired
    private AppUserServiceDUNG appUserServiceDUNG;

    @Value("${upload.path}")
    private String upload_path;

    @ModelAttribute("user")
    private AppUser getPrincipal() {
        AppUser appUser = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            appUser = appUserServiceDUNG.getUserByUserName(((UserDetails) principal).getUsername());
        }
        return appUser;
    }

    private Date getCurrentDate() {
        return new Date(System.currentTimeMillis());
    }

    @PostMapping("/create")
    public ResponseEntity<String> createPost(AppPost appPost) {
        appPost.setDateUpload(getCurrentDate());
        MultipartFile photo = appPost.getPhoto();
        String photoName = "post_" + photo.getOriginalFilename();
        appPost.setPhotoName(photoName);
        appPost.setStatus(statusServiceDUNG.findByName("pending").get());
        appPost.setAppUser(getPrincipal());
        try {
            FileCopyUtils.copy(photo.getBytes(), new File(upload_path + photoName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        appPostServiceDUNG.save(appPost);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/edit")
    public ModelAndView showEditForm() {
        ModelAndView modelAndView = new ModelAndView("post/edit");
        modelAndView.addObject("post", new AppPost());
        return modelAndView;
    }

    @PostMapping("/edit")
    public ModelAndView edit(AppPost appPost) {
        ModelAndView modelAndView = new ModelAndView("post/edit");
        MultipartFile photo = appPost.getPhoto();
        appPost.setPhotoName(photo.getOriginalFilename());
        appPostServiceDUNG.save(appPost);
        return modelAndView;
    }

    @GetMapping("/detail/{id}")
    public ModelAndView showPostDetail(@PathVariable("id") Long id,
                                       @PageableDefault(value = 5, page = 0)
                                       @SortDefault(sort = "timeComment", direction = Sort.Direction.DESC)
                                               Pageable pageable) {
        AppPost currentPost= appPostServiceDUNG.getPostById(id);
        Page <AppComment> commentPage = appCommentServiceDUNG.getAllCommentByPost(currentPost, pageable);
        ModelAndView modelAndView = new ModelAndView("/post/detail");
        modelAndView.addObject("post", currentPost);
        modelAndView.addObject("commentPage", commentPage);
        modelAndView.addObject("newComment", new AppComment());
        modelAndView.addObject("currentTime", System.currentTimeMillis());
        return modelAndView;
    }

    @PostMapping("/detail/{id}")
    public ResponseEntity<AppComment> saveComment(@RequestBody AppComment comment,
                                                  @PathVariable("id") Long id) {
        comment.setTimeComment(getCurrentDate());
        appCommentServiceDUNG.save(comment);
        AppPost appPost = appPostServiceDUNG.getPostById(id);
        appPost.setCommentCount(appCommentServiceDUNG.countAllByPost(appPost));
        appPostServiceDUNG.save(appPost);
        return new ResponseEntity<>(comment, HttpStatus.OK);
    }

    @PutMapping("/detail/{id}")
    public ResponseEntity<AppComment> editComment(@RequestBody AppComment comment,
                                                  @PathVariable("id") Long id) {
        AppComment currentComment = appCommentServiceDUNG.getCommentById(comment.getId());
        currentComment.setContent(comment.getContent());
        appCommentServiceDUNG.save(currentComment);
        AppPost appPost = appPostServiceDUNG.getPostById(id);
        appPost.setCommentCount(appCommentServiceDUNG.countAllByPost(appPost));
        appPostServiceDUNG.save(appPost);
        return new ResponseEntity<>(currentComment,HttpStatus.OK);
    }

    @DeleteMapping("/detail/{id}")
    public ResponseEntity<AppComment> removeComment(@RequestBody AppComment comment,
                                                    @PathVariable("id") Long id) {
        AppComment currentComment = appCommentServiceDUNG.getCommentById(comment.getId());
        appCommentServiceDUNG.remove(currentComment);

        AppPost appPost = appPostServiceDUNG.getPostById(id);
        appPost.setCommentCount(appCommentServiceDUNG.countAllByPost(appPost));

        appPostServiceDUNG.save(appPost);
        return new ResponseEntity(appPost,HttpStatus.OK);
    }

    @PostMapping("/upVote")
    public ResponseEntity<AppPost> votePost(@RequestBody AppVote appVote) {
        AppPost currentPost = appPostServiceDUNG.getPostById(appVote.getPost().getId());
        AppUser currentUser = getPrincipal();
        if (currentUser != null && !voteService.existsByAppUserAndAndPost(currentUser,currentPost)) {
            appVote.setValue(1L);
            voteService.save(appVote);
        } else {
            AppVote currentVote = voteService.getByAppUserAndAndPost(currentUser, currentPost);
            if (currentVote.getValue() == -1L ) {
                currentVote.setValue(1L);
                voteService.save(currentVote);
            } else {
                voteService.remove(currentVote);
            }
        }
        Long countVote = voteService.sumOfValues(currentPost);
        System.out.println("upVote_countVote = " + countVote);
        if (countVote == null ) {
            currentPost.setVoteCount(0);
        } else  {
            currentPost.setVoteCount(countVote);
        }
        appPostServiceDUNG.save(currentPost);
        return new ResponseEntity<>(currentPost,HttpStatus.OK);
    }

    @PostMapping("/downVote")
    public ResponseEntity<AppPost> downVotePost(@RequestBody AppVote vote) {
        AppPost currentPost = appPostServiceDUNG.getPostById(vote.getPost().getId());
        AppUser currentUser = getPrincipal();
        if (currentUser != null && !voteService.existsByAppUserAndAndPost(currentUser,currentPost)) {
        vote.setValue(-1L);
        voteService.save(vote);
    }else {
            AppVote currentVote = voteService.getByAppUserAndAndPost(currentUser, currentPost);
            if (currentVote.getValue() == 1L) {
                currentVote.setValue(-1L);
                voteService.save(currentVote);
            } else {
                voteService.remove(currentVote);
            }
        }
        Long countVote = voteService.sumOfValues(currentPost);
        if (countVote == null) {
            currentPost.setVoteCount(0);
        } else {
            currentPost.setVoteCount(countVote);
        }
        System.out.println("downVote_countVote = " + countVote);
        appPostServiceDUNG.save(currentPost);
        return new ResponseEntity<>(currentPost, HttpStatus.OK);
    }

}

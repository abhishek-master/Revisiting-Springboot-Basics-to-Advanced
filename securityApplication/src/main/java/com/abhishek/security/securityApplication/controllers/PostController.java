package com.abhishek.security.securityApplication.controllers;

import com.abhishek.security.securityApplication.dto.PostDTO;
import com.abhishek.security.securityApplication.entities.User;
import com.abhishek.security.securityApplication.services.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(path = "/posts")
@RequiredArgsConstructor
@Slf4j
public class PostController {

    private final PostService postService;

    @GetMapping
    @Secured("ROLE_ADMIN")
    public List<PostDTO> getAllPosts() {
        return postService.getAllPosts();
    }

    @GetMapping("/{postId}")
    @PreAuthorize("@postSecurity.isOwnerOfPost(#postId)") //How to use a specific bean method to secure the endpoint
    public PostDTO getPostById(@PathVariable Long postId) {
        //We can now get the user from the Security Context and filter out posts based on that.
        User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        log.info("Posts are of the User : {} ", user);
        return postService.getPostById(postId); //We can do getPostByUsers and use relation between User and Posts to get just the posts by the logged in user
    }

    @PostMapping
    public PostDTO createNewPost(@RequestBody PostDTO inputPost) {
        return postService.createNewPost(inputPost);
    }
}

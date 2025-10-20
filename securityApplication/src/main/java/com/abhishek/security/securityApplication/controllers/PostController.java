package com.abhishek.security.securityApplication.controllers;

import com.abhishek.security.securityApplication.dto.PostDTO;
import com.abhishek.security.securityApplication.entities.User;
import com.abhishek.security.securityApplication.services.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
    public List<PostDTO> getAllPosts() {
        return postService.getAllPosts();
    }

    @GetMapping("/{postId}")
    public PostDTO getPostById(@PathVariable Long postId) {
        //We can now get the user from the Security Context and filter out posts based on that.
        User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        log.info("Posts are of the User : {} ", user);
        return postService.getPostById(postId);
    }

    @PostMapping
    public PostDTO createNewPost(@RequestBody PostDTO inputPost) {
        return postService.createNewPost(inputPost);
    }
}

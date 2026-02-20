package com.abhishek.security.securityApplication.services;

import com.abhishek.security.securityApplication.dto.PostDTO;
import com.abhishek.security.securityApplication.entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostSecurity {

    private final PostService postService ;

    public boolean isOwnerOfPost(Long postId){
        PostDTO post = postService.getPostById(postId);
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal() ;
        return post.getAuthor().getId().equals(user.getId());
    }
}

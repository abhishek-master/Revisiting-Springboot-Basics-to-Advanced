package com.abhishek.security.securityApplication.dto;

import com.abhishek.security.securityApplication.entities.User;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDTO {
    private Long id;
    private String title;
    private String description;

    private User author ;
}

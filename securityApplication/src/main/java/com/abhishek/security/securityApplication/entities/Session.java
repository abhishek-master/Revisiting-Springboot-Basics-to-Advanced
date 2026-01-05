package com.abhishek.security.securityApplication.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class Session {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;

    private String refreshToken ;

    @CreationTimestamp
    private LocalDateTime lastUsedAt;

    @ManyToOne
    private User user;

}


/*
How refreshToken is used to maintain/limit the sessions
1. Generate AT + RT and store the Session using this schema
(session_id, refreshToken, userId, lastUsedAt)
2. Renew AT using RT, iff RT is not expired AND the session is present.
3. Upon a New Login request, check if the session limit is full.
if full -> remove the least recently used session
else -> Follow step 1
* */

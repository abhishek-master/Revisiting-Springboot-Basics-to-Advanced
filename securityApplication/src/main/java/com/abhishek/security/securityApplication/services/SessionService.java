package com.abhishek.security.securityApplication.services;

import com.abhishek.security.securityApplication.entities.Session;
import com.abhishek.security.securityApplication.entities.User;
import com.abhishek.security.securityApplication.repositories.SessionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SessionService {

    private final SessionRepository sessionRepository ;
    private final int SESSION_LIMIT = 2;

    public void generateNewSession (User user, String refreshToken)     {
        List<Session> userSessions = sessionRepository.findByUser(user);
        if(userSessions.size() == 2){
                userSessions.sort(Comparator.comparing(Session::getLastUsedAt));
                Session leastRecentlyUsedSession = userSessions.get(0);
                sessionRepository.delete(leastRecentlyUsedSession);
        }
        Session newSession = Session.builder().
                refreshToken(refreshToken).
                user(user).
                build();
        sessionRepository.save(newSession);
    }

    public void validateSession(String refreshToken){
        Session session = sessionRepository.findByRefreshToken(refreshToken)
                .orElseThrow(() -> new SessionAuthenticationException("Session Not Found for refresh token : " + refreshToken));
        session.setLastUsedAt(LocalDateTime.now());
        sessionRepository.save(session);
    }

    public void removeSessionOnLogout(String refreshToken){
        Session currentSession = sessionRepository.findByRefreshToken(refreshToken).orElseThrow(() -> new SessionAuthenticationException("No related Refresh Token Found !"));
        sessionRepository.delete(currentSession);
    }
}

package com.marufeb.data.security;

import com.marufeb.data.users.User;
import com.marufeb.data.users.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Slf4j
@Component
@RequiredArgsConstructor
public class SuccessfulAuthenticationHandler implements AuthenticationSuccessHandler {
    private final UserService userService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        if (authentication.getPrincipal() instanceof UserDetails principal) {
            userService.findByEmail(principal.getUsername()).ifPresentOrElse(
                    user -> {
                        user.setLastLogin(LocalDateTime.now());
                        userService.save(user);
                    }, () -> userService.save(new User(principal.getUsername()))
            );
        }
    }

}

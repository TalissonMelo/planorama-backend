package com.liberbox.config.domain;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.OncePerRequestFilter;

import lombok.NonNull;

public class UserFilter extends OncePerRequestFilter {

    private static final String USER_ID_HEADER = "X-UserID";

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {

        String userIdHeader = request.getHeader(USER_ID_HEADER);

        if (userIdHeader != null && !userIdHeader.isEmpty()) {
            UserContext.setCurrentUser(userIdHeader);
        }

        try {
            filterChain.doFilter(request, response);

        } finally {
            UserContext.clear();
        }
    }

}

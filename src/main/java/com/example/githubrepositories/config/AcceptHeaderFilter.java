package com.example.githubrepositories.config;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;

import java.io.IOException;

public class AcceptHeaderFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String acceptHeader = req.getHeader("Accept");

        if (acceptHeader == null || !acceptHeader.contains("application/json")) {
            res.setStatus(HttpStatus.NOT_ACCEPTABLE.value());
            res.setContentType("application/json");
            res.getWriter().write("{\"status\": 406, \"Message\": \"The provided Accept header is not supported.\"}");
            return;
        }

        chain.doFilter(request, response);
    }
}

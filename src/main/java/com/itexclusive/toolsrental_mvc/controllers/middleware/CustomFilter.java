package com.itexclusive.toolsrental_mvc.controllers.middleware;

import jakarta.servlet.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Order(100)
public class CustomFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        servletRequest.setAttribute("isAdminTEST", "false");
        filterChain.doFilter(servletRequest, servletResponse);
    }
}

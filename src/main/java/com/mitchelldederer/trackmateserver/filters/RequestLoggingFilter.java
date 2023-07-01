package com.mitchelldederer.trackmateserver.filters;

import jakarta.servlet.*;

import java.io.IOException;

/**
 * This is mostly just here as a reference for future filters.
 */
public class RequestLoggingFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("Recieving request");
        filterChain.doFilter(servletRequest, servletResponse);
        System.out.println("Sending request");
    }

}

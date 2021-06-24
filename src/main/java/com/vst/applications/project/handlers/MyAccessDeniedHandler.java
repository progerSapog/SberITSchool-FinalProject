package com.vst.applications.project.handlers;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Собственный перехватичк ошибок доступа.
 * С его помощью при получении ошибки доступа мы может передать собственную страницу,
 * избежав error 403 forbidden
 *
 * @see AccessDeniedHandler
 * */
public class MyAccessDeniedHandler implements AccessDeniedHandler
{
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException e) throws IOException
    {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        response.sendRedirect(request.getContextPath() + "/accessDenied");
    }
}

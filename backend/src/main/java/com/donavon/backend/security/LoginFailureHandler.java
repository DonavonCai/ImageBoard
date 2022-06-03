package com.donavon.backend.security;

import org.springframework.stereotype.Component;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

@Component
public class LoginFailureHandler implements AuthenticationFailureHandler {
  @Override
  public void onAuthenticationFailure(
    HttpServletRequest request,
    HttpServletResponse response,
    AuthenticationException exception
  ) throws IOException, ServletException {
    String username = request.getParameter("username");
    String error = exception.getMessage();
    System.out.println("A failed login attempt with username: " + username + ". Reason: " + error);

    // String redirectUrl = request.getContextPath() + "/login?error";
    // response.sendRedirect(redirectUrl);
    String message = "Login failed.";
    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    response.getWriter().write(message);
    response.getWriter().flush();
  }
}

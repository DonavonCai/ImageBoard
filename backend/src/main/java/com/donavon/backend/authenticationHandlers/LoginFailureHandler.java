package com.donavon.backend.authenticationHandlers;

import org.springframework.stereotype.Component;

import com.donavon.backend.model.User;
import com.donavon.backend.services.UserService;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

@Component
public class LoginFailureHandler implements AuthenticationFailureHandler {
  @Autowired
  UserService userService;

  @Override
  public void onAuthenticationFailure(
    HttpServletRequest request,
    HttpServletResponse response,
    AuthenticationException exception
  ) throws IOException, ServletException {
    String username = request.getParameter("username");
    String error = exception.getMessage();
    System.out.println("A failed login attempt with username: " + username + ". Reason: " + error);

    // TODO: implement account unlocking before uncommenting this
    // userService.processFailedAttempt(username);

    String message = "Login failed.";
    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    response.getWriter().write(message);
    response.getWriter().flush();
  }
}

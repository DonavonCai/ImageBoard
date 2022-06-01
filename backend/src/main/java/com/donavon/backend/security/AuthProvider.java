package com.donavon.backend.security;

import java.util.Optional;

import com.donavon.backend.model.Attempts;
import com.donavon.backend.model.User;
import com.donavon.backend.repository.AttemptsRepository;
import com.donavon.backend.repository.UserRepository;
import com.donavon.backend.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component public class AuthProvider implements AuthenticationProvider {
  private static final int ATTEMPTS_LIMIT = 5;

  @Autowired
  private UserService userService;
  @Autowired
  private PasswordEncoder passwordEncoder;
  @Autowired
  private AttemptsRepository attemptsRepo;
  @Autowired
  private UserRepository userRepo;

  @Override
  public Authentication authenticate(Authentication auth) throws AuthenticationException {
    if (auth.getPrincipal() == "" || auth.getCredentials() == "") {
      throw new BadCredentialsException("Missing fields in authorization!");
    }

    if (auth.getPrincipal() == null || auth.getCredentials() == null) {
      throw new InternalAuthenticationServiceException("Principal or credentials are null!");
    }

    String username = (String)auth.getPrincipal();
    User user = (User)userService.loadUserByUsername(username);
    String formPassword = auth.getCredentials().toString();
    String hashedPassword = user.getPassword();

    boolean matches = passwordEncoder.matches(formPassword, hashedPassword);
    if (!matches) {
      processFailedAttempt(user);
      throw new BadCredentialsException("Passwords do not match.");
    }
    return new UsernamePasswordAuthenticationToken(username, user.getPassword());
  }

  private void processFailedAttempt(User user) throws AuthenticationException {
    String username = user.getUsername();
    Optional<Attempts> userAttempts = attemptsRepo.findByUsername(username);

    Attempts attempts;
    if (userAttempts.isPresent()) {
      attempts = userAttempts.get();
      attempts.setAttempts(attempts.getAttempts() + 1);
      attemptsRepo.save(attempts);

      if (attempts.getAttempts() >= ATTEMPTS_LIMIT) {
        user.setAccountNonLocked(false);
        userRepo.save(user);
        throw new LockedException("Too many invalid attempts. Account is locked!");
      }
    }
    else {
      attempts = new Attempts(username, 1);
      attemptsRepo.save(attempts);
    }
  }

  @Override
  public boolean supports(Class<?> authentication) {
    return authentication.equals(UsernamePasswordAuthenticationToken.class);
  }
}

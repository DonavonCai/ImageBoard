package com.donavon.backend.security;

import java.util.Optional;

import com.donavon.backend.model.Attempts;
import com.donavon.backend.model.User;
import com.donavon.backend.repository.AttemptsRepository;
import com.donavon.backend.repository.UserRepository;
import com.donavon.backend.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
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
    String username = auth.getName();
    Optional<Attempts> userAttempts = attemptsRepo.findByUsername(username);
    User user = (User)userService.loadUserByUsername(username);

    // Process attempts
    Attempts attempts = null;
    if (userAttempts.isEmpty()) {
      attempts = new Attempts(username, 1);
      attemptsRepo.save(attempts);
    }
    else {
      attempts = userAttempts.get();
      attempts.setAttempts(attempts.getAttempts() + 1);
      attemptsRepo.save(attempts);

      if (attempts.getAttempts() >= ATTEMPTS_LIMIT) {
        user.setAccountNonLocked(false);
        userRepo.save(user);
        throw new LockedException("Too many invalid attempts. Account is locked!!");
      }
    }

    // TODO: match passwords using PasswordEncoder matches()
    passwordEncoder.matches(auth.getCredentials().toString(), user.getPassword());
    return new UsernamePasswordAuthenticationToken(username, user.getPassword());
  }

  @Override
  public boolean supports(Class<?> authentication) {
    return true;
  }
}

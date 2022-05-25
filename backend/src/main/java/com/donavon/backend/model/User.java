package com.donavon.backend.model;

import java.util.Collection;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;

@Data
@Document("users")
public class User implements UserDetails {
  @Id
  private String id;
  @Indexed(unique = true)
  private String username;
  @Indexed(unique = true)
  private String email;
  private String password;
  private boolean accountNonLocked;

  public User(String username, String email, String password, boolean accountNonLocked) {
    this.username = username;
    this.email = email;
    this.password = password;
    this.accountNonLocked = accountNonLocked;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return List.of(() -> "read");
  }

  @Override
  public boolean isAccountNonLocked() {
    return this.accountNonLocked;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}

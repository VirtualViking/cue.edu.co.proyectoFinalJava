package com.example.velocerentalsspring.infrastructure.rest.security;

import com.example.velocerentalsspring.domain.models.User;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@AllArgsConstructor
public class SecurityUser implements UserDetails {
  private final User user;
  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return List.of(new GrantedAuthority() {
      @Override
      public String getAuthority() {
        return "ROLE_USER";
      }
    });
  }

  @Override
  public String getPassword() {
    return user.getPassword();
  }

  @Override
  public String getUsername() {
    return user.getEmail();
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
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

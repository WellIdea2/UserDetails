package com.floxie.user_details.infrastructure.config.security;

import org.commons.feature.user.dto.UserView;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class CustomAuthenticationToken extends AbstractAuthenticationToken {

  private final UserView principal;

  public CustomAuthenticationToken(
      UserView principal, Collection<? extends GrantedAuthority> authorities) {
    super(authorities);
    this.principal = principal;
    setAuthenticated(true);
  }

  @Override
  public Object getCredentials() {
    return null;
  }

  @Override
  public UserView getPrincipal() {
    return principal;
  }
}

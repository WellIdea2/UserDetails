package com.floxie.user_details.infrastructure.config.security;

import com.floxie.user_details.infrastructure.exceptions.ExceptionMessages;
import org.commons.exceptions.throwable.ForbiddenException;
import org.commons.feature.user.dto.UserView;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtils {

  private SecurityUtils() {
  }

  public static UserView getCurrentLoggedInUser() {
    if (SecurityContextHolder.getContext()
        .getAuthentication() instanceof CustomAuthenticationToken authentication) {
      return authentication.getPrincipal();
    }
    throw new ForbiddenException(ExceptionMessages.INVALID_USER_TOKEN);
  }
}
package com.floxie.user_details.infrastructure.config.security.evaluator;

import com.floxie.user_details.features.user_details.services.UserDetailsService;
import com.floxie.user_details.infrastructure.config.security.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class UserDetailsEvaluator {

  private final UserDetailsService service;

  public boolean isOwner(UUID userDetailsId) {
    var currentLoggedInUser = SecurityUtils.getCurrentLoggedInUser();

    return service.existsByIdAndUserId(userDetailsId, currentLoggedInUser.id());
  }
}

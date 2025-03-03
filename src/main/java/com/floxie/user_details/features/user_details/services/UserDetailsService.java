package com.floxie.user_details.features.user_details.services;

import com.floxie.user_details.features.user_details.dto.UserDetailsCreateRequest;
import com.floxie.user_details.features.user_details.dto.UserDetailsEditRequest;
import org.commons.feature.user_details.dto.UserDetailsView;

import java.util.UUID;

public interface UserDetailsService {

  UserDetailsView getById(UUID userId);

  UserDetailsView create(UserDetailsCreateRequest registerUserDto);

  UserDetailsView edit(UserDetailsEditRequest userDto, UUID userId);

  void delete(UUID userId);

  boolean existsByIdAndUserId(UUID id, UUID userId);

  UserDetailsView me();
}

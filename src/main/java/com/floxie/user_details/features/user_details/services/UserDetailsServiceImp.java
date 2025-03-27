package com.floxie.user_details.features.user_details.services;

import static com.floxie.user_details.infrastructure.exceptions.ExceptionMessages.*;

import com.floxie.user_details.features.user_details.dto.UserDetailsCreateRequest;
import com.floxie.user_details.features.user_details.dto.UserDetailsEditRequest;
import com.floxie.user_details.features.user_details.entity.UserDetails;
import com.floxie.user_details.features.user_details.mappers.UserDetailsMapper;
import com.floxie.user_details.features.user_details.repository.UserDetailsRepository;
import com.floxie.user_details.infrastructure.config.security.SecurityUtils;
import com.floxie.user_details.infrastructure.rabbitmq.UserDetailsProducer;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.commons.exceptions.throwable.BadRequestException;
import org.commons.exceptions.throwable.NotFoundException;
import org.commons.feature.user_details.dto.UserDetailsView;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImp implements UserDetailsService {

  private final UserDetailsRepository repository;
  private final UserDetailsMapper mapper;
  private final UserDetailsProducer rabbitMqProducer;

  public UserDetailsView getById(UUID id) {
    return mapper.toView(findById(id));
  }

  public UserDetailsView create(UserDetailsCreateRequest dto) {
    var user = SecurityUtils.getCurrentLoggedInUser();

    repository
        .findByUserId(user.id())
        .ifPresent(
            userDetails -> {
              throw new BadRequestException(USER_DETAILS_ALREADY_EXISTS);
            });

    var entity = mapper.toEntity(dto);

    entity.setUserId(user.id());

    var view = mapper.toView(repository.save(entity));

    rabbitMqProducer.sendUserDetails(view);
    return view;
  }

  public UserDetailsView edit(UserDetailsEditRequest dto, UUID id) {
    var user = findById(id);

    mapper.update(user, dto);

    return mapper.toView(repository.save(user));
  }

  @Transactional
  public void delete(UUID userId) {
    repository.deleteByUserId(userId);
  }

  public boolean existsByIdAndUserId(UUID id, UUID userId) {
    return repository.existsByIdAndUserId(id, userId);
  }

  public UserDetailsView me() {
    var currentLoggedInUser = SecurityUtils.getCurrentLoggedInUser();
    return repository.findByUserId(currentLoggedInUser.id())
        .map(mapper::toView)
        .orElseThrow(() -> new NotFoundException(USER_DETAILS_NOT_FOUND_FOR_USER));
  }

  public UserDetails findById(UUID id) {
    return repository.findById(id)
        .orElseThrow(() -> new NotFoundException(USER_DETAILS_NOT_FOUND));
  }
}

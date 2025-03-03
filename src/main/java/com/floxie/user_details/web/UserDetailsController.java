package com.floxie.user_details.web;

import com.floxie.user_details.features.user_details.dto.UserDetailsCreateRequest;
import com.floxie.user_details.features.user_details.dto.UserDetailsEditRequest;
import com.floxie.user_details.features.user_details.services.UserDetailsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.commons.feature.user_details.dto.UserDetailsView;
import org.commons.feature.user_details.paths.UserDetailsControllerPaths;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(UserDetailsControllerPaths.BASE)
@RequiredArgsConstructor
public class UserDetailsController {

  private final UserDetailsService service;

  @GetMapping(UserDetailsControllerPaths.GET_BY_ID)
  @PreAuthorize("hasRole('ADMIN') || @userDetailsEvaluator.isOwner(#id)")
  public ResponseEntity<UserDetailsView> get(@PathVariable UUID id) {
    return ResponseEntity.status(HttpStatus.OK).body(service.getById(id));
  }

  @GetMapping(UserDetailsControllerPaths.ME)
  public ResponseEntity<UserDetailsView> me() {
    return ResponseEntity.status(HttpStatus.OK).body(service.me());
  }

  @PostMapping(UserDetailsControllerPaths.CREATE)
  public ResponseEntity<UserDetailsView> create(
      @RequestBody @Valid UserDetailsCreateRequest registerUserDto) {
    UserDetailsView createdUser = service.create(registerUserDto);
    return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
  }

  @PatchMapping(UserDetailsControllerPaths.EDIT)
  @PreAuthorize("hasRole('ADMIN') || @userDetailsEvaluator.isOwner(#id)")
  public ResponseEntity<UserDetailsView> edit(@RequestBody @Valid UserDetailsEditRequest dto,
                                              @PathVariable UUID id) {
    return ResponseEntity.status(HttpStatus.OK).body(service.edit(dto, id));
  }

}
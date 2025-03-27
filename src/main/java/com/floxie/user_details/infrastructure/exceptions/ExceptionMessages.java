package com.floxie.user_details.infrastructure.exceptions;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.commons.exceptions.ExceptionMessage;

@Getter
@RequiredArgsConstructor
public enum ExceptionMessages implements ExceptionMessage {
  USER_DETAILS_NOT_FOUND("User not found with id: %s", "User not found"),
  USER_DETAILS_NOT_FOUND_FOR_USER("User details not found for user", "User details not found"),
  USER_DETAILS_ALREADY_EXISTS("User details already exists", "User details already exists"),
  INVALID_USER_TOKEN("Invalid user token", "Invalid user token"),
  ;

  private final String message;
  private final String title;
}

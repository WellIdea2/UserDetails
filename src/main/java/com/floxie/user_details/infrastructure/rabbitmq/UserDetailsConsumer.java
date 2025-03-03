package com.floxie.user_details.infrastructure.rabbitmq;

import com.floxie.user_details.features.user_details.services.UserDetailsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.commons.rabbitmq.RabbitMqUserQueues;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserDetailsConsumer {
  private final UserDetailsService userDetailsService;

  @RabbitListener(queues = RabbitMqUserQueues.USER_DELETION_NOTIFY_USER_DETAILS)
  public void deleteUser(String message) {
    var userId = UUID.fromString(message);

    userDetailsService.delete(userId);

    log.info("Records deleted for user with id: {}", userId);
  }
}

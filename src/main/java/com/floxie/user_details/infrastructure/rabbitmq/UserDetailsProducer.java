package com.floxie.user_details.infrastructure.rabbitmq;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.commons.feature.shared.utils.GsonWrapper;
import org.commons.feature.user_details.dto.UserDetailsView;
import org.commons.rabbitmq.RabbitMqUserQueues;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserDetailsProducer {

  private static final GsonWrapper GSON_WRAPPER = new GsonWrapper();
  private final RabbitTemplate rabbitTemplate;

  public void sendUserDetails(UserDetailsView userDetails) {

    String message = GSON_WRAPPER.toJson(userDetails);

    rabbitTemplate.convertAndSend(RabbitMqUserQueues.USER_DETAILS_CREATION_NOTIFY_RECORD, message);
    log.info("User details sent to RabbitMQ: {}", message);
  }
}

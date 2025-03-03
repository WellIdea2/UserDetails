package com.floxie.user_details.infrastructure.config.openfeign;

import org.commons.feature.user.dto.UserView;
import org.commons.feature.user.paths.UserControllerPaths;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "authentication", configuration = UserFeignConfig.class)
public interface UserClient {

  @GetMapping(
      value = UserControllerPaths.BASE + UserControllerPaths.ME,
      produces = MediaType.APPLICATION_JSON_VALUE)
  UserView getCurrentUser();
}

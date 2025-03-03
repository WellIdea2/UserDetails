package com.floxie.user_details.infrastructure.config.openfeign;

import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Configuration
public class UserFeignConfig {

  @Bean
  public RequestInterceptor bearerTokenInterceptor() {
    return template -> {
      ServletRequestAttributes attributes =
          (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

      if (attributes != null) {
        String authHeader = attributes.getRequest().getHeader(HttpHeaders.AUTHORIZATION);

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
          template.header(HttpHeaders.AUTHORIZATION, authHeader);
        }
      }
    };
  }
}

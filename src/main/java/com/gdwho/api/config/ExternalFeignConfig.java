package com.gdwho.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.Request;

@Configuration
public class ExternalFeignConfig {

  @Bean
  public Request.Options feignOptions() {
    return new Request.Options(3000, 5000);
  }

  @Bean
  public feign.Logger.Level feignLoggerLevel() {
    return feign.Logger.Level.BASIC;
  }
}
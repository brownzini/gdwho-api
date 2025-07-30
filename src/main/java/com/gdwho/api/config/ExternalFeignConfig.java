package com.gdwho.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.Request;

@Configuration
public class ExternalFeignConfig {

  @Bean
  public Request.Options feignOptions() {
    return new Request.Options(5000, 120000);
  }

  @Bean
  public feign.Logger.Level feignLoggerLevel() {
    return feign.Logger.Level.BASIC;
  }
}
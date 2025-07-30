package com.gdwho.api.infrastructure.gateways.api;

import java.math.BigDecimal;

import org.springframework.cache.annotation.Cacheable;

import com.gdwho.api.application.gateways.ModelApiGateway;

public class ModeApiImplementation implements ModelApiGateway {

  private final ModelV1ApiClient client;

  public ModeApiImplementation(ModelV1ApiClient client) {
    this.client = client;
  }

  @Cacheable(cacheNames = "input", key = "T(java.lang.String).format('%s::input::%s', #id, #input?.toLowerCase()?.trim())", unless = "#result == null")
  @Override
  public BigDecimal send(Long id, String input) {
    String value = (input == null ? "" : input.trim().toLowerCase());
    if (value.isBlank()) {
      throw new IllegalArgumentException("[Param Error]: Input is requeried");
    }
    return client.getResult(id, value).result();
  }
}

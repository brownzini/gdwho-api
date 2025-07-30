package com.gdwho.api.infrastructure.gateways.api;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;

import com.gdwho.api.application.gateways.ModelApiGateway;
import com.gdwho.api.domain.shape.EntriesDomainShape;
import com.gdwho.api.infrastructure.gateways.api.dto.request.GuessRequestDTO;
import com.gdwho.api.infrastructure.gateways.api.dto.request.TrainRequestDTO;

public class ModeApiImplementation implements ModelApiGateway {

  private final ModelV1ApiClient client;

  public ModeApiImplementation(ModelV1ApiClient client) {
    this.client = client;
  }

  @Override
  public String train(Long id, List<EntriesDomainShape> entries) {
    return client.train(new TrainRequestDTO(id, entries)).message();
  }

  @Cacheable(cacheNames = "input", key = "T(java.lang.String).format('%s::input::%s', #id, #input?.toLowerCase()?.trim())", unless = "#result == null")
  @Override
  public double guess(Long id, List<String> data, String input) {
    return client.guess(new GuessRequestDTO(id, data, input)).result();
  }
}

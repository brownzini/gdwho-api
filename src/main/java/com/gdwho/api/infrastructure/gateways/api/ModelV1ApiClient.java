package com.gdwho.api.infrastructure.gateways.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gdwho.api.config.ExternalFeignConfig;
import com.gdwho.api.infrastructure.gateways.api.dto.ModelResponseDTO;

@FeignClient(name = "modelV1", url = "${external.api.base-url}", configuration = ExternalFeignConfig.class)
public interface ModelV1ApiClient {
  @GetMapping("/action")
  ModelResponseDTO getResult(
      @RequestParam("id") Long id,
      @RequestParam("input") String input);
}

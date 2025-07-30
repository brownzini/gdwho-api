package com.gdwho.api.infrastructure.gateways.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.gdwho.api.config.ExternalFeignConfig;

import com.gdwho.api.infrastructure.gateways.api.dto.request.GuessRequestDTO;
import com.gdwho.api.infrastructure.gateways.api.dto.request.TrainRequestDTO;
import com.gdwho.api.infrastructure.gateways.api.dto.response.TrainReponseDTO;

import com.gdwho.api.infrastructure.gateways.api.dto.response.GuessResponseDTO;

@FeignClient(
    name = "modelV1", 
    url = "${external.api.base-url}", 
    configuration = ExternalFeignConfig.class
)
public interface ModelV1ApiClient {
    @PostMapping("/train")
    TrainReponseDTO train(@RequestBody TrainRequestDTO request);

    @PostMapping("/guess")
    GuessResponseDTO guess(@RequestBody GuessRequestDTO request);
}

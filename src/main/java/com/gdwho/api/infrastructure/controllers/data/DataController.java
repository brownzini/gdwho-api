package com.gdwho.api.infrastructure.controllers.data;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.gdwho.api.application.usecases.DataUseCase;

import com.gdwho.api.infrastructure.controllers.data.dtos.request.CreateDataRequestDTO;
import com.gdwho.api.infrastructure.controllers.data.dtos.response.CreateDataResponseDTO;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/v1/api/data")
public class DataController {

  private final DataUseCase dataUseCase;

  public DataController(DataUseCase dataUseCase) {
    this.dataUseCase = dataUseCase;
  }

  @PostMapping("/add/{id}")
  public ResponseEntity<CreateDataResponseDTO> createData(@Valid @RequestBody CreateDataRequestDTO request,
      @PathVariable Long id) {

    dataUseCase.createData(request.response(), request.dataList(), request.entries(), id);

    URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
        .buildAndExpand(id).toUri();
    
    return ResponseEntity.created(uri).body(new CreateDataResponseDTO("created"));
  }

}

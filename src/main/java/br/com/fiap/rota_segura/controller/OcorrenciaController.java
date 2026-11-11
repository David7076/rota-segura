package br.com.fiap.rota_segura.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.rota_segura.dto.OcorrenciaCreateRequest;
import br.com.fiap.rota_segura.dto.OcorrenciaMapper;
import br.com.fiap.rota_segura.dto.OcorrenciaResponse;
import br.com.fiap.rota_segura.dto.OcorrenciaUpdateRequest;
import br.com.fiap.rota_segura.service.OcorrenciaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/${api.version}/ocorrencias")
@RequiredArgsConstructor
public class OcorrenciaController {

    private final OcorrenciaService service;
    private final OcorrenciaMapper mapper;

    @PostMapping
    public ResponseEntity<OcorrenciaResponse> create(@Valid @RequestBody OcorrenciaCreateRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(mapper.toDto(service.create(mapper.toModel(request))));
    }

    @GetMapping
    public ResponseEntity<List<OcorrenciaResponse>> findAll() {
        return ResponseEntity.ok(service.findAll().stream().map(mapper::toDto).toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OcorrenciaResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(mapper.toDto(service.findById(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<OcorrenciaResponse> update(@PathVariable Long id,
            @Valid @RequestBody OcorrenciaUpdateRequest request) {
        return ResponseEntity.ok(mapper.toDto(service.update(id, mapper.toModel(request))));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

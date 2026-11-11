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

import br.com.fiap.rota_segura.dto.TrechoViaCreateRequest;
import br.com.fiap.rota_segura.dto.TrechoViaMapper;
import br.com.fiap.rota_segura.dto.TrechoViaResponse;
import br.com.fiap.rota_segura.dto.TrechoViaUpdateRequest;
import br.com.fiap.rota_segura.service.TrechoViaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/${api.version}/trechos-via")
@RequiredArgsConstructor
public class TrechoViaController {

    private final TrechoViaService service;
    private final TrechoViaMapper mapper;

    @PostMapping
    public ResponseEntity<TrechoViaResponse> create(@Valid @RequestBody TrechoViaCreateRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(mapper.toDto(service.create(mapper.toModel(request))));
    }

    @GetMapping
    public ResponseEntity<List<TrechoViaResponse>> findAll() {
        return ResponseEntity.ok(service.findAll().stream().map(mapper::toDto).toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TrechoViaResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(mapper.toDto(service.findById(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TrechoViaResponse> update(@PathVariable Long id,
            @Valid @RequestBody TrechoViaUpdateRequest request) {
        return ResponseEntity.ok(mapper.toDto(service.update(id, mapper.toModel(request))));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

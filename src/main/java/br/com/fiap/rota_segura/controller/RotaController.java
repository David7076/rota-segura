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

import br.com.fiap.rota_segura.dto.RotaCreateRequest;
import br.com.fiap.rota_segura.dto.RotaMapper;
import br.com.fiap.rota_segura.dto.RotaResponse;
import br.com.fiap.rota_segura.dto.RotaUpdateRequest;
import br.com.fiap.rota_segura.service.RotaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/${api.version}/rotas")
@RequiredArgsConstructor
public class RotaController {

    private final RotaService service;
    private final RotaMapper mapper;

    @PostMapping
    public ResponseEntity<RotaResponse> create(@Valid @RequestBody RotaCreateRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(mapper.toDto(service.create(mapper.toModel(request))));
    }

    @GetMapping
    public ResponseEntity<List<RotaResponse>> findAll() {
        return ResponseEntity.ok(service.findAll().stream().map(mapper::toDto).toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RotaResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(mapper.toDto(service.findById(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RotaResponse> update(@PathVariable Long id,
            @Valid @RequestBody RotaUpdateRequest request) {
        return ResponseEntity.ok(mapper.toDto(service.update(id, mapper.toModel(request))));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

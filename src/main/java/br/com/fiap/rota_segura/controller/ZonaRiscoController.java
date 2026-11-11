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

import br.com.fiap.rota_segura.dto.ZonaRiscoCreateRequest;
import br.com.fiap.rota_segura.dto.ZonaRiscoMapper;
import br.com.fiap.rota_segura.dto.ZonaRiscoResponse;
import br.com.fiap.rota_segura.dto.ZonaRiscoUpdateRequest;
import br.com.fiap.rota_segura.service.ZonaRiscoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/${api.version}/zonas-risco")
@RequiredArgsConstructor
public class ZonaRiscoController {

    private final ZonaRiscoService service;
    private final ZonaRiscoMapper mapper;

    @PostMapping
    public ResponseEntity<ZonaRiscoResponse> create(@Valid @RequestBody ZonaRiscoCreateRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(mapper.toDto(service.create(mapper.toModel(request))));
    }

    @GetMapping
    public ResponseEntity<List<ZonaRiscoResponse>> findAll() {
        return ResponseEntity.ok(service.findAll().stream().map(mapper::toDto).toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ZonaRiscoResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(mapper.toDto(service.findById(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ZonaRiscoResponse> update(@PathVariable Long id,
            @Valid @RequestBody ZonaRiscoUpdateRequest request) {
        return ResponseEntity.ok(mapper.toDto(service.update(id, mapper.toModel(request))));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

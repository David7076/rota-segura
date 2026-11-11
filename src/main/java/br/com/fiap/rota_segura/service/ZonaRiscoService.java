package br.com.fiap.rota_segura.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.fiap.rota_segura.exception.ResourceNotFoundException;
import br.com.fiap.rota_segura.model.ZonaRisco;
import br.com.fiap.rota_segura.repository.ZonaRiscoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ZonaRiscoService {
    private final ZonaRiscoRepository repository;

    @Transactional
    public ZonaRisco create(ZonaRisco zona) {
        zona.setId(null);
        zona.setAtualizadoEm(LocalDateTime.now());
        return repository.save(zona);
    }

    @Transactional(readOnly = true)
    public ZonaRisco findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Zona de risco não encontrada: id " + id));
    }

    @Transactional(readOnly = true)
    public List<ZonaRisco> findAll() {
        return repository.findAll();
    }

    @Transactional
    public ZonaRisco update(Long id, ZonaRisco zona) {
        findById(id);
        zona.setId(id);
        zona.setAtualizadoEm(LocalDateTime.now());
        return repository.save(zona);
    }

    @Transactional
    public void deleteById(Long id) {
        repository.delete(findById(id));
    }
}

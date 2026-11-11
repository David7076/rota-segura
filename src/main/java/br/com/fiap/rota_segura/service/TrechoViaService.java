package br.com.fiap.rota_segura.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.fiap.rota_segura.exception.ResourceNotFoundException;
import br.com.fiap.rota_segura.model.StatusTransito;
import br.com.fiap.rota_segura.model.TrechoVia;
import br.com.fiap.rota_segura.repository.TrechoViaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TrechoViaService {
    private final TrechoViaRepository repository;
    private final ZonaRiscoService zonaRiscoService;

    @Transactional
    public TrechoVia create(TrechoVia trecho) {
        trecho.setId(null);
        return save(trecho);
    }

    @Transactional(readOnly = true)
    public TrechoVia findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Trecho de via não encontrado: id " + id));
    }

    @Transactional(readOnly = true)
    public List<TrechoVia> findAll() {
        return repository.findAll();
    }

    @Transactional
    public TrechoVia update(Long id, TrechoVia trecho) {
        findById(id);
        trecho.setId(id);
        return save(trecho);
    }

    @Transactional
    public void deleteById(Long id) {
        repository.delete(findById(id));
    }

    private TrechoVia save(TrechoVia trecho) {
        if (trecho.getStatusTransito() == null) {
            trecho.setStatusTransito(StatusTransito.TRANSITAVEL);
        }
        if (trecho.getZonaRisco() != null) {
            trecho.setZonaRisco(zonaRiscoService.findById(trecho.getZonaRisco().getId()));
        }
        return repository.save(trecho);
    }
}

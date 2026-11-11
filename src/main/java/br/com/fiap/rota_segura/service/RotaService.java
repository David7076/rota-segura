package br.com.fiap.rota_segura.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.fiap.rota_segura.exception.BusinessException;
import br.com.fiap.rota_segura.exception.ResourceNotFoundException;
import br.com.fiap.rota_segura.model.Rota;
import br.com.fiap.rota_segura.model.StatusTransito;
import br.com.fiap.rota_segura.model.TipoRota;
import br.com.fiap.rota_segura.model.TrechoVia;
import br.com.fiap.rota_segura.repository.RotaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RotaService {
    private static final int PENALIDADE_ATENCAO_SEGUNDOS = 120;
    private static final int PENALIDADE_INTRANSITAVEL_SEGUNDOS = 3600;

    private final RotaRepository repository;
    private final TrechoViaService trechoViaService;

    @Transactional
    public Rota create(Rota rota) {
        rota.setId(null);
        return save(rota);
    }

    @Transactional(readOnly = true)
    public Rota findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Rota não encontrada: id " + id));
    }

    @Transactional(readOnly = true)
    public List<Rota> findAll() {
        return repository.findAll();
    }

    @Transactional
    public Rota update(Long id, Rota rota) {
        findById(id);
        rota.setId(id);
        return save(rota);
    }

    @Transactional
    public void deleteById(Long id) {
        repository.delete(findById(id));
    }

    private Rota save(Rota rota) {
        rota.setGeradaEm(LocalDateTime.now());

        List<TrechoVia> trechos = new ArrayList<>();
        for (TrechoVia ref : rota.getTrechos()) {
            trechos.add(trechoViaService.findById(ref.getId()));
        }
        rota.setTrechos(trechos);

        if (!trechos.isEmpty()) {
            calcularTotais(rota);
        }
        return repository.save(rota);
    }

    /**
     * custo = tempo básico + penalidade da zona de risco + penalidade do status do trecho.
     * Rota de menor risco não pode passar por trecho intransitável.
     */
    private void calcularTotais(Rota rota) {
        double distancia = 0;
        int tempo = 0;
        long custo = 0;

        for (TrechoVia t : rota.getTrechos()) {
            if (t.getStatusTransito() == StatusTransito.INTRANSITAVEL && rota.getTipoRota() == TipoRota.MENOR_RISCO) {
                throw new BusinessException("Rota de menor risco não pode conter o trecho intransitável: " + t.getNomeVia());
            }
            distancia += t.getExtensaoMetros();
            tempo += t.getTempoBasicoSegundos();
            custo += t.getTempoBasicoSegundos();
            if (t.getZonaRisco() != null) {
                custo += t.getZonaRisco().getNivelRisco().getPenalidadeSegundos();
            }
            if (t.getStatusTransito() == StatusTransito.ATENCAO) {
                custo += PENALIDADE_ATENCAO_SEGUNDOS;
            } else if (t.getStatusTransito() == StatusTransito.INTRANSITAVEL) {
                custo += PENALIDADE_INTRANSITAVEL_SEGUNDOS;
            }
        }
        rota.setDistanciaMetros(distancia);
        rota.setTempoEstimadoSegundos(tempo);
        rota.setCustoTotal((double) custo);
    }
}

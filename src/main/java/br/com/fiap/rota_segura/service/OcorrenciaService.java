package br.com.fiap.rota_segura.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.fiap.rota_segura.exception.BusinessException;
import br.com.fiap.rota_segura.exception.ResourceNotFoundException;
import br.com.fiap.rota_segura.model.Ocorrencia;
import br.com.fiap.rota_segura.model.StatusOcorrencia;
import br.com.fiap.rota_segura.repository.OcorrenciaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OcorrenciaService {
    private static final long HORAS_VALIDADE_PADRAO = 6;

    private final OcorrenciaRepository repository;
    private final TrechoViaService trechoViaService;

    @Transactional
    public Ocorrencia create(Ocorrencia ocorrencia) {
        LocalDateTime agora = LocalDateTime.now();
        ocorrencia.setId(null);
        ocorrencia.setStatus(StatusOcorrencia.ATIVA);
        ocorrencia.setRegistradaEm(agora);
        if (ocorrencia.getExpiraEm() == null) {
            ocorrencia.setExpiraEm(agora.plusHours(HORAS_VALIDADE_PADRAO));
        }
        validarExpiracao(ocorrencia);
        return save(ocorrencia);
    }

    @Transactional(readOnly = true)
    public Ocorrencia findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ocorrência não encontrada: id " + id));
    }

    @Transactional(readOnly = true)
    public List<Ocorrencia> findAll() {
        return repository.findAll();
    }

    @Transactional
    public Ocorrencia update(Long id, Ocorrencia ocorrencia) {
        Ocorrencia atual = findById(id);
        ocorrencia.setId(id);
        ocorrencia.setRegistradaEm(atual.getRegistradaEm());
        if (ocorrencia.getStatus() == null) {
            ocorrencia.setStatus(atual.getStatus());
        }
        if (ocorrencia.getExpiraEm() == null) {
            ocorrencia.setExpiraEm(atual.getExpiraEm());
        }
        validarExpiracao(ocorrencia);
        return save(ocorrencia);
    }

    @Transactional
    public void deleteById(Long id) {
        repository.delete(findById(id));
    }

    private void validarExpiracao(Ocorrencia ocorrencia) {
        if (!ocorrencia.getExpiraEm().isAfter(ocorrencia.getRegistradaEm())) {
            throw new BusinessException("A data de expiração deve ser posterior ao registro da ocorrência.");
        }
    }

    private Ocorrencia save(Ocorrencia ocorrencia) {
        if (ocorrencia.getFonte() == null || ocorrencia.getFonte().isBlank()) {
            ocorrencia.setFonte("USUARIO");
        }
        if (ocorrencia.getTrechoVia() != null) {
            ocorrencia.setTrechoVia(trechoViaService.findById(ocorrencia.getTrechoVia().getId()));
        }
        return repository.save(ocorrencia);
    }
}

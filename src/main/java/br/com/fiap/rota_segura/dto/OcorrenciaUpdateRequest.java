package br.com.fiap.rota_segura.dto;

import br.com.fiap.rota_segura.model.StatusOcorrencia;

import lombok.Getter;
import lombok.Setter;

/** Mesmas regras do cadastro, mais o status (confirmar, resolver, expirar). */
@Getter
@Setter
public class OcorrenciaUpdateRequest extends OcorrenciaCreateRequest {
    private StatusOcorrencia status;
}

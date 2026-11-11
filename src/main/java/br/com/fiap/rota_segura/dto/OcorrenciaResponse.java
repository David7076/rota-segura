package br.com.fiap.rota_segura.dto;

import java.time.LocalDateTime;

import br.com.fiap.rota_segura.model.Gravidade;
import br.com.fiap.rota_segura.model.StatusOcorrencia;
import br.com.fiap.rota_segura.model.TipoOcorrencia;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OcorrenciaResponse {
    private Long id;
    private TipoOcorrencia tipo;
    private Gravidade gravidade;
    private String descricao;
    private Double latitude;
    private Double longitude;
    private StatusOcorrencia status;
    private String fonte;
    private String fotoUrl;
    private LocalDateTime registradaEm;
    private LocalDateTime expiraEm;
    private Long trechoViaId;
}

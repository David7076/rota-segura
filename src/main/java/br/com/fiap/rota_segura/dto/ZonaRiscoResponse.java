package br.com.fiap.rota_segura.dto;

import java.time.LocalDateTime;

import br.com.fiap.rota_segura.model.NivelRisco;
import br.com.fiap.rota_segura.model.TipoRisco;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ZonaRiscoResponse {
    private Long id;
    private String nome;
    private TipoRisco tipoRisco;
    private NivelRisco nivelRisco;
    private String bairro;
    private Double latitude;
    private Double longitude;
    private Integer raioMetros;
    private String fonte;
    private LocalDateTime atualizadoEm;
}

package br.com.fiap.rota_segura.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.rota_segura.model.TipoRota;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RotaResponse {
    private Long id;
    private Double origemLatitude;
    private Double origemLongitude;
    private Double destinoLatitude;
    private Double destinoLongitude;
    private TipoRota tipoRota;
    private Double distanciaMetros;
    private Integer tempoEstimadoSegundos;
    private Double custoTotal;
    private String justificativa;
    private LocalDateTime geradaEm;
    private List<Long> trechoIds = new ArrayList<>();
}

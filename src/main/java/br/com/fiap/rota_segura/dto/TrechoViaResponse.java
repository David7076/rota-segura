package br.com.fiap.rota_segura.dto;

import br.com.fiap.rota_segura.model.StatusTransito;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TrechoViaResponse {
    private Long id;
    private String nomeVia;
    private String bairro;
    private Double latitudeInicio;
    private Double longitudeInicio;
    private Double latitudeFim;
    private Double longitudeFim;
    private Double extensaoMetros;
    private Integer tempoBasicoSegundos;
    private StatusTransito statusTransito;
    private Long osmWayId;
    private Long zonaRiscoId;
}

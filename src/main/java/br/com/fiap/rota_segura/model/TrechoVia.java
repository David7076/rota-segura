package br.com.fiap.rota_segura.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "trechos_via")
@Getter
@Setter
@NoArgsConstructor
public class TrechoVia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome_via", nullable = false, length = 150)
    private String nomeVia;

    @Column(length = 100)
    private String bairro;

    @Column(name = "latitude_inicio", nullable = false)
    private Double latitudeInicio;

    @Column(name = "longitude_inicio", nullable = false)
    private Double longitudeInicio;

    @Column(name = "latitude_fim", nullable = false)
    private Double latitudeFim;

    @Column(name = "longitude_fim", nullable = false)
    private Double longitudeFim;

    @Column(name = "extensao_metros", nullable = false)
    private Double extensaoMetros;

    @Column(name = "tempo_basico_segundos", nullable = false)
    private Integer tempoBasicoSegundos;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_transito", nullable = false, length = 20)
    private StatusTransito statusTransito;

    /** Identificador da via no OpenStreetMap (opcional). */
    @Column(name = "osm_way_id")
    private Long osmWayId;

    @ManyToOne
    @JoinColumn(name = "zona_risco_id")
    private ZonaRisco zonaRisco;
}

package br.com.fiap.rota_segura.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "zonas_risco")
@Getter
@Setter
@NoArgsConstructor
public class ZonaRisco {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 120)
    private String nome;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_risco", nullable = false, length = 20)
    private TipoRisco tipoRisco;

    @Enumerated(EnumType.STRING)
    @Column(name = "nivel_risco", nullable = false, length = 20)
    private NivelRisco nivelRisco;

    @Column(length = 100)
    private String bairro;

    @Column(nullable = false)
    private Double latitude;

    @Column(nullable = false)
    private Double longitude;

    @Column(name = "raio_metros")
    private Integer raioMetros;

    /** Origem do dado (ex.: GeoSampa, Cemaden). */
    @Column(length = 60)
    private String fonte;

    @Column(name = "atualizado_em", nullable = false)
    private LocalDateTime atualizadoEm;
}

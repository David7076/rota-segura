package br.com.fiap.rota_segura.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OrderColumn;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "rotas")
@Getter
@Setter
@NoArgsConstructor
public class Rota {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "origem_latitude", nullable = false)
    private Double origemLatitude;

    @Column(name = "origem_longitude", nullable = false)
    private Double origemLongitude;

    @Column(name = "destino_latitude", nullable = false)
    private Double destinoLatitude;

    @Column(name = "destino_longitude", nullable = false)
    private Double destinoLongitude;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_rota", nullable = false, length = 20)
    private TipoRota tipoRota;

    @Column(name = "distancia_metros")
    private Double distanciaMetros;

    @Column(name = "tempo_estimado_segundos")
    private Integer tempoEstimadoSegundos;

    /** tempo + penalidades de risco (menor custo = rota de menor exposição conhecida). */
    @Column(name = "custo_total")
    private Double custoTotal;

    /** Explica por que a rota é considerada de menor risco. */
    @Column(length = 500)
    private String justificativa;

    @Column(name = "gerada_em", nullable = false)
    private LocalDateTime geradaEm;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "rotas_trechos",
            joinColumns = @JoinColumn(name = "rota_id"),
            inverseJoinColumns = @JoinColumn(name = "trecho_via_id"))
    @OrderColumn(name = "ordem")
    private List<TrechoVia> trechos = new ArrayList<>();
}

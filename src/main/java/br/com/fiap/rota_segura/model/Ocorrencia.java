package br.com.fiap.rota_segura.model;

import java.time.LocalDateTime;

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
@Table(name = "ocorrencias")
@Getter
@Setter
@NoArgsConstructor
public class Ocorrencia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private TipoOcorrencia tipo;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private Gravidade gravidade;

    @Column(length = 500)
    private String descricao;

    @Column(nullable = false)
    private Double latitude;

    @Column(nullable = false)
    private Double longitude;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private StatusOcorrencia status;

    /** Quem reportou (ex.: USUARIO, CGE, INMET). */
    @Column(length = 60)
    private String fonte;

    @Column(name = "foto_url", length = 300)
    private String fotoUrl;

    @Column(name = "registrada_em", nullable = false)
    private LocalDateTime registradaEm;

    @Column(name = "expira_em", nullable = false)
    private LocalDateTime expiraEm;

    @ManyToOne
    @JoinColumn(name = "trecho_via_id")
    private TrechoVia trechoVia;
}

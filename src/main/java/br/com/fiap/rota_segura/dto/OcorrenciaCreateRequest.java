package br.com.fiap.rota_segura.dto;

import java.time.LocalDateTime;

import br.com.fiap.rota_segura.model.Gravidade;
import br.com.fiap.rota_segura.model.TipoOcorrencia;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OcorrenciaCreateRequest {
    @NotNull(message = "O tipo da ocorrência é obrigatório")
    private TipoOcorrencia tipo;

    @NotNull(message = "A gravidade é obrigatória")
    private Gravidade gravidade;

    @Size(max = 500, message = "A descrição deve ter no máximo 500 caracteres")
    private String descricao;

    @NotNull(message = "A latitude é obrigatória")
    @DecimalMin(value = "-90.0", message = "Latitude inválida")
    @DecimalMax(value = "90.0", message = "Latitude inválida")
    private Double latitude;

    @NotNull(message = "A longitude é obrigatória")
    @DecimalMin(value = "-180.0", message = "Longitude inválida")
    @DecimalMax(value = "180.0", message = "Longitude inválida")
    private Double longitude;

    /** Opcional: se omitido, assume USUARIO. */
    @Size(max = 60, message = "A fonte deve ter no máximo 60 caracteres")
    private String fonte;

    @Size(max = 300, message = "A URL da foto deve ter no máximo 300 caracteres")
    private String fotoUrl;

    /** Opcional: se omitido, expira 6 horas após o registro. */
    private LocalDateTime expiraEm;

    /** Opcional: trecho de via afetado. */
    private Long trechoViaId;
}

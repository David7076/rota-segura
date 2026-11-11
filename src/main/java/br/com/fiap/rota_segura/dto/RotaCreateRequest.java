package br.com.fiap.rota_segura.dto;

import java.util.List;

import br.com.fiap.rota_segura.model.TipoRota;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RotaCreateRequest {
    @NotNull(message = "A latitude de origem é obrigatória")
    @DecimalMin(value = "-90.0", message = "Latitude inválida")
    @DecimalMax(value = "90.0", message = "Latitude inválida")
    private Double origemLatitude;

    @NotNull(message = "A longitude de origem é obrigatória")
    @DecimalMin(value = "-180.0", message = "Longitude inválida")
    @DecimalMax(value = "180.0", message = "Longitude inválida")
    private Double origemLongitude;

    @NotNull(message = "A latitude de destino é obrigatória")
    @DecimalMin(value = "-90.0", message = "Latitude inválida")
    @DecimalMax(value = "90.0", message = "Latitude inválida")
    private Double destinoLatitude;

    @NotNull(message = "A longitude de destino é obrigatória")
    @DecimalMin(value = "-180.0", message = "Longitude inválida")
    @DecimalMax(value = "180.0", message = "Longitude inválida")
    private Double destinoLongitude;

    @NotNull(message = "O tipo da rota é obrigatório")
    private TipoRota tipoRota;

    /** Opcional: com trechos informados, distância/tempo/custo são calculados pelo Service. */
    @Positive(message = "A distância deve ser positiva")
    private Double distanciaMetros;

    @Positive(message = "O tempo estimado deve ser positivo")
    private Integer tempoEstimadoSegundos;

    @PositiveOrZero(message = "O custo não pode ser negativo")
    private Double custoTotal;

    @Size(max = 500, message = "A justificativa deve ter no máximo 500 caracteres")
    private String justificativa;

    /** Ids dos trechos, na ordem em que a rota os percorre. */
    private List<Long> trechoIds;
}

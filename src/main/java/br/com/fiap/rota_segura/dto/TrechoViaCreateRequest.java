package br.com.fiap.rota_segura.dto;

import br.com.fiap.rota_segura.model.StatusTransito;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TrechoViaCreateRequest {
    @NotBlank(message = "O nome da via é obrigatório")
    @Size(max = 150, message = "O nome da via deve ter no máximo 150 caracteres")
    private String nomeVia;

    @Size(max = 100, message = "O bairro deve ter no máximo 100 caracteres")
    private String bairro;

    @NotNull(message = "A latitude de início é obrigatória")
    @DecimalMin(value = "-90.0", message = "Latitude inválida")
    @DecimalMax(value = "90.0", message = "Latitude inválida")
    private Double latitudeInicio;

    @NotNull(message = "A longitude de início é obrigatória")
    @DecimalMin(value = "-180.0", message = "Longitude inválida")
    @DecimalMax(value = "180.0", message = "Longitude inválida")
    private Double longitudeInicio;

    @NotNull(message = "A latitude de fim é obrigatória")
    @DecimalMin(value = "-90.0", message = "Latitude inválida")
    @DecimalMax(value = "90.0", message = "Latitude inválida")
    private Double latitudeFim;

    @NotNull(message = "A longitude de fim é obrigatória")
    @DecimalMin(value = "-180.0", message = "Longitude inválida")
    @DecimalMax(value = "180.0", message = "Longitude inválida")
    private Double longitudeFim;

    @NotNull(message = "A extensão em metros é obrigatória")
    @Positive(message = "A extensão deve ser positiva")
    private Double extensaoMetros;

    @NotNull(message = "O tempo básico de deslocamento é obrigatório")
    @Positive(message = "O tempo básico deve ser positivo")
    private Integer tempoBasicoSegundos;

    /** Opcional: se omitido, assume TRANSITAVEL. */
    private StatusTransito statusTransito;

    private Long osmWayId;

    /** Opcional: zona de risco que o trecho cruza. */
    private Long zonaRiscoId;
}

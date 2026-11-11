package br.com.fiap.rota_segura.dto;

import br.com.fiap.rota_segura.model.NivelRisco;
import br.com.fiap.rota_segura.model.TipoRisco;

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
public class ZonaRiscoCreateRequest {
    @NotBlank(message = "O nome da zona é obrigatório")
    @Size(max = 120, message = "O nome deve ter no máximo 120 caracteres")
    private String nome;

    @NotNull(message = "O tipo de risco é obrigatório")
    private TipoRisco tipoRisco;

    @NotNull(message = "O nível de risco é obrigatório")
    private NivelRisco nivelRisco;

    @Size(max = 100, message = "O bairro deve ter no máximo 100 caracteres")
    private String bairro;

    @NotNull(message = "A latitude é obrigatória")
    @DecimalMin(value = "-90.0", message = "Latitude inválida")
    @DecimalMax(value = "90.0", message = "Latitude inválida")
    private Double latitude;

    @NotNull(message = "A longitude é obrigatória")
    @DecimalMin(value = "-180.0", message = "Longitude inválida")
    @DecimalMax(value = "180.0", message = "Longitude inválida")
    private Double longitude;

    @Positive(message = "O raio deve ser positivo")
    private Integer raioMetros;

    @Size(max = 60, message = "A fonte deve ter no máximo 60 caracteres")
    private String fonte;
}

package br.com.fiap.rota_segura.dto;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.stereotype.Component;

import br.com.fiap.rota_segura.model.TrechoVia;
import br.com.fiap.rota_segura.model.ZonaRisco;

@Component
public class TrechoViaMapper {
    private final TypeMap<TrechoViaCreateRequest, TrechoVia> toModelMap;
    private final TypeMap<TrechoVia, TrechoViaResponse> toDtoMap;

    public TrechoViaMapper(ModelMapper modelMapper) {
        this.toModelMap = modelMapper.emptyTypeMap(TrechoViaCreateRequest.class, TrechoVia.class)
                .addMappings(m -> m.skip(TrechoVia::setZonaRisco))
                .implicitMappings();
        this.toDtoMap = modelMapper.emptyTypeMap(TrechoVia.class, TrechoViaResponse.class)
                .addMappings(m -> m.map(src -> src.getZonaRisco().getId(), TrechoViaResponse::setZonaRiscoId))
                .implicitMappings();
    }

    public TrechoVia toModel(TrechoViaCreateRequest dto) {
        TrechoVia trecho = toModelMap.map(dto);
        if (dto.getZonaRiscoId() != null) {
            ZonaRisco ref = new ZonaRisco();
            ref.setId(dto.getZonaRiscoId()); // referência; o Service valida a existência
            trecho.setZonaRisco(ref);
        }
        return trecho;
    }

    public TrechoVia toModel(TrechoViaUpdateRequest dto) {
        return toModel((TrechoViaCreateRequest) dto);
    }

    public TrechoViaResponse toDto(TrechoVia entity) {
        return toDtoMap.map(entity);
    }
}

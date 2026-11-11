package br.com.fiap.rota_segura.dto;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.stereotype.Component;

import br.com.fiap.rota_segura.model.ZonaRisco;

@Component
public class ZonaRiscoMapper {
    private final TypeMap<ZonaRiscoCreateRequest, ZonaRisco> toModelMap;
    private final TypeMap<ZonaRisco, ZonaRiscoResponse> toDtoMap;

    public ZonaRiscoMapper(ModelMapper modelMapper) {
        this.toModelMap = modelMapper.createTypeMap(ZonaRiscoCreateRequest.class, ZonaRisco.class);
        this.toDtoMap = modelMapper.createTypeMap(ZonaRisco.class, ZonaRiscoResponse.class);
    }

    public ZonaRisco toModel(ZonaRiscoCreateRequest dto) {
        return toModelMap.map(dto);
    }

    public ZonaRisco toModel(ZonaRiscoUpdateRequest dto) {
        return toModelMap.map(dto);
    }

    public ZonaRiscoResponse toDto(ZonaRisco entity) {
        return toDtoMap.map(entity);
    }
}

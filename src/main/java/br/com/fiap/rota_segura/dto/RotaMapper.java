package br.com.fiap.rota_segura.dto;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.stereotype.Component;

import br.com.fiap.rota_segura.model.Rota;
import br.com.fiap.rota_segura.model.TrechoVia;

@Component
public class RotaMapper {
    private final TypeMap<RotaCreateRequest, Rota> toModelMap;
    private final TypeMap<Rota, RotaResponse> toDtoMap;

    public RotaMapper(ModelMapper modelMapper) {
        this.toModelMap = modelMapper.emptyTypeMap(RotaCreateRequest.class, Rota.class)
                .addMappings(m -> m.skip(Rota::setTrechos))
                .implicitMappings();
        this.toDtoMap = modelMapper.emptyTypeMap(Rota.class, RotaResponse.class)
                .addMappings(m -> m.skip(RotaResponse::setTrechoIds))
                .implicitMappings();
    }

    public Rota toModel(RotaCreateRequest dto) {
        Rota rota = toModelMap.map(dto);
        if (dto.getTrechoIds() != null) {
            List<TrechoVia> refs = dto.getTrechoIds().stream().map(id -> {
                TrechoVia ref = new TrechoVia();
                ref.setId(id); // referência; o Service valida a existência
                return ref;
            }).toList();
            rota.setTrechos(new java.util.ArrayList<>(refs));
        }
        return rota;
    }

    public Rota toModel(RotaUpdateRequest dto) {
        return toModel((RotaCreateRequest) dto);
    }

    public RotaResponse toDto(Rota entity) {
        RotaResponse response = toDtoMap.map(entity);
        response.setTrechoIds(entity.getTrechos().stream().map(TrechoVia::getId).toList());
        return response;
    }
}

package br.com.fiap.rota_segura.dto;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.stereotype.Component;

import br.com.fiap.rota_segura.model.Ocorrencia;
import br.com.fiap.rota_segura.model.TrechoVia;

@Component
public class OcorrenciaMapper {
    private final TypeMap<OcorrenciaCreateRequest, Ocorrencia> toModelMap;
    private final TypeMap<Ocorrencia, OcorrenciaResponse> toDtoMap;

    public OcorrenciaMapper(ModelMapper modelMapper) {
        this.toModelMap = modelMapper.emptyTypeMap(OcorrenciaCreateRequest.class, Ocorrencia.class)
                .addMappings(m -> m.skip(Ocorrencia::setTrechoVia))
                .implicitMappings();
        this.toDtoMap = modelMapper.emptyTypeMap(Ocorrencia.class, OcorrenciaResponse.class)
                .addMappings(m -> m.map(src -> src.getTrechoVia().getId(), OcorrenciaResponse::setTrechoViaId))
                .implicitMappings();
    }

    public Ocorrencia toModel(OcorrenciaCreateRequest dto) {
        Ocorrencia ocorrencia = toModelMap.map(dto);
        if (dto.getTrechoViaId() != null) {
            TrechoVia ref = new TrechoVia();
            ref.setId(dto.getTrechoViaId()); // referência; o Service valida a existência
            ocorrencia.setTrechoVia(ref);
        }
        return ocorrencia;
    }

    public Ocorrencia toModel(OcorrenciaUpdateRequest dto) {
        Ocorrencia ocorrencia = toModel((OcorrenciaCreateRequest) dto);
        ocorrencia.setStatus(dto.getStatus());
        return ocorrencia;
    }

    public OcorrenciaResponse toDto(Ocorrencia entity) {
        return toDtoMap.map(entity);
    }
}

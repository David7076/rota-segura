package br.com.fiap.rota_segura.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;

import org.junit.jupiter.api.Test;

import br.com.fiap.rota_segura.config.ModelMapperConfig;
import br.com.fiap.rota_segura.model.*;

class MappersTest {
    private final org.modelmapper.ModelMapper mm = new ModelMapperConfig().modelMapper();

    @Test
    void trechoViaMapeiaReferenciaDeZona() {
        TrechoViaMapper mapper = new TrechoViaMapper(mm);
        TrechoViaUpdateRequest req = new TrechoViaUpdateRequest();
        req.setNomeVia("Av. Teste");
        req.setLatitudeInicio(-23.5);
        req.setLongitudeInicio(-46.6);
        req.setLatitudeFim(-23.51);
        req.setLongitudeFim(-46.61);
        req.setExtensaoMetros(120.0);
        req.setTempoBasicoSegundos(30);
        req.setZonaRiscoId(7L);

        TrechoVia e = mapper.toModel(req);
        assertEquals("Av. Teste", e.getNomeVia());
        assertEquals(7L, e.getZonaRisco().getId());
        assertNull(e.getId());

        e.setId(1L);
        TrechoViaResponse r = mapper.toDto(e);
        assertEquals(7L, r.getZonaRiscoId());
        assertEquals(120.0, r.getExtensaoMetros());
    }

    @Test
    void trechoViaSemZonaGeraResponseSemId() {
        TrechoViaMapper mapper = new TrechoViaMapper(mm);
        TrechoVia e = new TrechoVia();
        e.setId(1L);
        e.setNomeVia("X");
        assertNull(mapper.toDto(e).getZonaRiscoId());
    }

    @Test
    void ocorrenciaUpdateMapeiaStatusETrecho() {
        OcorrenciaMapper mapper = new OcorrenciaMapper(mm);
        OcorrenciaUpdateRequest req = new OcorrenciaUpdateRequest();
        req.setTipo(TipoOcorrencia.ALAGAMENTO);
        req.setGravidade(Gravidade.ALTA);
        req.setLatitude(-23.5);
        req.setLongitude(-46.6);
        req.setTrechoViaId(3L);
        req.setStatus(StatusOcorrencia.CONFIRMADA);

        Ocorrencia e = mapper.toModel(req);
        assertEquals(StatusOcorrencia.CONFIRMADA, e.getStatus());
        assertEquals(3L, e.getTrechoVia().getId());
        e.setId(9L);
        assertEquals(3L, mapper.toDto(e).getTrechoViaId());
    }

    @Test
    void rotaMapeiaListaDeTrechos() {
        RotaMapper mapper = new RotaMapper(mm);
        RotaCreateRequest req = new RotaCreateRequest();
        req.setOrigemLatitude(-23.5);
        req.setOrigemLongitude(-46.6);
        req.setDestinoLatitude(-23.6);
        req.setDestinoLongitude(-46.7);
        req.setTipoRota(TipoRota.MENOR_RISCO);
        req.setTrechoIds(List.of(2L, 1L));

        Rota e = mapper.toModel(req);
        assertEquals(List.of(2L, 1L), e.getTrechos().stream().map(TrechoVia::getId).toList());
        e.setId(5L);
        assertEquals(List.of(2L, 1L), mapper.toDto(e).getTrechoIds());
    }

    @Test
    void zonaRiscoMapeiaIdaEVolta() {
        ZonaRiscoMapper mapper = new ZonaRiscoMapper(mm);
        ZonaRiscoCreateRequest req = new ZonaRiscoCreateRequest();
        req.setNome("Córrego X");
        req.setTipoRisco(TipoRisco.ENCHENTE);
        req.setNivelRisco(NivelRisco.ALTO);
        req.setLatitude(-23.5);
        req.setLongitude(-46.6);
        ZonaRisco e = mapper.toModel(req);
        e.setId(1L);
        ZonaRiscoResponse r = mapper.toDto(e);
        assertEquals("Córrego X", r.getNome());
        assertEquals(NivelRisco.ALTO, r.getNivelRisco());
    }
}

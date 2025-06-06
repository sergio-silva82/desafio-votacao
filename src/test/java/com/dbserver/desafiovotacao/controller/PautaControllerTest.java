package com.dbserver.desafiovotacao.controller;

import com.dbserver.desafiovotacao.controller.v1.PautaController;
import com.dbserver.desafiovotacao.dto.PautaDTO;
import com.dbserver.desafiovotacao.service.interfaces.IPautaService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PautaController.class)
class PautaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IPautaService pautaService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void deveCriarPautaComSucesso() throws Exception {
        PautaDTO pautaRequest = new PautaDTO();
        pautaRequest.setNome("Pauta Teste");

        PautaDTO pautaResponse = new PautaDTO();
        pautaResponse.setId(1L);
        pautaResponse.setNome("Pauta Teste");

        Mockito.when(pautaService.criarPauta(any(PautaDTO.class))).thenReturn(pautaResponse);

        mockMvc.perform(post("/api/v1/pautas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(pautaRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.nome").value("Pauta Teste"));
    }

    @Test
    void deveListarPautas() throws Exception {
        PautaDTO pauta1 = new PautaDTO();
        pauta1.setId(1L);
        pauta1.setNome("Pauta 1");

        PautaDTO pauta2 = new PautaDTO();
        pauta2.setId(2L);
        pauta2.setNome("Pauta 2");

        Mockito.when(pautaService.listarPautas()).thenReturn(List.of(pauta1, pauta2));

        mockMvc.perform(get("/api/v1/pautas"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$[0].nome").value("Pauta 1"))
                .andExpect(jsonPath("$[1].nome").value("Pauta 2"));
    }

    @Test
    void deveRetornar400QuandoTituloPautaForNulo() throws Exception {
        PautaDTO pautaRequest = new PautaDTO();

        mockMvc.perform(post("/api/v1/pautas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(pautaRequest)))
                .andExpect(status().isBadRequest());
    }
}
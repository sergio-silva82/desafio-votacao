package com.dbserver.desafiovotacao.controller;

import com.dbserver.desafiovotacao.controller.v1.VotoController;
import com.dbserver.desafiovotacao.dto.VotoDTO;
import com.dbserver.desafiovotacao.enums.SimNaoEnum;
import com.dbserver.desafiovotacao.service.interfaces.IVotoService;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@WebMvcTest(VotoController.class)
class VotoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IVotoService votoService;

    @Test
    void devePermitirVoto() throws Exception {
        Mockito.when(votoService.votar(anyLong(), anyString(), anyInt())).thenReturn(new VotoDTO());

        mockMvc.perform(post("/api/v1/votos?sessaoId=1&cpf=12345678900&opcao=1")
        		.contentType("application/json"))
                .andExpect(status().isOk());
    }

    @Test
    void deveRetornarResultadoDaVotacao() throws Exception {
        VotoDTO v1 = new VotoDTO();
        v1.setSessaoId(1L);
        v1.setCpf("123");
        v1.setOpcao(SimNaoEnum.SIM.getId());
        v1.setPautaNome("Pauta Teste");

        Mockito.when(votoService.listarVotosPorSessao(1L)).thenReturn(List.of(v1));

        mockMvc.perform(get("/api/v1/votos/resultado")
                        .param("sessaoId", "1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Pauta: Pauta Teste = SIM: 1 votos | NÃO: 0 votos"));
    }
    
    @Test
    void deveRetornarErro406QuandoVotoInvalido() throws Exception {
        Mockito.when(votoService.votar(anyLong(), anyString(), anyInt()))
                .thenThrow(new IllegalArgumentException("Sessão encerrada"));

        mockMvc.perform(post("/api/v1/votos")
                        .param("sessaoId", "1")
                        .param("cpf", "12345678901")
                        .param("opcao", "1"))
                .andExpect(status().isNotAcceptable())
                .andExpect(content().string("Erro Voto: Sessão encerrada"));
    }
}

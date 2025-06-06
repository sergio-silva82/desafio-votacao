package com.dbserver.desafiovotacao.controller;

import com.dbserver.desafiovotacao.controller.v1.SessaoController;
import com.dbserver.desafiovotacao.dto.SessaoVotacaoDTO;
import com.dbserver.desafiovotacao.service.interfaces.ISessaoService;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SessaoController.class)
class SessaoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ISessaoService sessaoService;

    @Test
    void deveAbrirSessao() throws Exception {
        Mockito.when(sessaoService.abrirSessao(anyLong(), any())).thenReturn(new SessaoVotacaoDTO());

        mockMvc.perform(post("/api/v1/sessoes/abrir?pautaId=1&duracaoMinutos=5"))
                .andExpect(status().isOk());
    }
}

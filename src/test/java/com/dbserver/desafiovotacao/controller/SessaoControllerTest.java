package com.dbserver.desafiovotacao.controller;

import com.dbserver.desafiovotacao.entity.SessaoVotacao;
import com.dbserver.desafiovotacao.service.SessaoService;
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
    private SessaoService sessaoService;

    @Test
    void deveAbrirSessao() throws Exception {
        Mockito.when(sessaoService.abrirSessao(anyLong(), any())).thenReturn(new SessaoVotacao());

        mockMvc.perform(post("/api/sessoes/abrir?pautaId=1&duracaoMinutos=5"))
                .andExpect(status().isOk());
    }
}

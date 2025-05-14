package com.dbserver.desafiovotacao.controller;

import com.dbserver.desafiovotacao.entity.Voto;
import com.dbserver.desafiovotacao.service.VotoService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(VotoController.class)
class VotoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VotoService votoService;

    @Test
    void devePermitirVoto() throws Exception {
        Mockito.when(votoService.votar(anyLong(), anyString(), anyInt())).thenReturn(new Voto());

        mockMvc.perform(post("/api/votos?sessaoId=1&cpf=12345678900&opcao=1")
        		.contentType("application/json"))
                .andExpect(status().isOk());
    }

    @Test
    void deveObterResultado() throws Exception {
        Mockito.when(votoService.listarVotosPorSessao(anyLong())).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/api/votos/resultado?sessaoId=1"))
                .andExpect(status().isOk());
    }
}

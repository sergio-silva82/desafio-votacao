package com.dbserver.desafiovotacao.controller;

import com.dbserver.desafiovotacao.controller.v1.VotoController;
import com.dbserver.desafiovotacao.dto.VotoDTO;
import com.dbserver.desafiovotacao.service.interfaces.IVotoService;

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
    private IVotoService votoService;

    @Test
    void devePermitirVoto() throws Exception {
        Mockito.when(votoService.votar(anyLong(), anyString(), anyInt())).thenReturn(new VotoDTO());

        mockMvc.perform(post("/api/v1/votos?sessaoId=1&cpf=12345678900&opcao=1")
        		.contentType("application/json"))
                .andExpect(status().isOk());
    }

    @Test
    void deveObterResultado() throws Exception {
        Mockito.when(votoService.listarVotosPorSessao(anyLong())).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/api/v1/votos/resultado?sessaoId=1"))
                .andExpect(status().isOk());
    }
}

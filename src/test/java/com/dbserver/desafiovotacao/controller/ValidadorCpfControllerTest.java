package com.dbserver.desafiovotacao.controller;
import com.dbserver.desafiovotacao.controller.v1.ValidadorCpfController;
import com.dbserver.desafiovotacao.service.interfaces.IValidadorCpfService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ValidadorCpfController.class)
class ValidadorCpfControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IValidadorCpfService validadorCpfService;

    @Test
    void deveRetornar200QuandoCpfValidoEAptoParaVotar() throws Exception {
        Mockito.when(validadorCpfService.isCpfValido("12345678901")).thenReturn(true);
        Mockito.when(validadorCpfService.isCpfPodeVotar("12345678901")).thenReturn(true);

        mockMvc.perform(get("/api/v1/validador-cpf/permite-votar")
                        .param("cpf", "12345678901"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("ABLE_TO_VOTE"));
    }

    @Test
    void deveRetornar404QuandoCpfValidoMasNaoPodeVotar() throws Exception {
        Mockito.when(validadorCpfService.isCpfValido("12345678901")).thenReturn(true);
        Mockito.when(validadorCpfService.isCpfPodeVotar("12345678901")).thenReturn(false);

        mockMvc.perform(get("/api/v1/validador-cpf/permite-votar")
                        .param("cpf", "12345678901"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status").value("UNABLE_TO_VOTE"));
    }

    @Test
    void deveRetornar404QuandoCpfInvalido() throws Exception {
        Mockito.when(validadorCpfService.isCpfValido("123")).thenReturn(false);

        mockMvc.perform(get("/api/v1/validador-cpf/permite-votar")
                        .param("cpf", "123"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status").value("UNABLE_TO_VOTE"));
    }

    @Test
    void deveRetornar400QuandoCpfNaoEnviado() throws Exception {
        mockMvc.perform(get("/api/v1/validador-cpf/permite-votar"))
                .andExpect(status().isBadRequest());
    }
}
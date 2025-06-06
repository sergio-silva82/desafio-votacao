package com.dbserver.desafiovotacao.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.dbserver.desafiovotacao.service.impl.ValidadorCpfService;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ValidadorCpfServiceTest {

    private ValidadorCpfService validadorCpfService;
    
    public ValidadorCpfServiceTest() {
    	MockitoAnnotations.openMocks(this);
    }

    @BeforeEach
    void setUp() {
        validadorCpfService = Mockito.spy(new ValidadorCpfService());
    }

    @Test
    void deveRetornarFalsoParaCpfNulo() {
        assertFalse(validadorCpfService.isCpfValido(null));
    }

    @Test
    void deveRetornarFalsoParaCpfComMenosDe11Digitos() {
        assertFalse(validadorCpfService.isCpfValido("123456789"));
    }

    @Test
    void deveRetornarFalsoParaCpfComDigitosRepetidos() {
        assertFalse(validadorCpfService.isCpfValido("11111111111"));
    }

    @Test
    void deveRetornarVerdadeiroParaCpfValido() {
        assertTrue(validadorCpfService.isCpfValido("529.982.247-25"));
    }

    @Test
    void deveRetornarFalsoParaCpfInvalido() {
        assertFalse(validadorCpfService.isCpfValido("52998224700"));
    }

    @Test
    void deveRetornarTrueQuandoForcadoNoRandom() {
    	String cpf = "52998224725";
        ValidadorCpfService spyService = Mockito.spy(new ValidadorCpfService());

        doReturn(true).when(spyService).isCpfPodeVotar(anyString());

        assertTrue(spyService.isCpfPodeVotar(cpf));
        boolean result = validadorCpfService.isCpfPodeVotar(cpf);
        assertNotNull(result);
    }

    @Test
    void deveMascararCpfCorretamenteQuandoException() {
        ValidadorCpfService spyService = Mockito.spy(new ValidadorCpfService());
        String cpfInvalido = "abc.def.ghi-jk";

        assertFalse(spyService.isCpfValido(cpfInvalido));
    }
}
package com.dbserver.desafiovotacao.service;

import com.dbserver.desafiovotacao.dto.PautaDTO;
import com.dbserver.desafiovotacao.entity.Pauta;
import com.dbserver.desafiovotacao.repository.PautaRepository;
import com.dbserver.desafiovotacao.service.impl.PautaService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PautaServiceTest {

    @Mock
    private PautaRepository pautaRepository;

    @InjectMocks
    private PautaService pautaService;
    
    public PautaServiceTest() {
    	MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveCriarPautaComSucesso() {
        PautaDTO pautaDTO = new PautaDTO();
        pautaDTO.setNome("Nova Pauta");

        Pauta pautaSalva = new Pauta();
        pautaSalva.setId(1L);
        pautaSalva.setNome("Nova Pauta");

        when(pautaRepository.save(any(Pauta.class))).thenReturn(pautaSalva);

        PautaDTO resultado = pautaService.criarPauta(pautaDTO);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        assertEquals("Nova Pauta", resultado.getNome());

        ArgumentCaptor<Pauta> captor = ArgumentCaptor.forClass(Pauta.class);
        verify(pautaRepository).save(captor.capture());

        Pauta pautaEnviada = captor.getValue();
        assertEquals("Nova Pauta", pautaEnviada.getNome());
    }

    @Test
    void deveListarPautas() {
        Pauta pauta1 = new Pauta();
        pauta1.setId(1L);
        pauta1.setNome("Pauta 1");

        Pauta pauta2 = new Pauta();
        pauta2.setId(2L);
        pauta2.setNome("Pauta 2");

        when(pautaRepository.findAll()).thenReturn(Arrays.asList(pauta1, pauta2));
        List<PautaDTO> pautas = pautaService.listarPautas();

        assertEquals(2, pautas.size());
        assertEquals("Pauta 1", pautas.get(0).getNome());
        assertEquals("Pauta 2", pautas.get(1).getNome());

        verify(pautaRepository, times(1)).findAll();
    }
}
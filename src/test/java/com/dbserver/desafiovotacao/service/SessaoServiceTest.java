package com.dbserver.desafiovotacao.service;

import com.dbserver.desafiovotacao.config.VotacaoProperties;
import com.dbserver.desafiovotacao.entity.Pauta;
import com.dbserver.desafiovotacao.entity.SessaoVotacao;
import com.dbserver.desafiovotacao.repository.PautaRepository;
import com.dbserver.desafiovotacao.repository.SessaoVotacaoRepository;
import com.dbserver.desafiovotacao.service.interfaces.ISessaoService;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SessaoServiceTest {

    @Mock
    private SessaoVotacaoRepository sessaoRepository;

    @Mock
    private PautaRepository pautaRepository;
    
    @Mock
    private VotacaoProperties votacaoConfiguracao;

    @InjectMocks
    private ISessaoService sessaoService;

    public SessaoServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveAbrirSessaoComDuracaoDefinida() {
        Pauta pauta = new Pauta();
        pauta.setId(1L);

        when(pautaRepository.findById(1L)).thenReturn(Optional.of(pauta));

        SessaoVotacao sessaoSalva = new SessaoVotacao();
        when(sessaoRepository.save(any())).thenReturn(sessaoSalva);

        SessaoVotacao resultado = sessaoService.abrirSessao(1L, 5);

        assertNotNull(resultado);
        verify(sessaoRepository, times(1)).save(any());
    }

    @Test
    void deveLancarExcecaoQuandoPautaNaoExiste() {
        when(pautaRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> sessaoService.abrirSessao(1L, 5));
    }
}

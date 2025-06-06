package com.dbserver.desafiovotacao.service;

import com.dbserver.desafiovotacao.dto.VotoDTO;
import com.dbserver.desafiovotacao.entity.Pauta;
import com.dbserver.desafiovotacao.entity.SessaoVotacao;
import com.dbserver.desafiovotacao.entity.Voto;
import com.dbserver.desafiovotacao.enums.SimNaoEnum;
import com.dbserver.desafiovotacao.repository.SessaoVotacaoRepository;
import com.dbserver.desafiovotacao.repository.VotoRepository;
import com.dbserver.desafiovotacao.service.impl.VotoService;
import com.dbserver.desafiovotacao.service.interfaces.IValidadorCpfService;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class VotoServiceTest {

    @Mock
    private VotoRepository votoRepository;

    @Mock
    private SessaoVotacaoRepository sessaoRepository;

    @Mock
    private IValidadorCpfService cpfValidationService;

    @InjectMocks
    private VotoService votoService;

    public VotoServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void devePermitirVotoValido() {
        SessaoVotacao sessao = new SessaoVotacao();
        sessao.setId(1L);
        sessao.setAbertura(LocalDateTime.now().minusMinutes(1));
        sessao.setFechamento(LocalDateTime.now().plusMinutes(1));
        sessao.setPauta(new Pauta());

        when(sessaoRepository.findById(1L)).thenReturn(Optional.of(sessao));
        when(cpfValidationService.isCpfValido("123")).thenReturn(true);
        when(cpfValidationService.isCpfPodeVotar("123")).thenReturn(true);
        when(votoRepository.getBySessaoAndAssociadoCpf(sessao, "123")).thenReturn(Optional.empty());
        Voto voto = new Voto();
        voto.setSessao(sessao);
        when(votoRepository.save(any())).thenReturn(voto);

        VotoDTO resultado = votoService.votar(1L, "123", SimNaoEnum.SIM.getId());

        assertNotNull(resultado);
        verify(votoRepository, times(1)).save(any());
    }

    @Test
    void deveLancarExcecaoQuandoSessaoNaoExiste() {
        when(sessaoRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> votoService.votar(1L, "123", SimNaoEnum.SIM.getId()));
    }

    @Test
    void deveLancarExcecaoQuandoCpfInvalido() {
        SessaoVotacao sessao = new SessaoVotacao();
        sessao.setAbertura(LocalDateTime.now().minusMinutes(1));
        sessao.setFechamento(LocalDateTime.now().plusMinutes(1));

        when(sessaoRepository.findById(1L)).thenReturn(Optional.of(sessao));
        when(cpfValidationService.isCpfValido("123")).thenReturn(false);

        assertThrows(IllegalArgumentException.class, () -> votoService.votar(1L, "123", SimNaoEnum.SIM.getId()));
    }
}

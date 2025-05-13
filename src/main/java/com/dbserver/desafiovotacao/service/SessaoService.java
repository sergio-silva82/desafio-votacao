package com.dbserver.desafiovotacao.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.dbserver.desafiovotacao.config.VotacaoProperties;
import com.dbserver.desafiovotacao.entity.Pauta;
import com.dbserver.desafiovotacao.entity.SessaoVotacao;
import com.dbserver.desafiovotacao.repository.PautaRepository;
import com.dbserver.desafiovotacao.repository.SessaoVotacaoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SessaoService {
	private final SessaoVotacaoRepository sessaoVotacaoRepository;
	private final PautaRepository pautaRepository;
	private final VotacaoProperties votacaoConfiguracao;
	
	/**
	 * M�todo que abre a sess�o de vota��o inserindo o in�cio e o fim da mesma
	 * @param pautaId :Id da pauta a ser votada
	 * @param duracaoSessao :dura��o da sess�o em minutos
	 * @return
	 */
	public SessaoVotacao abrirSessao (Long pautaId, Integer duracaoSessao) {
		Pauta pauta = pautaRepository.findById(pautaId).orElseThrow(
				() -> new IllegalArgumentException("A Pauta n�o foi encontrada!"));
		
		SessaoVotacao sessao = new SessaoVotacao();
		sessao.setPauta(pauta);
		sessao.setAbertura(LocalDateTime.now());
		int minutos = votacaoConfiguracao.getDuracaoDefault();
		if (duracaoSessao != null && duracaoSessao > 0) {
			minutos = duracaoSessao;
		}
		sessao.setFechamento(sessao.getAbertura().plusMinutes(minutos));
		
		return sessaoVotacaoRepository.save(sessao);
	}
}
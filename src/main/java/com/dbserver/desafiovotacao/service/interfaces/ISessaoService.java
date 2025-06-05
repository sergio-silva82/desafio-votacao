package com.dbserver.desafiovotacao.service.interfaces;

import com.dbserver.desafiovotacao.entity.SessaoVotacao;

public interface ISessaoService {
	/**
	 * Método que abre a sessão de votação inserindo o início e o fim da mesma
	 * @param pautaId : Id da pauta a ser votada
	 * @param duracaoSessao : duração da sessão em minutos, se não informado o padrão é o da properties
	 * @return
	 */
	public SessaoVotacao abrirSessao (Long pautaId, Integer duracaoSessao);
}

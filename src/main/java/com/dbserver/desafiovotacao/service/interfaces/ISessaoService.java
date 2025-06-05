package com.dbserver.desafiovotacao.service.interfaces;

import com.dbserver.desafiovotacao.dto.SessaoVotacaoDTO;

public interface ISessaoService {
	/**
	 * M�todo que abre a sess�o de vota��o inserindo o in�cio e o fim da mesma
	 * @param pautaId : Id da pauta a ser votada
	 * @param duracaoSessao : dura��o da sess�o em minutos, se n�o informado o padr�o � o da properties
	 * @return
	 */
	public SessaoVotacaoDTO abrirSessao (Long pautaId, Integer duracaoSessao);
}

package com.dbserver.desafiovotacao.service.interfaces;

import java.util.List;

import com.dbserver.desafiovotacao.dto.VotoDTO;

public interface IVotoService {
	/**
     * M�todo que realiza o voto baseado em um n�mero �nico do associado(cpf)
     * @param sessaoId : id da sess�o de vota��o vinculado a pauta
     * @param cpf : n�mero �nico do associado
     * @param opcao : 1=SIM / 2=NAO
     * @throws IllegalArgumentException
     * @return
     */
    public VotoDTO votar(Long sessaoId, String cpf, int opcao);
    /**
     * M�todo que retorna a lista de votos computados em uma sess�o
     * @param sessaoId
     * @return
     */
    public List<VotoDTO> listarVotosPorSessao(Long sessaoId);
}

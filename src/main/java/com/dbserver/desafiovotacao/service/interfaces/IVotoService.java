package com.dbserver.desafiovotacao.service.interfaces;

import java.util.List;

import com.dbserver.desafiovotacao.dto.VotoDTO;

public interface IVotoService {
	/**
     * Método que realiza o voto baseado em um número único do associado(cpf)
     * @param sessaoId : id da sessão de votação vinculado a pauta
     * @param cpf : número único do associado
     * @param opcao : 1=SIM / 2=NAO
     * @throws IllegalArgumentException
     * @return
     */
    public VotoDTO votar(Long sessaoId, String cpf, int opcao);
    /**
     * Método que retorna a lista de votos computados em uma sessão
     * @param sessaoId
     * @return
     */
    public List<VotoDTO> listarVotosPorSessao(Long sessaoId);
}

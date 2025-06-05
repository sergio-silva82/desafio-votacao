package com.dbserver.desafiovotacao.service.interfaces;

import java.util.List;

import com.dbserver.desafiovotacao.dto.PautaDTO;

public interface IPautaService {
	/**
     * M�todo de cria��o das pautas de vota��o
     * @param pauta
     * @return
     */
    public PautaDTO criarPauta(PautaDTO pauta);
    /**
     * M�todo que retorna uma lista de Pautas j� cadastradas
     * @return
     */
    public List<PautaDTO> listarPautas();
}

package com.dbserver.desafiovotacao.service.interfaces;

import java.util.List;

import com.dbserver.desafiovotacao.dto.PautaDTO;

public interface IPautaService {
	/**
     * Método de criação das pautas de votação
     * @param pauta
     * @return
     */
    public PautaDTO criarPauta(PautaDTO pauta);
    /**
     * Método que retorna uma lista de Pautas já cadastradas
     * @return
     */
    public List<PautaDTO> listarPautas();
}

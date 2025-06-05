package com.dbserver.desafiovotacao.service.interfaces;

import java.util.List;

import com.dbserver.desafiovotacao.entity.Pauta;

public interface IPautaService {
	/**
     * M�todo de cria��o das pautas de vota��o
     * @param pauta
     * @return
     */
    public Pauta criarPauta(Pauta pauta);
    /**
     * M�todo que retorna uma lista de Pautas j� cadastradas
     * @return
     */
    public List<Pauta> listarPautas();
}

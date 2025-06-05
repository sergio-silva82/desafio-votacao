package com.dbserver.desafiovotacao.service.interfaces;

import java.util.List;

import com.dbserver.desafiovotacao.entity.Pauta;

public interface IPautaService {
	/**
     * Método de criação das pautas de votação
     * @param pauta
     * @return
     */
    public Pauta criarPauta(Pauta pauta);
    /**
     * Método que retorna uma lista de Pautas já cadastradas
     * @return
     */
    public List<Pauta> listarPautas();
}

package com.dbserver.desafiovotacao.service;

import com.dbserver.desafiovotacao.entity.Pauta;
import com.dbserver.desafiovotacao.repository.PautaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PautaService {

    private final PautaRepository pautaRepository;

    /**
     * Método de criação das pautas de votação
     * @param pauta
     * @return
     */
    public Pauta criarPauta(Pauta pauta) {
        return pautaRepository.save(pauta);
    }

    /**
     * Método que retorna uma lista de Pautas já cadastradas
     * @return
     */
    public List<Pauta> listarPautas() {
        return pautaRepository.findAll();
    }
}
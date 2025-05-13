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
     * M�todo de cria��o das pautas de vota��o
     * @param pauta
     * @return
     */
    public Pauta criarPauta(Pauta pauta) {
        return pautaRepository.save(pauta);
    }

    /**
     * M�todo que retorna uma lista de Pautas j� cadastradas
     * @return
     */
    public List<Pauta> listarPautas() {
        return pautaRepository.findAll();
    }
}
package com.dbserver.desafiovotacao.service.impl;

import com.dbserver.desafiovotacao.entity.Pauta;
import com.dbserver.desafiovotacao.repository.PautaRepository;
import com.dbserver.desafiovotacao.service.interfaces.IPautaService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PautaService implements IPautaService {

    private final PautaRepository pautaRepository;

    public Pauta criarPauta(Pauta pauta) {
        return pautaRepository.save(pauta);
    }

    public List<Pauta> listarPautas() {
        return pautaRepository.findAll();
    }
}
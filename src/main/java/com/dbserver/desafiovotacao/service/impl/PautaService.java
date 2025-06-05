package com.dbserver.desafiovotacao.service.impl;

import com.dbserver.desafiovotacao.dto.PautaDTO;
import com.dbserver.desafiovotacao.entity.Pauta;
import com.dbserver.desafiovotacao.repository.PautaRepository;
import com.dbserver.desafiovotacao.service.interfaces.IPautaService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PautaService implements IPautaService {

    private final PautaRepository pautaRepository;

    public PautaDTO criarPauta(PautaDTO pautaDto) {
    	Pauta pauta = new Pauta(); 
    	pauta.setNome(pautaDto.getNome());

    	return this.toDto(pautaRepository.save(pauta));
    }

    public List<PautaDTO> listarPautas() {
        List<Pauta> pautas = pautaRepository.findAll();
        
        return pautas.stream()
        		.map(this::toDto)
        		.collect(Collectors.toList());
    }
    
    private PautaDTO toDto(Pauta pauta) {
    	PautaDTO registroReturn = new PautaDTO();
    	registroReturn.setId(pauta.getId());
    	registroReturn.setNome(pauta.getNome());
    	
    	return registroReturn;
    }
}
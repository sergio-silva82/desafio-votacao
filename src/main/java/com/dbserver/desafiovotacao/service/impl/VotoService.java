package com.dbserver.desafiovotacao.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.dbserver.desafiovotacao.dto.VotoDTO;
import com.dbserver.desafiovotacao.entity.SessaoVotacao;
import com.dbserver.desafiovotacao.entity.Voto;
import com.dbserver.desafiovotacao.enums.SimNaoEnum;
import com.dbserver.desafiovotacao.repository.SessaoVotacaoRepository;
import com.dbserver.desafiovotacao.repository.VotoRepository;
import com.dbserver.desafiovotacao.service.interfaces.IValidadorCpfService;
import com.dbserver.desafiovotacao.service.interfaces.IVotoService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class VotoService implements IVotoService{

    private final String NAO_PODE_VOTAR = "Associado não pode votar";
    private final String JA_VOTOU_NA_PAUTA = "Associado já votou nesta pauta";
    private final String CPF_INVALIDO= "CPF inválido";
    private final String SESSAO_ENCERRADA= "Sessão encerrada";
    private final String SESSAO_NAO_ENCONTRADA= "Sessão não encontrada";

	private final VotoRepository votoRepository;
    private final SessaoVotacaoRepository sessaoRepository;
    private final IValidadorCpfService validadorCpfService;

    public VotoDTO votar(Long sessaoId, String cpf, int opcao) {
        
    	SessaoVotacao sessao = sessaoRepository.findById(sessaoId).orElseGet(() -> {
														    		log.error(SESSAO_NAO_ENCONTRADA);
																	throw new IllegalArgumentException(SESSAO_NAO_ENCONTRADA);
																	});

        if (LocalDateTime.now().isAfter(sessao.getFechamento())) {
        	log.error(SESSAO_ENCERRADA);
            throw new IllegalArgumentException(SESSAO_ENCERRADA);
        }

        if (!validadorCpfService.isCpfValido(cpf)) {
        	log.error(CPF_INVALIDO);
            throw new IllegalArgumentException(CPF_INVALIDO);
        }

        if (!validadorCpfService.isCpfPodeVotar(cpf)) {
        	log.error(NAO_PODE_VOTAR);
            throw new IllegalArgumentException(NAO_PODE_VOTAR);
        }

        votoRepository.getBySessaoAndAssociadoCpf(sessao, cpf)
                .ifPresent(v -> { 
                	log.error(JA_VOTOU_NA_PAUTA);
                	throw new IllegalArgumentException(JA_VOTOU_NA_PAUTA);
                	});

        Voto voto = new Voto();
        voto.setSessao(sessao);
        voto.setAssociadoCpf(cpf);
        voto.setVoto(SimNaoEnum.porId(opcao));

        votoRepository.save(voto);

        return toDto(voto);
        
    }

    public List<VotoDTO> listarVotosPorSessao(Long sessaoId) {
    	SessaoVotacao sessao = sessaoRepository.findById(sessaoId).orElseThrow(() -> new IllegalArgumentException("Sessão não encontrada"));
        List<Voto> votoList = votoRepository.findAllBySessao(sessao);
        List<VotoDTO> votosReturnList = votoList.stream()
        		.map(this::toDto)
        		.collect(Collectors.toList());
        
        return votosReturnList;
    }

    private VotoDTO toDto(Voto voto) {
    	VotoDTO votoDTO = new VotoDTO();
    	votoDTO.setCpf(voto.getAssociadoCpf());
    	votoDTO.setSessaoId(voto.getSessao().getId());
    	votoDTO.setOpcao(voto.getVoto().getId());
    	votoDTO.setPautaNome(voto.getSessao().getPauta().getNome());
    	
    	return votoDTO;
    }
}

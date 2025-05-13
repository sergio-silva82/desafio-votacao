package com.dbserver.desafiovotacao.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.dbserver.desafiovotacao.entity.SessaoVotacao;
import com.dbserver.desafiovotacao.entity.Voto;
import com.dbserver.desafiovotacao.enums.SimNaoEnum;
import com.dbserver.desafiovotacao.repository.SessaoVotacaoRepository;
import com.dbserver.desafiovotacao.repository.VotoRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class VotoService {

    private final String NAO_PODE_VOTAR = "Associado n�o pode votar";
    private final String JA_VOTOU_NA_PAUTA = "Associado j� votou nesta pauta";
    private final String CPF_INVALIDO= "CPF inv�lido";
    private final String SESSAO_ENCERRADA= "Sess�o encerrada";
    private final String SESSAO_NAO_ENCONTRADA= "Sess�o n�o encontrada";

	private final VotoRepository votoRepository;
    private final SessaoVotacaoRepository sessaoRepository;
    private final ValidadorCpfService validadoCpfService;

    /**
     * M�todo que realiza o voto baseado em um n�mero �nico do associado(cpf)
     * @param sessaoId : id da sess�o de vota��o vinculado a pauta
     * @param cpf : n�mero �nico do associado
     * @param opcao : 1=SIM / 2=NAO
     * @return
     */
    public Voto votar(Long sessaoId, String cpf, int opcao) {
        
    	SessaoVotacao sessao = sessaoRepository.findById(sessaoId).orElseGet(() -> {
														    		log.error(SESSAO_NAO_ENCONTRADA);
																	throw new IllegalArgumentException(SESSAO_NAO_ENCONTRADA);
																	});

        if (LocalDateTime.now().isAfter(sessao.getFechamento())) {
        	log.error(SESSAO_ENCERRADA);
            throw new IllegalArgumentException(SESSAO_ENCERRADA);
        }

        if (!validadoCpfService.isCpfValido(cpf)) {
        	log.error(CPF_INVALIDO);
            throw new IllegalArgumentException(CPF_INVALIDO);
        }

        if (!validadoCpfService.isCpfPodeVotar(cpf)) {
        	log.error(NAO_PODE_VOTAR);
            throw new IllegalArgumentException(NAO_PODE_VOTAR);
        }

        votoRepository.buscaPorSessaoECpfAssociado(sessao, cpf)
                .ifPresent(v -> { 
                	log.error(JA_VOTOU_NA_PAUTA);
                	throw new IllegalArgumentException(JA_VOTOU_NA_PAUTA);
                	});

        Voto voto = new Voto();
        voto.setSessao(sessao);
        voto.setAssociadoCpf(cpf);
        voto.setVoto(SimNaoEnum.porId(opcao));

        return votoRepository.save(voto);
    }

    /**
     * M�todo que retorna a lista de votos computados em uma sess�o
     * @param sessaoId
     * @return
     */
    public List<Voto> listarVotosPorSessao(Long sessaoId) {
        SessaoVotacao sessao = sessaoRepository.findById(sessaoId).orElseThrow(() -> new IllegalArgumentException("Sess�o n�o encontrada"));
        return votoRepository.buscaTodosPorSessao(sessao);
    }
}

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

    private final String NAO_PODE_VOTAR = "Associado não pode votar";
    private final String JA_VOTOU_NA_PAUTA = "Associado já votou nesta pauta";
    private final String CPF_INVALIDO= "CPF inválido";
    private final String SESSAO_ENCERRADA= "Sessão encerrada";
    private final String SESSAO_NAO_ENCONTRADA= "Sessão não encontrada";

	private final VotoRepository votoRepository;
    private final SessaoVotacaoRepository sessaoRepository;
    private final ValidadorCpfService validadoCpfService;

    /**
     * Método que realiza o voto baseado em um número único do associado(cpf)
     * @param sessaoId : id da sessão de votação vinculado a pauta
     * @param cpf : número único do associado
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
     * Método que retorna a lista de votos computados em uma sessão
     * @param sessaoId
     * @return
     */
    public List<Voto> listarVotosPorSessao(Long sessaoId) {
        SessaoVotacao sessao = sessaoRepository.findById(sessaoId).orElseThrow(() -> new IllegalArgumentException("Sessão não encontrada"));
        return votoRepository.buscaTodosPorSessao(sessao);
    }
}

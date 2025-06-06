package com.dbserver.desafiovotacao.controller.v1;

import com.dbserver.desafiovotacao.dto.SessaoVotacaoDTO;
import com.dbserver.desafiovotacao.service.interfaces.ISessaoService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador REST pra opera��es ligadas a sess�o de vota��o
 * @author sergio.monte
 *
 */
@RestController
@RequestMapping("/api/v1/sessoes")
@RequiredArgsConstructor
public class SessaoController {

    private final ISessaoService sessaoService;

    /**
     * Abre um sess�o de vota��o com in�cio e fim pr�-definidos
     * @param pautaId : id da pauta da sess�o
     * @param duracaoMinutos : inteiro pra minutos da sess�o, se n�o informado usa a configura��o do properties
     * @return
     */
    @PostMapping("/abrir")
    public ResponseEntity<SessaoVotacaoDTO> abrirSessao(
            @RequestParam(required = true) Long pautaId,
            @RequestParam(required = false) Integer duracaoMinutos
    ) {
        SessaoVotacaoDTO sessao = sessaoService.abrirSessao(pautaId, duracaoMinutos);
        return ResponseEntity.ok(sessao);
    }
}
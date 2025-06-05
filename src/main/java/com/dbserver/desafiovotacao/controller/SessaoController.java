package com.dbserver.desafiovotacao.controller;

import com.dbserver.desafiovotacao.entity.SessaoVotacao;
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
@RequestMapping("/api/sessoes")
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
    public ResponseEntity<SessaoVotacao> abrirSessao(
            @RequestParam Long pautaId,
            @RequestParam(required = false) Integer duracaoMinutos
    ) {
        SessaoVotacao sessao = sessaoService.abrirSessao(pautaId, duracaoMinutos);
        return ResponseEntity.ok(sessao);
    }
}
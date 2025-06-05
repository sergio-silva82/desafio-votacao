package com.dbserver.desafiovotacao.controller;

import com.dbserver.desafiovotacao.entity.SessaoVotacao;
import com.dbserver.desafiovotacao.service.interfaces.ISessaoService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador REST pra operações ligadas a sessão de votação
 * @author sergio.monte
 *
 */
@RestController
@RequestMapping("/api/sessoes")
@RequiredArgsConstructor
public class SessaoController {

    private final ISessaoService sessaoService;

    /**
     * Abre um sessão de votação com início e fim pré-definidos
     * @param pautaId : id da pauta da sessão
     * @param duracaoMinutos : inteiro pra minutos da sessão, se não informado usa a configuração do properties
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
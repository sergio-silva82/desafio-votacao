package com.dbserver.desafiovotacao.controller;

import com.dbserver.desafiovotacao.entity.SessaoVotacao;
import com.dbserver.desafiovotacao.service.SessaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/sessoes")
@RequiredArgsConstructor
public class SessaoController {

    private final SessaoService sessaoService;

    @PostMapping("/abrir")
    public ResponseEntity<SessaoVotacao> abrirSessao(
            @RequestParam Long pautaId,
            @RequestParam(required = false) Integer duracaoMinutos
    ) {
        SessaoVotacao sessao = sessaoService.abrirSessao(pautaId, duracaoMinutos);
        return ResponseEntity.ok(sessao);
    }
}
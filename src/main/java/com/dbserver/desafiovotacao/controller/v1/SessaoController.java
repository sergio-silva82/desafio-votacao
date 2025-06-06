package com.dbserver.desafiovotacao.controller.v1;

import com.dbserver.desafiovotacao.dto.SessaoVotacaoDTO;
import com.dbserver.desafiovotacao.service.interfaces.ISessaoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador REST pra operações ligadas a sessão de votação
 * @author sergio.monte
 *
 */
@RestController
@RequestMapping("/api/v1/sessoes")
@RequiredArgsConstructor
public class SessaoController {

    private final ISessaoService sessaoService;

    /**
     * Abre um sessão de votação com início e fim pré-definidos
     * @param pautaId : id da pauta da sessão
     * @param duracaoMinutos : inteiro pra minutos da sessão, se não informado usa a configuração do properties
     * @return
     */
    @Operation(summary = "Abre um sessão de votação com início e fim pré-definidos")
    @ApiResponse(responseCode = "200", description = "Retorna a confirmação do registro no formato SessaoVotacaoDTO")
    @PostMapping("/abrir")
    public ResponseEntity<SessaoVotacaoDTO> abrirSessao(
            @RequestParam(required = true) Long pautaId,
            @RequestParam(required = false) Integer duracaoMinutos
    ) {
        SessaoVotacaoDTO sessao = sessaoService.abrirSessao(pautaId, duracaoMinutos);
        return ResponseEntity.ok(sessao);
    }
}
package com.dbserver.desafiovotacao.controller.v1;

import com.dbserver.desafiovotacao.dto.PautaDTO;
import com.dbserver.desafiovotacao.service.interfaces.IPautaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import javax.validation.Valid;

/**
 * Controlador REST pra operações ligadas a pauta de votação
 * @author sergio.monte
 *
 */
@RestController
@RequestMapping("/api/v1/pautas")
@RequiredArgsConstructor
public class PautaController {

    private final IPautaService pautaService;

    /**
     * Cria uma pauta passando somente o título da mesma.
     * @param pauta
     * @return
     */
    @Operation(summary = "Cria uma pauta passando somente o título da mesma")
    @ApiResponse(responseCode = "200", description = "Retorna a confirmação do registro no formato PautaDTO")
    @PostMapping
    public ResponseEntity<PautaDTO> criarPauta(@RequestBody @Valid PautaDTO pauta) {
    	PautaDTO novaPauta = pautaService.criarPauta(pauta);
        return ResponseEntity.ok(novaPauta);
    }

    /**
     * Busca e retorna uma lista de pautas já cadastradas
     * @return
     */
    @Operation(summary = "Busca e retorna uma lista de pautas já cadastradas")
    @ApiResponse(responseCode = "200", description = "Retorna uma lista de registros no formato PautaDTO")
    @GetMapping
    public ResponseEntity<List<PautaDTO>> listarPautas() {
        return ResponseEntity.ok(pautaService.listarPautas());
    }
}

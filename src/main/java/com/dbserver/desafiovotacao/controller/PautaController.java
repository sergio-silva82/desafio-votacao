package com.dbserver.desafiovotacao.controller;

import com.dbserver.desafiovotacao.dto.PautaDTO;
import com.dbserver.desafiovotacao.service.interfaces.IPautaService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import javax.validation.Valid;

/**
 * Controlador REST pra opera��es ligadas a pauta de vota��o
 * @author sergio.monte
 *
 */
@RestController
@RequestMapping("/api/pautas")
@RequiredArgsConstructor
public class PautaController {

    private final IPautaService pautaService;

    /**
     * Cria uma pauta passando somente o t�tulo da mesma.
     * @param pauta
     * @return
     */
    @PostMapping
    public ResponseEntity<PautaDTO> criarPauta(@RequestBody @Valid PautaDTO pauta) {
    	PautaDTO novaPauta = pautaService.criarPauta(pauta);
        return ResponseEntity.ok(novaPauta);
    }

    /**
     * Busca e retorna uma lista de pautas j� cadastradas
     * @return
     */
    @GetMapping
    public ResponseEntity<List<PautaDTO>> listarPautas() {
        return ResponseEntity.ok(pautaService.listarPautas());
    }
}

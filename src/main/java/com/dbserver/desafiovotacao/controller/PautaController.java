package com.dbserver.desafiovotacao.controller;

import com.dbserver.desafiovotacao.entity.Pauta;
import com.dbserver.desafiovotacao.service.PautaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST pra operações ligadas a pauta de votação
 * @author sergio.monte
 *
 */
@RestController
@RequestMapping("/api/pautas")
@RequiredArgsConstructor
public class PautaController {

    private final PautaService pautaService;

    /**
     * Cria uma pauta passando somente o título da mesma.
     * @param pauta
     * @return
     */
    @PostMapping
    public ResponseEntity<Pauta> criarPauta(@RequestBody Pauta pauta) {
        Pauta novaPauta = pautaService.criarPauta(pauta);
        return ResponseEntity.ok(novaPauta);
    }

    /**
     * Busca e retorna uma lista de pautas já cadastradas
     * @return
     */
    @GetMapping
    public ResponseEntity<List<Pauta>> listarPautas() {
        return ResponseEntity.ok(pautaService.listarPautas());
    }
}

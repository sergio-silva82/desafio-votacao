package com.dbserver.desafiovotacao.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dbserver.desafiovotacao.dto.VotoDTO;
import com.dbserver.desafiovotacao.enums.SimNaoEnum;
import com.dbserver.desafiovotacao.service.interfaces.IVotoService;

import lombok.RequiredArgsConstructor;

/**
 * Controlador REST pra operações de votação
 * @author sergio.monte
 *
 */
@RestController
@RequestMapping("/api/votos")
@RequiredArgsConstructor
public class VotoController {

    private final IVotoService votoService;

    /**
     * Registra o voto do associado por sessão e cpf
     * @param sessaoId
     * @param cpf
     * @param opcao
     * @return 200 para voto ok / 406 para não votado
     */
    @PostMapping
    public ResponseEntity<?> votar(
            @RequestParam(required = true) @Valid Long sessaoId,
            @RequestParam(required = true) @Valid String cpf,
            @RequestParam(required = true) @Valid int opcao
    ) {
        try {
			VotoDTO voto = votoService.votar(sessaoId, cpf, opcao);
			return ResponseEntity.ok(voto);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Erro Voto: " + e.getMessage()); 
		}
    }

    /**
     * Busca e totaliza os votos pra uma sessão específica
     * @param sessaoId
     * @return texto com o total de votos sim/não
     */
    @GetMapping("/resultado")
    public ResponseEntity<String> resultado(@RequestParam(required = true) @Valid Long sessaoId) {
        List<VotoDTO> votos = votoService.listarVotosPorSessao(sessaoId);
        String resultado = "";
        try {
	        long sim = votos.stream().filter(v -> v.getOpcao() == SimNaoEnum.SIM.getId()).count();
	        long nao = votos.stream().filter(v -> v.getOpcao() == SimNaoEnum.NAO.getId()).count();
	
	        String nomePauta = votos.get(0).getPautaNome();
	        resultado = String.format("Pauta: " + nomePauta + " = SIM: %d votos | NÃO: %d votos", sim, nao);
        } catch(IndexOutOfBoundsException e) {
        	resultado = "Sem Votação";
        }
        return ResponseEntity.ok(resultado);
    }
}
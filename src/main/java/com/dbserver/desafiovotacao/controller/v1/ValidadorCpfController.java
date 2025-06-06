package com.dbserver.desafiovotacao.controller.v1;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.dbserver.desafiovotacao.service.interfaces.IValidadorCpfService;

import java.util.Map;

/**
 * Controlador REST pra operações com o cpf do associado
 * @author sergio.monte
 *
 */
@RestController
@RequestMapping("/api/v1/validador-cpf")
@RequiredArgsConstructor
public class ValidadorCpfController {

    private final String UNABLE_VOTE = "UNABLE_TO_VOTE";
    private final String ABLE_VOTE = "ABLE_TO_VOTE";
    private final String STATUS = "status";
    
	private final IValidadorCpfService validadorCpf;

    /**
     * Verifica se o cpf está habilitado pra votar
     * @param cpf
     * @return retorna 200 pra apto pra votar e 404 pra não apto
     */
	@GetMapping("/permite-votar")
    public ResponseEntity<Map<String, String>> validarSePodeVotar( @RequestParam(required = true) String cpf) {
        if(validadorCpf.isCpfValido(cpf)) {
        	if(validadorCpf.isCpfPodeVotar(cpf)) {
        		return ResponseEntity.ok(Map.of(STATUS, ABLE_VOTE));
        	} else {
        		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of(STATUS, UNABLE_VOTE)); 
        	}
    	} else {
    		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of(STATUS, UNABLE_VOTE)); 
    	}
    }

}
package com.dbserver.desafiovotacao.dto;

import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class SessaoVotacaoDTO {
	private Long sessaoId;

	@NotNull(message = "aberturaDataHora � obrigat�rio")
    private LocalDateTime aberturaDataHora;

	@NotNull(message = "fechamentoDataHora � obrigat�rio")
	private LocalDateTime fechamentoDataHora;

	private Long pautaId;

	private String pautaNome;
}

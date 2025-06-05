package com.dbserver.desafiovotacao.dto;

import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class SessaoVotacaoDTO {
	private Long sessaoId;

	@NotNull(message = "aberturaDataHora é obrigatório")
    private LocalDateTime aberturaDataHora;

	@NotNull(message = "fechamentoDataHora é obrigatório")
	private LocalDateTime fechamentoDataHora;

	private Long pautaId;

	private String pautaNome;
}

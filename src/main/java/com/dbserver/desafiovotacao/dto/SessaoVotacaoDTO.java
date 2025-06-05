package com.dbserver.desafiovotacao.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class SessaoVotacaoDTO {
	private Long sessaoId;
    private LocalDateTime aberturaDataHora;
    private LocalDateTime fechamentoDataHora;
    private Long pautaId;
    private String pautaNome;
}

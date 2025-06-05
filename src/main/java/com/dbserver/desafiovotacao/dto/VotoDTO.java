package com.dbserver.desafiovotacao.dto;

import lombok.Data;

@Data
public class VotoDTO {
	private Long sessaoId;
    private String cpf;
    private String pautaNome;
    private int opcao; // 0 para NÃO, 1 para SIM
}

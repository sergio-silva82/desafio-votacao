package com.dbserver.desafiovotacao.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class VotoDTO {
	@NotNull(message = "sessaoId é obrigatório")
	private Long sessaoId;

	@NotBlank(message = "cpf é obrigatório")
    private String cpf;

	private String pautaNome;

	@Min(value = 0, message = "opcao deve ser 1 (SIM) ou 2 (NÃO)")
    @Max(value = 1, message = "opcao deve ser 1 (SIM) ou 2 (NÃO)")
	private int opcao;
}

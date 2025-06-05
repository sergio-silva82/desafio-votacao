package com.dbserver.desafiovotacao.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class VotoDTO {
	@NotNull(message = "sessaoId � obrigat�rio")
	private Long sessaoId;

	@NotBlank(message = "cpf � obrigat�rio")
    private String cpf;

	private String pautaNome;

	@Min(value = 0, message = "opcao deve ser 1 (SIM) ou 2 (N�O)")
    @Max(value = 1, message = "opcao deve ser 1 (SIM) ou 2 (N�O)")
	private int opcao;
}

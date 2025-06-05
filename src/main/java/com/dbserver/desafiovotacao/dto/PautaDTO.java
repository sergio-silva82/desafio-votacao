package com.dbserver.desafiovotacao.dto;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class PautaDTO {
	private Long id;

	@NotBlank(message = "nome � obrigat�rio")
    private String nome;
}

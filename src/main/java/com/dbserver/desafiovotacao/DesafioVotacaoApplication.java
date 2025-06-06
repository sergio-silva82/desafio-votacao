package com.dbserver.desafiovotacao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(info = @Info(
		title = "Desafio Vota��o",
		version = "1.0",
		description = "Projeto de desafio de programa��o"))
@SpringBootApplication
public class DesafioVotacaoApplication {
    public static void main(String[] args) {
        SpringApplication.run(DesafioVotacaoApplication.class, args);
    }
}

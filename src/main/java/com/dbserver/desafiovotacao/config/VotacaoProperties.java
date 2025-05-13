package com.dbserver.desafiovotacao.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@ConfigurationProperties(prefix = "votacao.sessao")
@Component
@Getter
@Setter
public class VotacaoProperties{
	private Integer duracaoDefault;
}
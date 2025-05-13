package com.dbserver.desafiovotacao.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@ConfigurationProperties(prefix = "callback.url")
@Component
@Getter
@Setter
public class CallbackProperties{
	private String base;
}
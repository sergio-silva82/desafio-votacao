package com.dbserver.desafiovotacao.enums;


public enum SimNaoEnum {
	SIM(1, "Sim"),
	NAO(2, "Não");
	
	private final int id;
	private final String descricao;
	
	SimNaoEnum(int id, String descricao) {
		this.id = id;
		this.descricao = descricao;
	}

	public int getId() {
		return id;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public static SimNaoEnum porId(int id) {
		for(SimNaoEnum opcaoVoto : SimNaoEnum.values()) {
			if(opcaoVoto.getId() == id) {
				return opcaoVoto;
			}
		}
		throw new IllegalArgumentException("Id: " + id + " inválido.");
	}
	
}
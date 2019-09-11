package com.tcc.lolanalise.enums;

public enum StatusPartidaEnum {
	PRONTA(1),
	EM_ANALISE(2),
	ANALISADA(3),
	REMOVIDA(4);

	private final Integer status;

	StatusPartidaEnum(Integer status) {
		this.status = status;
	}

	public Integer getStatus() {
		return status;
	}
}

package com.tcc.lolanalise.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PartidaDTO {
	private Boolean resultado;

	private Integer multiKill;

	private Integer duracao;

	private String gameType;

	private Integer nomeCampeao;

	//private Lista de Speel

	private String kda;

	//Lista de itens

	private Long scoreVisao;

	private Integer farm;

	private Integer gold;

	private Integer wards;
}

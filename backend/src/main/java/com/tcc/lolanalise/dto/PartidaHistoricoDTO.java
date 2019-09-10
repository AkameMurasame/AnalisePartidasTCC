package com.tcc.lolanalise.dto;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PartidaHistoricoDTO {
	private Long id;

	private Boolean resultado;

	private Integer multiKill;

	private Integer duracao;

	private String gameType;

	private Integer nomeCampeao;

	private List<ItemDTO> build;

	private String kda;

	private List<SpellDTO> spells;

	private Long scoreVisao;

	private Integer farm;

	private Integer gold;

	private Integer wards;
}

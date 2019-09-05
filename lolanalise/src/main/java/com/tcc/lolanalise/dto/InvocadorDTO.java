package com.tcc.lolanalise.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "accountId")
@ToString
public class InvocadorDTO {
	private String accountId;
	private String id;
	private String puuid;
	private String name;
	private int profileIconId;
	private long revisionDate;
	private int summonerLevel;
	private int validarIconId;
	private String urlIcone;
	private String urlIconeValidar;
}

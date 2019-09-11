package com.tcc.lolanalise.dto;

import java.util.List;

import com.tcc.lolanalise.domain.PartidasProPlayer;
import com.tcc.lolanalise.domain.Regiao;
import com.tcc.lolanalise.domain.Role;

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
public class ProPlayerDTO {

	private String accountId;

	private String nome;

	private String nick;

	private Regiao regiaoId;

	private Role mainRole;

	private List<PartidasProPlayer> partidasProPlayer;
}

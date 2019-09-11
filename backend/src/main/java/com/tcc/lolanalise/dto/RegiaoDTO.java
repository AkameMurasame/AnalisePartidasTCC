package com.tcc.lolanalise.dto;

import java.util.List;

import com.tcc.lolanalise.domain.ProPlayer;

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
@EqualsAndHashCode(of = "id")
@ToString
public class RegiaoDTO {

	private Integer id;

	private String regiao;

	private List<ProPlayer> proPlayer;
}

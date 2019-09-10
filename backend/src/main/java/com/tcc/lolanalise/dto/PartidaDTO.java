package com.tcc.lolanalise.dto;

import java.util.Date;

import com.tcc.lolanalise.domain.Invocador;

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
@EqualsAndHashCode(of = "idPartida")
@ToString
public class PartidaDTO {
	private Integer idPartida;

	private Long idPartidaRiot;

	private String jsonPartida;

	private String jsonReferenciaPartida;

	private Date dataCriacao;

	private Invocador accountId;
}

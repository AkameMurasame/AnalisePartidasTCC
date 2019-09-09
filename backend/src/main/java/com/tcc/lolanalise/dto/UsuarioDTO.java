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
@EqualsAndHashCode(of = "id")
@ToString
public class UsuarioDTO {
	private Integer id;

	private String username;

	private String email;

	private String password;

	private String accessToken;

	private Boolean valido;
}

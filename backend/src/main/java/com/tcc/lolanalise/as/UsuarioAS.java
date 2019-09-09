package com.tcc.lolanalise.as;

import org.springframework.stereotype.Service;

import com.tcc.lolanalise.comum.ConverterMapper;
import com.tcc.lolanalise.domain.Usuario;
import com.tcc.lolanalise.dto.UsuarioDTO;
import com.tcc.lolanalise.service.UsuarioService;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsuarioAS {
	@NonNull
	private UsuarioService usuarioService;

	@NonNull
	private ConverterMapper mapper;

	public UsuarioDTO login(UsuarioDTO usuario) {
		return usuarioService.login(mapper.map(usuario, Usuario.class));
	}

	public UsuarioDTO cadastroUsuario(UsuarioDTO usuario) {
		usuario = mapper.map(usuarioService.cadastroUsuario(mapper.map(usuario, Usuario.class)), UsuarioDTO.class);
		return usuario;
	}
}

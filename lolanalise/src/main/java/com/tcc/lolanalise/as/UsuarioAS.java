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
		String jwt = usuarioService.login(mapper.map(usuario, Usuario.class));
		usuario.setAuthToken(jwt);
		return usuario;
}
	
	public String cadastroUsuario(UsuarioDTO usuario) {
		return usuarioService.cadastroUsuario(mapper.map(usuario, Usuario.class));
	}
}

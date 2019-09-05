package com.tcc.lolanalise.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tcc.lolanalise.as.UsuarioAS;
import com.tcc.lolanalise.dto.UsuarioDTO;
import com.tcc.lolanalise.security.CurrentUser;
import com.tcc.lolanalise.security.UserPrincipal;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class UsuarioAPI {
	@NonNull
	private UsuarioAS usuarioAS;

	@PostMapping("/login")
	public UsuarioDTO login(@RequestBody UsuarioDTO usuario) {
		return usuarioAS.login(usuario);
	}

	@PostMapping("/cadastro")
	public String cadastroUsuario(@RequestBody UsuarioDTO usuario) {
		return usuarioAS.cadastroUsuario(usuario);
	}
	
	@GetMapping("/user/me")
    public UserPrincipal getCurrentUser(@CurrentUser UserPrincipal currentUser) {
        return currentUser;
    }
}

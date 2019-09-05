package com.tcc.lolanalise.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.tcc.lolanalise.domain.Role;
import com.tcc.lolanalise.domain.RoleName;
import com.tcc.lolanalise.domain.Usuario;
import com.tcc.lolanalise.dto.UsuarioDTO;
import com.tcc.lolanalise.exception.AppException;
import com.tcc.lolanalise.repository.UsuarioRepository;
import com.tcc.lolanalise.security.JwtTokenProvider;
import com.tcc.lolanalise.security.UserPrincipal;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UsuarioService {
	@NonNull
	private UsuarioRepository userRepository;

	@NonNull
	private PasswordEncoder passwordEncoder;

	@NonNull
	private JwtTokenProvider tokenProvider;

	@NonNull
	private AuthenticationManager authenticationManager;

	@ExceptionHandler
	public UsuarioDTO login(Usuario usuario) {
		try {
			Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(usuario.getUsername(), usuario.getPassword()));
			SecurityContextHolder.getContext().setAuthentication(authentication);
			UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
			String jwt = tokenProvider.generateToken(userPrincipal);
			UsuarioDTO usuarioLogado = new UsuarioDTO();
			usuarioLogado.setId(userPrincipal.getId());
			usuarioLogado.setUsername(usuarioLogado.getUsername());
			usuarioLogado.setAccessToken(jwt);
			return usuarioLogado;
		} catch (AuthenticationException e) {
			throw e;
		}
	}

	public Usuario cadastroUsuario(Usuario usuario) throws AppException {
		if (!userRepository.existsByUsername(usuario.getUsername())) {
			usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
			List<Role> roles = new ArrayList<Role>();
			Role role = new Role();
			role.setName(RoleName.ROLE_USER);
			roles.add(role);
			usuario.setRoles(roles);
			return userRepository.save(usuario);
		} else {
			throw new AppException("Username is already in use");
		}
	}
}

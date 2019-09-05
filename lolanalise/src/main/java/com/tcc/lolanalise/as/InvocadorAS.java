package com.tcc.lolanalise.as;

import org.springframework.stereotype.Service;

import com.tcc.lolanalise.comum.ConverterMapper;
import com.tcc.lolanalise.domain.Invocador;
import com.tcc.lolanalise.domain.Usuario;
import com.tcc.lolanalise.dto.InvocadorDTO;
import com.tcc.lolanalise.security.UserPrincipal;
import com.tcc.lolanalise.service.InvocadorService;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import net.rithms.riot.api.RiotApiException;

@Service
@RequiredArgsConstructor
public class InvocadorAS {

	@NonNull
	private ConverterMapper mapper;

	@NonNull
	private InvocadorService invocadorService;

	public InvocadorDTO validarInvocador(String nickname) throws RiotApiException {
		InvocadorDTO invocadorDTO = mapper.map(invocadorService.validarInvocador(nickname), InvocadorDTO.class);

		return invocadorDTO;
	}

	public InvocadorDTO getInvocadorByNickname(String nickname) throws RiotApiException {
		InvocadorDTO invocadorDTO = mapper.map(invocadorService.validarInvocador(nickname), InvocadorDTO.class);

		return invocadorDTO;
	}

	public InvocadorDTO saveInvocador(InvocadorDTO invocador, UserPrincipal currentUser) throws RiotApiException {
		Invocador invocadorEntity = mapper.map(invocadorService.getInvocador(invocador.getName()), Invocador.class);
		Usuario usuario = mapper.map(currentUser, Usuario.class);
		invocadorEntity.setUsuario(usuario);
		return invocador = mapper.map(invocadorService.saveInvocador(invocadorEntity), InvocadorDTO.class);
	}
}

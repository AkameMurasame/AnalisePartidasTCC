package com.tcc.lolanalise.as;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tcc.lolanalise.comum.ConverterMapper;
import com.tcc.lolanalise.domain.Invocador;
import com.tcc.lolanalise.domain.Usuario;
import com.tcc.lolanalise.dto.InvocadorDTO;
import com.tcc.lolanalise.dto.PartidaHistoricoDTO;
import com.tcc.lolanalise.security.UserPrincipal;
import com.tcc.lolanalise.service.InvocadorService;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InvocadorAS {

	@NonNull
	private ConverterMapper mapper;

	@NonNull
	private InvocadorService invocadorService;

	public InvocadorDTO validarInvocador(String nickname) {
		return mapper.map(invocadorService.validarInvocador(nickname), InvocadorDTO.class);
	}

	public InvocadorDTO getInvocadorByNickname(String nickname) {
		return mapper.map(invocadorService.validarInvocador(nickname), InvocadorDTO.class);
	}

	public InvocadorDTO saveInvocador(InvocadorDTO invocador, UserPrincipal currentUser) {
		Invocador invocadorEntity = mapper.map(invocadorService.getInvocador(invocador.getName()), Invocador.class);
		Usuario usuario = mapper.map(currentUser, Usuario.class);
		invocadorEntity.setIdUsuario(usuario);
		return mapper.map(invocadorService.saveInvocador(invocadorEntity), InvocadorDTO.class);
	}

	public List<PartidaHistoricoDTO> getHistorico(String accountId) {
		return invocadorService.getHistorico(accountId);
	}
}

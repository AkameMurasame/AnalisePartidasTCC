package com.tcc.lolanalise.service;

import org.springframework.stereotype.Component;

import com.tcc.lolanalise.domain.Invocador;
import com.tcc.lolanalise.repository.InvocadorRepository;
import com.tcc.lolanalise.security.UserPrincipal;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import net.rithms.riot.api.RiotApiException;
import net.rithms.riot.api.endpoints.match.dto.MatchList;
import net.rithms.riot.api.endpoints.summoner.dto.Summoner;

@Component
@RequiredArgsConstructor
public class InvocadorService {
	
	@NonNull
	private RiotApiService riotApiService;
	
	@NonNull
	private InvocadorRepository invocadorRepository;
	
	public Summoner validarInvocador(String nickname) throws RiotApiException {
		return riotApiService.getInvocadorByNickName(nickname);
	}
	
	public Summoner getInvocador(String nickname) throws RiotApiException {
		return riotApiService.getInvocadorByNickName(nickname);
	}
	
	public Invocador saveInvocador(Invocador invocador) {
		return invocadorRepository.save(invocador);
	}
	
	public void getHistorico(String accountId) throws RiotApiException {
		MatchList partidas = getPartidasJogadas(accountId);
	}
	
	private MatchList getPartidasJogadas(String accountId) throws RiotApiException {
		return riotApiService.getPartidasJogadas(accountId);
	}
}

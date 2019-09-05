package com.tcc.lolanalise.service;

import org.springframework.stereotype.Component;

import net.rithms.riot.api.ApiConfig;
import net.rithms.riot.api.RiotApi;
import net.rithms.riot.api.RiotApiException;
import net.rithms.riot.api.endpoints.match.dto.Match;
import net.rithms.riot.api.endpoints.match.dto.MatchList;
import net.rithms.riot.api.endpoints.summoner.dto.Summoner;
import net.rithms.riot.constant.Platform;

@Component
public class RiotApiService {

	private ApiConfig config;
	private RiotApi api;

	public RiotApiService() {
		config = new ApiConfig().setKey("RGAPI-da9f7260-2329-45b9-9380-9cefb71f5fd8");
		api = new RiotApi(config);
	}

	public Summoner getInvocadorByNickName(String nickname) {
		try {
			return api.getSummonerByName(Platform.BR, nickname);
		} catch (RiotApiException e) {
			//Colocar Log dps
			e.getMessage();
		}
		return null;
	}

	public MatchList getPartidasJogadas(String accountId) {
		try {
			return api.getMatchListByAccountId(Platform.BR, accountId);
		} catch (RiotApiException e) {
			//Colocar Log dps
			e.getMessage();
		}
		return null;
	}

	public Match getInformacoesPartida(Long idPartida) {
		try {
			return api.getMatch(Platform.BR, idPartida);
		} catch (RiotApiException e) {
			//Colocar Log dps
			e.getMessage();
		}
		return null;
	}
}

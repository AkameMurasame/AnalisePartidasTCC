package com.tcc.lolanalise.service;

import org.springframework.stereotype.Component;

import net.rithms.riot.api.ApiConfig;
import net.rithms.riot.api.RiotApi;
import net.rithms.riot.api.RiotApiException;
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
	
	public Summoner getInvocadorByNickName(String nickname) throws RiotApiException {
		return api.getSummonerByName(Platform.BR, nickname);
	}
	
	public MatchList getPartidasJogadas(String accountId) throws RiotApiException {
		return api.getMatchListByAccountId(Platform.BR, accountId);
	}
}

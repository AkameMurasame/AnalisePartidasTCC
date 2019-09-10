package com.tcc.lolanalise.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.tcc.lolanalise.comum.ConverterMapper;

import lombok.NonNull;
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

    private static final Logger logger = LoggerFactory.getLogger(RiotApiService.class);

    @NonNull
	private ConverterMapper mapper;

	public RiotApiService() {
		config = new ApiConfig().setKey("RGAPI-56974c50-0a8d-4cf0-b4b7-ab2693b2b7f7");
		api = new RiotApi(config);
	}

	public Summoner getInvocadorByNickName(String nickname) {
		try {
			return api.getSummonerByName(Platform.BR, nickname);
		} catch (RiotApiException e) {
	        logger.error("ERROR. Message - {}", RiotApiService.class + " | " + e.getMessage());
		}
		return null;
	}

	public MatchList getPartidasJogadas(String accountId) {
		try {
			return api.getMatchListByAccountId(Platform.BR, accountId);
		} catch (RiotApiException e) {
			logger.error("ERROR. Message - {}", RiotApiService.class + " | " + e.getMessage());
		}
		return null;
	}

	public Match getInformacoesPartida(Long idPartida) {
		try {
			return api.getMatch(Platform.BR, idPartida);
		} catch (RiotApiException e) {
			logger.error("ERROR. Message - {}", RiotApiService.class + " | " + e.getMessage());;
		}
		return null;
	}

	public <S, D> D convertRiotClasses(S sourceObject, Class<D> destinationClass) {
		return mapper.map(sourceObject, destinationClass);
	}
}

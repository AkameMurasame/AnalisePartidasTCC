package com.tcc.lolanalise.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.stereotype.Component;

import com.tcc.lolanalise.comum.ConverterMapper;
import com.tcc.lolanalise.domain.Invocador;
import com.tcc.lolanalise.dto.PartidaHistoricoDTO;
import com.tcc.lolanalise.repository.InvocadorRepository;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import net.rithms.riot.api.endpoints.summoner.dto.Summoner;

@Component
@RequiredArgsConstructor
public class InvocadorService {

	@NonNull
	private RiotApiService riotApiService;

	@NonNull
	private InvocadorRepository invocadorRepository;

	@NonNull
	private PartidasService partidaService;

	@NonNull
	private ConverterMapper mapper;

	public Summoner validarInvocador(String nickname) {
		return riotApiService.getInvocadorByNickName(nickname);
	}

	public Summoner getInvocador(String nickname) {
		return riotApiService.getInvocadorByNickName(nickname);
	}

	public Invocador saveInvocador(Invocador invocador) {
		return invocadorRepository.save(invocador);
	}

	public List<Invocador> getInvocadoresToScheduler() {
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
		String dataScheduler = dateTimeFormatter.format(LocalDateTime.now());
		String dataManual = dateTimeFormatter.format(LocalDateTime.now().minusHours(1));
		return invocadorRepository.getInvocadoresToScheduler(dataScheduler, dataManual);
	}

	public Invocador getInvocadorByAccountId(String accountId) {
		return invocadorRepository.getInvocadorByAccountId(accountId);
	}

	public List<PartidaHistoricoDTO> getHistorico(String accountId) {
		return partidaService.getHistorico(accountId);
	}
}

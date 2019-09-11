package com.tcc.lolanalise.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tcc.lolanalise.comum.ConverterMapper;
import com.tcc.lolanalise.domain.Invocador;
import com.tcc.lolanalise.domain.Partida;
import com.tcc.lolanalise.domain.StatusPartida;
import com.tcc.lolanalise.dto.ItemDTO;
import com.tcc.lolanalise.dto.PartidaDTO;
import com.tcc.lolanalise.dto.PartidaHistoricoDTO;
import com.tcc.lolanalise.dto.SpellDTO;
import com.tcc.lolanalise.repository.PartidaRepository;
import com.tcc.lolanalise.repository.StatusPartidaRepository;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import net.rithms.riot.api.endpoints.match.dto.Match;
import net.rithms.riot.api.endpoints.match.dto.MatchList;
import net.rithms.riot.api.endpoints.match.dto.MatchReference;
import net.rithms.riot.api.endpoints.match.dto.Participant;
import net.rithms.riot.api.endpoints.match.dto.ParticipantStats;

@Component
@RequiredArgsConstructor
public class PartidasService {

	@NonNull
	private RiotApiService riotApiService;

	@NonNull
	private DataDragonService dataDragonService;

	@NonNull
	private PartidaRepository partidaRepository;

	@NonNull
	private InvocadorService invocadorService;

	@NonNull
	private StatusPartidaRepository statusPartidaRepository;

	@NonNull
	private ConverterMapper mapper;

	private static final Logger logger = LoggerFactory.getLogger(PartidasService.class);

	public List<Partida> getHistoricoToScheduler(String accountId) {
		return partidaRepository.getHistorico(accountId);
	}

	public List<PartidaHistoricoDTO> getHistorico(String accountId) {
		MatchList matchList = this.getPartidasJogadas(accountId);
		PartidaHistoricoDTO partidaDTO = new PartidaHistoricoDTO();
		List<PartidaHistoricoDTO> historico = new ArrayList<PartidaHistoricoDTO>();
		if (!matchList.getMatches().equals(null)) {
			for (int index = 0; index < 10; index++) {
				Match partida = riotApiService.getInformacoesPartida(matchList.getMatches().get(index).getGameId());
				Participant dadosPlayer = partida.getParticipantByAccountId(accountId);
				ParticipantStats dadosPartida = dadosPlayer.getStats();
				partidaDTO.setResultado(dadosPartida.isWin());
				partidaDTO.setMultiKill(dadosPartida.getLargestMultiKill());
				partidaDTO.setDuracao(Math.round(partida.getGameDuration() / 60));
				partidaDTO.setGameType(partida.getGameType());
				partidaDTO.setNomeCampeao(dadosPlayer.getChampionId());

				Integer kills = dadosPartida.getKills();
				Integer deaths = dadosPartida.getDeaths();
				Integer assists = dadosPartida.getAssists();

				partidaDTO.setKda(kills.toString() + "/" + deaths.toString() + "/" + assists.toString());
				partidaDTO.setScoreVisao(dadosPartida.getVisionScore());
				partidaDTO.setFarm(dadosPartida.getTotalMinionsKilled());
				partidaDTO.setGold(dadosPartida.getGoldEarned());
				partidaDTO.setWards(dadosPartida.getWardsPlaced());

				List<ItemDTO> itens = new ArrayList<ItemDTO>();
				for (int indexBuild = 0; indexBuild < 6; indexBuild++) {
					itens.add(new ItemDTO(getItem(index, dadosPartida),
							dataDragonService.getItemIconUrl(getItem(index, dadosPartida))));
				}

				partidaDTO.setBuild(itens);

				List<SpellDTO> spells = new ArrayList<SpellDTO>();
				spells.add(new SpellDTO(dadosPlayer.getSpell1Id(), dataDragonService.getSpellIconUrl("ignite")));
				spells.add(new SpellDTO(dadosPlayer.getSpell2Id(), dataDragonService.getSpellIconUrl("ignite")));

				partidaDTO.setSpells(spells);
				partidaDTO.setId(partida.getGameId());

				historico.add(partidaDTO);
			}
		}

		return historico;
	}

	private Integer getItem(Integer index, ParticipantStats dadosPartida) {
		switch (index) {
		case 1:
			return dadosPartida.getItem1();
		case 2:
			return dadosPartida.getItem2();
		case 3:
			return dadosPartida.getItem3();
		case 4:
			return dadosPartida.getItem4();
		case 5:
			return dadosPartida.getItem5();
		case 6:
			return dadosPartida.getItem6();
		default:
			return dadosPartida.getItem0();
		}
	}

	public MatchList getPartidasJogadas(String accountId) {
		return riotApiService.getPartidasJogadas(accountId);
	}

	public Integer savePartidas(List<MatchReference> partidas, List<Partida> partidasSalvas, String accountId,
			StatusPartida statusPartida) {
		Invocador invocador = invocadorService.getInvocadorByAccountId(accountId);
		Integer contador = 0;
		ObjectMapper jsonParser = new ObjectMapper();
		for (MatchReference partida : partidas) {
			if (!partidasSalvas.get(contador).equals(partida.getGameId())) {
				Match partidaAPI = riotApiService.getInformacoesPartida(partida.getGameId());
				logger.info("salvando partida, id da partida riot: " + partida.getGameId());
				PartidaDTO partidaDTO = new PartidaDTO();
				partidaDTO.setAccountId(invocador);
				partidaDTO.setDataCriacao(new Date());
				partidaDTO.setIdPartidaRiot(partida.getGameId());
				partidaDTO.setStatusPartida(statusPartida);
				logger.info("criando json da partida");
				try {
					partidaDTO.setJsonReferenciaPartida(jsonParser.writeValueAsString(partida));
					partidaDTO.setJsonPartida(jsonParser.writeValueAsString(partidaAPI));
				} catch (JsonProcessingException e) {
					logger.error("ERRO na linha 153 - falha ao criar json da partida: {}",
							e.getStackTrace().toString());
					e.printStackTrace();
				}
				logger.info("json criado");
				Partida partidaEntity = mapper.map(partidaDTO, Partida.class);
				partidaEntity = partidaRepository.save(partidaEntity);
				logger.info("partida salva: id da Partida" + partidaEntity.getIdPartida() + " id da partida riot: "
						+ partidaEntity.getIdPartidaRiot());
				contador++;
			} else {
				break;
			}
		}
		logger.info("Total de novas partidas: {}", contador);
		return contador;
	}

	public void ajustarRegistrosHistorico(Integer qtdParaDesabilitar, String accountId, StatusPartida statusPartida) {
		List<Partida> partidas = partidaRepository.getPartidasOrderByDesc(accountId);
		Partida partida = null;
		logger.info("Total de partidas retornadas: {}", partidas.size());
		logger.info("Total de partidas a serem desabilitadas do historico: {}", qtdParaDesabilitar);
		for (Integer indice = 0; indice < qtdParaDesabilitar; indice++) {
			try {
				partida = partidas.get(indice);
				partida.setStatusPartida(statusPartida);
				partidaRepository.save(partida);
				logger.info("Partida de ID {} desabilitada.", qtdParaDesabilitar);
			} catch (Exception e) {
				logger.error("ERRO A LINHA 182: {}", e.getStackTrace().toString());
			}
		}
		logger.info("Historico Ajustado.");
	}

	public StatusPartida getStatusPartida(Integer status) {
		return statusPartidaRepository.findById(status).orElse(null);
	}

	public List<Partida> getPartidasByStatus(Integer status) {
		return partidaRepository.getPartidasByStatus(status);
	}

	public void updatePartida(Partida partida) {
		partidaRepository.save(partida);
	}
}

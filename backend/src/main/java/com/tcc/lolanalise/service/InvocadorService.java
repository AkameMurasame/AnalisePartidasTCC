package com.tcc.lolanalise.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.tcc.lolanalise.domain.Invocador;
import com.tcc.lolanalise.dto.ItemDTO;
import com.tcc.lolanalise.dto.PartidaDTO;
import com.tcc.lolanalise.dto.SpellDTO;
import com.tcc.lolanalise.repository.InvocadorRepository;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import net.rithms.riot.api.endpoints.match.dto.Match;
import net.rithms.riot.api.endpoints.match.dto.MatchList;
import net.rithms.riot.api.endpoints.match.dto.Participant;
import net.rithms.riot.api.endpoints.match.dto.ParticipantStats;
import net.rithms.riot.api.endpoints.summoner.dto.Summoner;

@Component
@RequiredArgsConstructor
public class InvocadorService {

	@NonNull
	private RiotApiService riotApiService;

	@NonNull
	private InvocadorRepository invocadorRepository;

	public Summoner validarInvocador(String nickname) {
		return riotApiService.getInvocadorByNickName(nickname);
	}

	public Summoner getInvocador(String nickname) {
		return riotApiService.getInvocadorByNickName(nickname);
	}

	public Invocador saveInvocador(Invocador invocador) {
		return invocadorRepository.save(invocador);
	}

	public List<PartidaDTO> getHistorico(String accountId) {
		MatchList matchList = this.getPartidasJogadas(accountId);
		PartidaDTO partidaDTO = new PartidaDTO();
		List<PartidaDTO> historico = new ArrayList<PartidaDTO>();
		if (!matchList.getMatches().equals(null)) {
			for (int index = 0; index < 20; index++) {
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
					itens.add(new ItemDTO(getItem(index, dadosPartida), "url"));
				}

				partidaDTO.setBuild(itens);

				List<SpellDTO> spells = new ArrayList<SpellDTO>();
				spells.add(new SpellDTO(dadosPlayer.getSpell1Id(), "url"));
				spells.add(new SpellDTO(dadosPlayer.getSpell2Id(), "url"));

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

	private MatchList getPartidasJogadas(String accountId) {
		return riotApiService.getPartidasJogadas(accountId);
	}
}

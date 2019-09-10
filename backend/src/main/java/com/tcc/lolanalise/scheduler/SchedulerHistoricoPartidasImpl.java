package com.tcc.lolanalise.scheduler;

import java.util.List;

import org.springframework.stereotype.Component;

import com.tcc.lolanalise.domain.Invocador;
import com.tcc.lolanalise.domain.Partida;
import com.tcc.lolanalise.repository.PartidaRepository;
import com.tcc.lolanalise.service.InvocadorService;

import net.rithms.riot.api.endpoints.match.dto.MatchList;

@Component
public class SchedulerHistoricoPartidasImpl {

	private InvocadorService invocadorService;

	private PartidaRepository partidaRepository;

	public void schedulerHistoricoPartidas() {
		List<Invocador> invocadores = invocadorService.getInvocadoresToScheduler();
		invocadores.forEach(invocador -> {
			MatchList historico = invocadorService.getPartidasJogadas(invocador.getAccountId());
			List<Partida> partidas = partidaRepository.getUltimaPartidaRegistrada(invocador.getAccountId());

			if(historico.getMatches().get(0).getGameId() != partidas.get(0).getIdPartidaRiot()) {
				Integer qtdPartidasSalvas = invocadorService.savePartidas(historico.getMatches(),partidas, invocador.getAccountId());
			}
		});
	}
}

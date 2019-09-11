package com.tcc.lolanalise.scheduler;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tcc.lolanalise.domain.Invocador;
import com.tcc.lolanalise.domain.Partida;
import com.tcc.lolanalise.domain.StatusPartida;
import com.tcc.lolanalise.enums.StatusPartidaEnum;
import com.tcc.lolanalise.service.InvocadorService;
import com.tcc.lolanalise.service.PartidasService;

import net.rithms.riot.api.endpoints.match.dto.MatchList;

@Component
public class SchedulerHistoricoPartidasImpl {

	@Autowired
	private InvocadorService invocadorService;

	@Autowired
	private PartidasService partidasService;

	private static final Logger logger = LoggerFactory.getLogger(SchedulerHistoricoPartidasImpl.class);

	public void schedulerHistoricoPartidas() {
		List<Invocador> invocadores = invocadorService.getInvocadoresToScheduler();
		StatusPartida statusPronto = partidasService.getStatusPartida(StatusPartidaEnum.PRONTA.getStatus());
		StatusPartida statusRemovida = partidasService.getStatusPartida(StatusPartidaEnum.REMOVIDA.getStatus());
		invocadores.forEach(invocador -> {
			logger.info("Pesquisando partidas do invocador: {}", invocador.getAccountId());
			MatchList historico = partidasService.getPartidasJogadas(invocador.getAccountId());
			List<Partida> partidas = partidasService.getHistoricoToScheduler(invocador.getAccountId());

			if(historico.getMatches().get(0).getGameId() != partidas.get(0).getIdPartidaRiot()) {
				logger.info("Salvando novas Partidas");
				Integer qtdPartidasSalvas = partidasService.savePartidas(historico.getMatches(),partidas, invocador.getAccountId(), statusPronto);
				logger.info("Desabilitando partidas anteriores");
				partidasService.ajustarRegistrosHistorico(qtdPartidasSalvas, invocador.getAccountId(), statusRemovida);
			} else {
				logger.info("Não a novas partidas");
			}
			logger.info("Historico do invocador: " + invocador.getAccountId() + " atualizado");
		});
	}
}

package com.tcc.lolanalise.scheduler;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tcc.lolanalise.domain.Partida;
import com.tcc.lolanalise.domain.StatusPartida;
import com.tcc.lolanalise.enums.StatusPartidaEnum;
import com.tcc.lolanalise.service.InvocadorService;
import com.tcc.lolanalise.service.PartidasService;

@Component
public class SchedulerAnaliseImpl {

	@Autowired
	private InvocadorService invocadorService;

	@Autowired
	private PartidasService partidasService;

	private static final Logger logger = LoggerFactory.getLogger(SchedulerAnaliseImpl.class);

	public void SchedulerAtualizaPartidasProntas() {
		StatusPartida statusPartida = partidasService.getStatusPartida(StatusPartidaEnum.EM_ANALISE.getStatus());
		List<Partida> partidasProntas = partidasService.getPartidasByStatus(StatusPartidaEnum.PRONTA.getStatus());
		partidasProntas.forEach(partida -> {
			partida.setStatusPartida(statusPartida);
			partidasService.updatePartida(partida);
		});
		logger.info("Partidas atualizadas para a analise: {}", partidasProntas.size());
	}

	public void SchedulerAnaliseDePartidas() {
		List<Partida> partidasAnalise = partidasService.getPartidasByStatus(StatusPartidaEnum.EM_ANALISE.getStatus());
		partidasAnalise.forEach(partida -> {
			
		});
	}
}

package com.tcc.lolanalise.scheduler;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {

	private static final Logger logger = LoggerFactory.getLogger(ScheduledTasks.class);
	private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

	@Autowired
	private SchedulerHistoricoPartidasImpl historicoSchedulerImpl;

	@Autowired
	private SchedulerAnaliseImpl analiseChedulerImpl;

	@Scheduled(fixedRate = 200000, initialDelay = 10000)
	public void scheduleHistoricoDePartidas() {
		logger.info("Historico de Partidas :: Processando em: - {}", dateTimeFormatter.format(LocalDateTime.now()));
		logger.info("Current Thread : {}", Thread.currentThread().getName());
		historicoSchedulerImpl.schedulerHistoricoPartidas();
	}

	@Scheduled(fixedRate = 500000, initialDelay = 50000)
	public void scheduleAnaliseDePartidas() {
		logger.info("Analise de Partidas :: Processando em: - {}", dateTimeFormatter.format(LocalDateTime.now()));
		logger.info("Current Thread : {}", Thread.currentThread().getName());
		analiseChedulerImpl.SchedulerAnaliseDePartidas();
	}

	@Scheduled(fixedRate = 500000, initialDelay = 10000)
	public void scheduleAtualizaStatusPartidas() {
		logger.info("Atualiza Status de Partidas :: Processando em: - {}", dateTimeFormatter.format(LocalDateTime.now()));
		logger.info("Current Thread : {}", Thread.currentThread().getName());
		analiseChedulerImpl.SchedulerAtualizaPartidasProntas();
	}
}

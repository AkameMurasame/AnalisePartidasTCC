package com.tcc.lolanalise.scheduler;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {

	private static final Logger logger = LoggerFactory.getLogger(ScheduledTasks.class);
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

    private SchedulerHistoricoPartidasImpl historicoScheduler;

    @Scheduled(fixedRate = 200000, initialDelay = 10000)
    public void scheduleHistoricoDePartidas() {
    	logger.info("Current Thread : {}", Thread.currentThread().getName());
    	logger.info("Historico de Partidas :: Processando em: - {}", dateTimeFormatter.format(LocalDateTime.now()));
    	historicoScheduler.schedulerHistoricoPartidas();
    }

	@Scheduled(fixedRate = 500000, initialDelay = 50000)
	public void scheduleAnaliseDePartidas() {
		logger.info("Current Thread : {}", Thread.currentThread().getName());
		 logger.info("Analise de Partidas :: Processando em: - {}", dateTimeFormatter.format(LocalDateTime.now()));
	}

}

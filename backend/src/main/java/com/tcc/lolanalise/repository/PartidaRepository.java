package com.tcc.lolanalise.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tcc.lolanalise.domain.Partida;

public interface PartidaRepository extends JpaRepository<Partida, Integer>{

	@Query("SELECT p from Partida p where p.accountId = :accountId")
	public List<Partida> getHistorico(String accountId);

	@Query("SELECT p Partida p where p.accountId = :accountId orderBy DESC")
	public List<Partida> getPartidasOrderByDesc(String accountId);

	@Query("SELECT p from Partida  p JOIN FETCH StatusPartida sp where p.statusPartida.id = :status")
	public List<Partida> getPartidasByStatus(Integer status);
}

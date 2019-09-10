package com.tcc.lolanalise.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tcc.lolanalise.domain.Partida;

public interface PartidaRepository extends JpaRepository<Partida, Integer>{

	@Query("SELECT Partida as pa where pa.accountId = :accountId")
	public List<Partida> getUltimaPartidaRegistrada(String accountId);
}

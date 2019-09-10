package com.tcc.lolanalise.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tcc.lolanalise.domain.Invocador;

public interface InvocadorRepository extends JpaRepository<Invocador, String>{

	@Query("SELECT Invocador as in where in.attHistoricoScheduler < :attScheduler and in.attHistoricoManual < :attManual")
	public List<Invocador> getInvocadoresToScheduler(String attScheduler, String attManual);

	@Query("SELECT i FROM Invocador i WHERE i.accountId = :accountId")
	public Invocador getInvocadorByAccountId(String accountId);
}

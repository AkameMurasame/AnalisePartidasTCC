package com.tcc.lolanalise.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tcc.lolanalise.domain.PartidasProPlayer;
import com.tcc.lolanalise.domain.ProPlayer;

public interface ProPlayerRepository extends JpaRepository<ProPlayer, String>{

	@Query("SELECT ppp FROM PartidasProPlayer ppp "
		 + "JOIN FETCH ProPlayer pp "
		 + "JOIN FETCH Role r "
		 + "WHERE r.role = :role or ppp.championId = :champion")
	public List<PartidasProPlayer> getPartidasProPlayerAnalise(String role, Long champion);
}

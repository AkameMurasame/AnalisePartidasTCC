package com.tcc.lolanalise.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.tcc.lolanalise.domain.ProPlayer;
import com.tcc.lolanalise.repository.ProPlayerRepository;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ProPlayerService {

	@NonNull
	private ProPlayerRepository proPlayerRepository;

	public List<ProPlayer> getProPlayers() {
		return proPlayerRepository.findAll();
	}

	public void saveProPlayer(ProPlayer proPlayer) {
		proPlayerRepository.save(proPlayer);
	}
}

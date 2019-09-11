package com.tcc.lolanalise.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.tcc.lolanalise.domain.Regiao;
import com.tcc.lolanalise.repository.RegiaoRepository;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class RegiaoService {

	@NonNull
	private RegiaoRepository regiaoRepository;

	public List<Regiao> getRegioes() {
		return regiaoRepository.findAll();
	}

	public void saveRegiao(Regiao regiao) {
		regiaoRepository.save(regiao);
	}
}

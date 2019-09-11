package com.tcc.lolanalise.as;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tcc.lolanalise.comum.ConverterMapper;
import com.tcc.lolanalise.domain.ProPlayer;
import com.tcc.lolanalise.domain.Regiao;
import com.tcc.lolanalise.dto.ProPlayerDTO;
import com.tcc.lolanalise.dto.RegiaoDTO;
import com.tcc.lolanalise.service.ProPlayerService;
import com.tcc.lolanalise.service.RegiaoService;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdministradorAS {

	@NonNull
	private ConverterMapper mapper;

	@NonNull
	private ProPlayerService proPlayerService;

	@NonNull
	private RegiaoService regiaoService;

	public List<ProPlayerDTO> getProplayers() {
		return mapper.mapAsList(proPlayerService.getProPlayers(), ProPlayerDTO.class);
	}

	public void saveProPlayer(ProPlayerDTO proPlayer) {
		proPlayerService.saveProPlayer(mapper.map(proPlayer, ProPlayer.class));
	}

	public List<RegiaoDTO> getRegioes() {
		return mapper.mapAsList(regiaoService.getRegioes(), RegiaoDTO.class);
	}

	public void saveRegiao(RegiaoDTO regiao) {
		regiaoService.saveRegiao(mapper.map(regiao, Regiao.class));
	}
}

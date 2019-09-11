package com.tcc.lolanalise.api;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tcc.lolanalise.as.AdministradorAS;
import com.tcc.lolanalise.dto.ProPlayerDTO;
import com.tcc.lolanalise.dto.RegiaoDTO;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/admin")
public class AdministradorAPI {

	@NonNull
	private AdministradorAS administradorAS;


	@GetMapping("/pro-player")
	public List<ProPlayerDTO> getProPlayers() {
		return administradorAS.getProplayers();
	}

	@PostMapping("/pro-player")
	public void saveProPlayer(@RequestBody ProPlayerDTO proPlayer) {
		administradorAS.saveProPlayer(proPlayer);
	}

	@GetMapping("/regiao")
	public List<RegiaoDTO> getRegioes() {
		return administradorAS.getRegioes();
	}

	@PostMapping("/regiao")
	public void saveRegiao(@RequestBody RegiaoDTO regiao) {
		administradorAS.saveRegiao(regiao);
	}
}

package com.tcc.lolanalise.api;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tcc.lolanalise.as.InvocadorAS;
import com.tcc.lolanalise.dto.InvocadorDTO;
import com.tcc.lolanalise.dto.PartidaDTO;
import com.tcc.lolanalise.security.CurrentUser;
import com.tcc.lolanalise.security.UserPrincipal;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/invocador")
public class InvocadorAPI {

	@NonNull
	private InvocadorAS invocadorAS;

	@GetMapping("/getBy/nickname/{nickname}")
	public InvocadorDTO getInvocadorByNickname(@PathVariable String nickname) {
		return invocadorAS.getInvocadorByNickname(nickname);
	}

	@PostMapping("/save")
	public InvocadorDTO saveInvocador(@RequestBody InvocadorDTO invocador, @CurrentUser UserPrincipal currentUser) {
		return invocadorAS.saveInvocador(invocador, currentUser);
	}

	@GetMapping("/historico/{accountID}")
	public List<PartidaDTO> getHistorico(@CurrentUser UserPrincipal usuario, @PathVariable String accountID) {
		return invocadorAS.getHistorico(accountID);
	}
}

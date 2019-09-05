package com.tcc.lolanalise.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tcc.lolanalise.as.InvocadorAS;
import com.tcc.lolanalise.dto.InvocadorDTO;
import com.tcc.lolanalise.security.CurrentUser;
import com.tcc.lolanalise.security.UserPrincipal;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import net.rithms.riot.api.RiotApiException;

@RequiredArgsConstructor
@RestController
@RequestMapping("/invocador")
public class InvocadorAPI {

	@NonNull
	private InvocadorAS invocadorAS;
	
	@GetMapping("/getBy/nickname/{nickname}")
	public InvocadorDTO getInvocadorByNickname(@PathVariable String nickname) throws RiotApiException {
		return invocadorAS.getInvocadorByNickname(nickname);
	}
	
	@PostMapping("/save")
	public InvocadorDTO saveInvocador(@RequestBody InvocadorDTO invocador, @CurrentUser UserPrincipal currentUser) throws RiotApiException {	
		return invocadorAS.saveInvocador(invocador, currentUser);
	}
	
	@GetMapping("/historico")
	public void getHistorico(@CurrentUser UserPrincipal usuario) {
		
	}
}
	
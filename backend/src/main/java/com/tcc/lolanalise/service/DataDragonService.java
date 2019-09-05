package com.tcc.lolanalise.service;

import org.springframework.stereotype.Component;

@Component
public class DataDragonService {

	private String SET_ENDPOINT = "https://ddragon.leagueoflegends.com/cdn/";
	private String version;

	public DataDragonService() {
		this.SET_ENDPOINT = "https://ddragon.leagueoflegends.com/cdn/";
		this.version = "8.4.1";
	}

	public String getItemIconUrl(int itemId) {
		return this.SET_ENDPOINT + this.version + "/img/item/" + itemId + ".png";
	}

	public String getSpellIconUrl(String spellName) {
		return this.SET_ENDPOINT + this.version + "/img/spell/" + spellName + ".png";
	}

	public String getChampionIconUrl(String championName) {
		return this.SET_ENDPOINT + this.version + "/img/champion/" + championName + ".png";
	}

	public String getProfileIconUrl(Integer iconeId) {
		return this.SET_ENDPOINT + this.version + "/img/profileicon/" + iconeId + ".png";
	}
}

package com.tcc.lolanalise;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class LolanaliseApplication {

	public static void main(String[] args) {
		SpringApplication.run(LolanaliseApplication.class, args);
	}
}

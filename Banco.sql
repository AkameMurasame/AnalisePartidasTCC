CREATE TABLE regiao(
id INT UNSIGNED AUTO_INCREMENT NOT NULL,
regiao CHAR(2) NOT NULL,
PRIMARY KEY (id)
);

CREATE TABLE role(
id INT UNSIGNED AUTO_INCREMENT NOT NULL,
role VARCHAR(10),
PRIMARY KEY (id)
);

CREATE TABLE `usuario` (
	`id` INT(11) NOT NULL AUTO_INCREMENT,
	`usuario` VARCHAR(100) NOT NULL,
	`senha` VARCHAR(100) NOT NULL,
	`email` VARCHAR(100) NOT NULL,
	`valido` CHAR(10) NULL DEFAULT 'N',
	PRIMARY KEY (`id`),
	UNIQUE INDEX `usuario` (`usuario`),
	UNIQUE INDEX `senha` (`senha`),
	UNIQUE INDEX `email` (`email`)
)
COLLATE='latin1_swedish_ci'
ENGINE=InnoDB
AUTO_INCREMENT=9
;


CREATE TABLE `permicao` (
	`id` INT(11) NOT NULL AUTO_INCREMENT,
	`nome` VARCHAR(30) NOT NULL,
	PRIMARY KEY (`id`)
)
COLLATE='latin1_swedish_ci'
ENGINE=InnoDB
;

CREATE TABLE `permicoes_usuario` (
	`id_usuario` INT(11) NOT NULL,
	`id_permicao` INT(11) NOT NULL,
	INDEX `id_usuario` (`id_usuario`),
	INDEX `id_permicao` (`id_permicao`),
	CONSTRAINT `permicoes_usuario_ibfk_1` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id`),
	CONSTRAINT `permicoes_usuario_ibfk_2` FOREIGN KEY (`id_permicao`) REFERENCES `permicao` (`id`)
)
COLLATE='latin1_swedish_ci'
ENGINE=InnoDB
;


CREATE TABLE `invocador` (
	`accountId` VARCHAR(40) NOT NULL,
	`id_usuario` INT(11) NOT NULL,
	`attHistoricoManual` TIME NULL DEFAULT NULL,
	`attHistoricoScheduler` TIME NULL DEFAULT NULL,
	PRIMARY KEY (`accountId`),
	UNIQUE INDEX `accountId` (`accountId`),
	UNIQUE INDEX `UK_94fk6ditwyoqfc4grrp216btb` (`id_usuario`),
	CONSTRAINT `invocador_ibfk_1` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id`)
)
COLLATE='latin1_swedish_ci'
ENGINE=InnoDB
;

CREATE TABLE `status_partida` (
	`id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
	`status` VARCHAR(30) NOT NULL,
	PRIMARY KEY (`id`)
)
COLLATE='latin1_swedish_ci'
ENGINE=InnoDB
;


CREATE TABLE `partida` (
	`idPartida` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
	`jsonPartida` TEXT NOT NULL,
	`dataCriacao` DATETIME NOT NULL,
	`accountId` VARCHAR(40) NOT NULL,
	`idPartidaRiot` INT(11) NULL DEFAULT NULL,
	`jsonReferenciaPartida` LONGTEXT NOT NULL,
	`statusPartida` INT(10) UNSIGNED NOT NULL,
	PRIMARY KEY (`idPartida`),
	INDEX `accountId` (`accountId`),
	INDEX `status_partida` (`statusPartida`),
	CONSTRAINT `partida_ibfk_1` FOREIGN KEY (`accountId`) REFERENCES `invocador` (`accountId`),
	CONSTRAINT `partida_ibfk_2` FOREIGN KEY (`statusPartida`) REFERENCES `status_partida` (`id`)
)
COLLATE='latin1_swedish_ci'
ENGINE=InnoDB
;


CREATE TABLE pro_player(
accountId VARCHAR(30) NOT NULL,
nome VARCHAR(30),
nick VARCHAR(30),
regiaoId INT UNSIGNED NOT null,
mainRole INT UNSIGNED NOT null,
PRIMARY KEY (accountId),
FOREIGN KEY (regiaoId) REFERENCES regiao(id),
FOREIGN KEY (mainRole) REFERENCES role(id)
);

CREATE TABLE `partidas_pro_player` (
	`id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
	`championId` BIGINT(20) UNSIGNED NOT NULL DEFAULT 0,
	`idPartidaRiot` BIGINT(20) NOT NULL,
	`jsonPartida` LONGTEXT NOT NULL,
	`dataCriacao` DATETIME NOT NULL,
	`accountId` VARCHAR(30) NOT NULL,
	`jsonReferenciaPartida` LONGTEXT NOT NULL,
	PRIMARY KEY (`id`),
	INDEX `accountId` (`accountId`),
	CONSTRAINT `partidas_pro_player_ibfk_1` FOREIGN KEY (`accountId`) REFERENCES `pro_player` (`accountId`)
)
COLLATE='latin1_swedish_ci'
ENGINE=InnoDB
;

SELECT * FROM partidas_pro_player ppp 
INNER JOIN pro_player  pp ON ppp.accountId = pp.accountId
INNER JOIN role r ON pp.mainRole = r.id
WHERE r.role = "" OR champion
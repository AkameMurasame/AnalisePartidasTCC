package com.tcc.lolanalise.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "invocador")
public class Invocador extends BaseEntity<String> {
	private static final long serialVersionUID = 1L;
	
	@Column(name = "accountId", nullable = false)
	private String accountId;
	
	@Id
	@Column(name = "id", nullable = false)
	private String id;
	
	@Column(name = "puuid", nullable = false)
	private String puuid;
	
	@OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
}

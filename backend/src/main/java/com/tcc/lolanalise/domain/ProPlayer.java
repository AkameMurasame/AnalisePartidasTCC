package com.tcc.lolanalise.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "pro_player")
@NamedQueries({
    @NamedQuery(name = "ProPlayer.findAll", query = "SELECT p FROM ProPlayer p"),
    @NamedQuery(name = "ProPlayer.findByAccountId", query = "SELECT p FROM ProPlayer p WHERE p.accountId = :accountId"),
    @NamedQuery(name = "ProPlayer.findByNome", query = "SELECT p FROM ProPlayer p WHERE p.nome = :nome"),
    @NamedQuery(name = "ProPlayer.findByNick", query = "SELECT p FROM ProPlayer p WHERE p.nick = :nick")})
public class ProPlayer implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
    @Basic(optional = false)
    @Column(name = "accountId")
    private String accountId;

	@Column(name = "nome")
    private String nome;

	@Column(name = "nick")
    private String nick;

	@JoinColumn(name = "regiaoId", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Regiao regiaoId;

	@JoinColumn(name = "mainRole", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Role mainRole;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "accountId")
    private List<PartidasProPlayer> partidasProPlayer;
}
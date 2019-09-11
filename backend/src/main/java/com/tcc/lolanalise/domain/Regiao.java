package com.tcc.lolanalise.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "regiao")
@NamedQueries({
    @NamedQuery(name = "Regiao.findAll", query = "SELECT r FROM Regiao r"),
    @NamedQuery(name = "Regiao.findById", query = "SELECT r FROM Regiao r WHERE r.id = :id"),
    @NamedQuery(name = "Regiao.findByRegiao", query = "SELECT r FROM Regiao r WHERE r.regiao = :regiao")})
public class Regiao implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;

	@Basic(optional = false)
    @Column(name = "regiao")
    private String regiao;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "regiaoId")
    private List<ProPlayer> proPlayer;
}

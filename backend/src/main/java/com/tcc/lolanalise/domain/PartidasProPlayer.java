package com.tcc.lolanalise.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "partidas_pro_player")
@NamedQueries({
    @NamedQuery(name = "PartidasProPlayer.findAll", query = "SELECT p FROM PartidasProPlayer p"),
    @NamedQuery(name = "PartidasProPlayer.findById", query = "SELECT p FROM PartidasProPlayer p WHERE p.id = :id"),
    @NamedQuery(name = "PartidasProPlayer.findByIdPartidaRiot", query = "SELECT p FROM PartidasProPlayer p WHERE p.idPartidaRiot = :idPartidaRiot"),
    @NamedQuery(name = "PartidasProPlayer.findByDataCriacao", query = "SELECT p FROM PartidasProPlayer p WHERE p.dataCriacao = :dataCriacao")})
public class PartidasProPlayer implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;

	@Basic(optional = false)
    @Column(name = "idPartidaRiot")
    private long idPartidaRiot;

	@Basic(optional = false)
    @Lob
    @Column(name = "jsonPartida")
    private String jsonPartida;

	@Basic(optional = false)
    @Column(name = "dataCriacao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataCriacao;

	@Basic(optional = false)
    @Lob
    @Column(name = "jsonReferenciaPartida")
    private String jsonReferenciaPartida;

	@JoinColumn(name = "accountId", referencedColumnName = "accountId")
    @ManyToOne(optional = false)
    private ProPlayer accountId;
}

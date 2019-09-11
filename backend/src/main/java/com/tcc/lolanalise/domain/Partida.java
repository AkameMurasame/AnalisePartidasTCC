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
@Table(name = "partida")
@NamedQueries({
    @NamedQuery(name = "Partida.findAll", query = "SELECT p FROM Partida p"),
    @NamedQuery(name = "Partida.findByIdPartida", query = "SELECT p FROM Partida p WHERE p.idPartida = :idPartida"),
    @NamedQuery(name = "Partida.findByDataCriacao", query = "SELECT p FROM Partida p WHERE p.dataCriacao = :dataCriacao")})
public class Partida implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idPartida")
    private Integer idPartida;

    @Basic(optional = false)
    @Column(name = "idPartidaRiot")
    private Long idPartidaRiot;

    @Basic(optional = false)
    @Lob
    @Column(name = "jsonPartida")
    private String jsonPartida;

    @Basic(optional = false)
    @Lob
    @Column(name = "jsonReferenciaPartida")
    private String JsonReferenciaPartida;

    @Basic(optional = false)
    @Column(name = "dataCriacao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataCriacao;

    @JoinColumn(name = "accountId", referencedColumnName = "accountId")
    @ManyToOne(optional = false)
    private Invocador accountId;

    @Column(name = "statusPartida")
    private StatusPartida statusPartida;
}

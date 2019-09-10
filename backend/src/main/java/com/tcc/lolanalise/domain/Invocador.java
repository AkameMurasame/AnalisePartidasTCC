package com.tcc.lolanalise.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
@Table(name = "invocador")
@NamedQueries({
    @NamedQuery(name = "Invocador.findAll", query = "SELECT i FROM Invocador i"),
    @NamedQuery(name = "Invocador.findByAccountId", query = "SELECT i FROM Invocador i WHERE i.accountId = :accountId"),
    @NamedQuery(name = "Invocador.findByAttHistoricoManual", query = "SELECT i FROM Invocador i WHERE i.attHistoricoManual = :attHistoricoManual"),
    @NamedQuery(name = "Invocador.findByAttHistoricoScheduler", query = "SELECT i FROM Invocador i WHERE i.attHistoricoScheduler = :attHistoricoScheduler")})
public class Invocador implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @Column(name = "accountId")
    private String accountId;

    @Column(name = "attHistoricoManual")
    @Temporal(TemporalType.TIME)
    private Date attHistoricoManual;

    @Column(name = "attHistoricoScheduler")
    @Temporal(TemporalType.TIME)
    private Date attHistoricoScheduler;

    @JoinColumn(name = "id_usuario", referencedColumnName = "id")
    @OneToOne(optional = false)
    private Usuario idUsuario;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "accountId")
    private List<Partida> partidas;
}

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
@Table(name = "status_partida")
@NamedQueries({
    @NamedQuery(name = "StatusPartida.findAll", query = "SELECT s FROM StatusPartida s"),
    @NamedQuery(name = "StatusPartida.findById", query = "SELECT s FROM StatusPartida s WHERE s.id = :id"),
    @NamedQuery(name = "StatusPartida.findByStatus", query = "SELECT s FROM StatusPartida s WHERE s.status = :status")})
public class StatusPartida implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;

    @Basic(optional = false)
    @Column(name = "status")
    private String status;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "statusPartida")
    private List<Partida> partidaCollection;
}

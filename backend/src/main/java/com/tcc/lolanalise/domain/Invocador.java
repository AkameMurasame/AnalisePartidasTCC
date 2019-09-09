package com.tcc.lolanalise.domain;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "invocador")
@Getter
@Setter
@NoArgsConstructor
@NamedQueries({
    @NamedQuery(name = "Invocador.findAll", query = "SELECT i FROM Invocador i"),
    @NamedQuery(name = "Invocador.findByAccountId", query = "SELECT i FROM Invocador i WHERE i.accountId = :accountId")})
public class Invocador implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "accountId")
    private String accountId;
    @JoinColumn(name = "id_usuario", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Usuario usuario;

}

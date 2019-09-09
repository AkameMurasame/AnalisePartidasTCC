package com.tcc.lolanalise.domain;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "permicoes")
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@NamedQueries({
    @NamedQuery(name = "Permicoes.findAll", query = "SELECT p FROM Permicoes p"),
    @NamedQuery(name = "Permicoes.findById", query = "SELECT p FROM Permicoes p WHERE p.id = :id"),
    @NamedQuery(name = "Permicoes.findByNome", query = "SELECT p FROM Permicoes p WHERE p.nome = :nome")})
public class Permicoes implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "nome")
    private String nome;
    @ManyToMany(mappedBy = "permicoesCollection")
    private Collection<Usuario> usuariosCollection;

}

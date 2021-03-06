package com.tcc.lolanalise.domain;

import java.io.Serializable;
import java.util.List;

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

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "permicao")
@NamedQueries({
    @NamedQuery(name = "Permicao.findAll", query = "SELECT p FROM Permicao p"),
    @NamedQuery(name = "Permicao.findById", query = "SELECT p FROM Permicao p WHERE p.id = :id"),
    @NamedQuery(name = "Permicao.findByNome", query = "SELECT p FROM Permicao p WHERE p.nome = :nome")})
public class Permicao implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;

    @Basic(optional = false)
    @Column(name = "nome")
    private String nome;

    @ManyToMany(mappedBy = "permicoes")
    private List<Usuario> usuarios;
}

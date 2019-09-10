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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "usuario")
@NamedQueries({ @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u"),
		@NamedQuery(name = "Usuario.findById", query = "SELECT u FROM Usuario u WHERE u.id = :id"),
		@NamedQuery(name = "Usuario.findByUsuario", query = "SELECT u FROM Usuario u WHERE u.username = :username"),
		@NamedQuery(name = "Usuario.findBySenha", query = "SELECT u FROM Usuario u WHERE u.password = :password"),
		@NamedQuery(name = "Usuario.findByEmail", query = "SELECT u FROM Usuario u WHERE u.email = :email"),
		@NamedQuery(name = "Usuario.findByValido", query = "SELECT u FROM Usuario u WHERE u.valido = :valido") })
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "id")
	private Integer id;

	@Basic(optional = false)
	@Column(name = "usuario")
	private String username;

	@Basic(optional = false)
	@Column(name = "senha")
	private String password;

	@Basic(optional = false)
	@Column(name = "email")
	private String email;

	@Column(name = "valido")
	private String valido;

	@JoinTable(name = "permicoes_usuario", joinColumns = {
			@JoinColumn(name = "id_usuario", referencedColumnName = "id") }, inverseJoinColumns = {
					@JoinColumn(name = "id_permicao", referencedColumnName = "id") })
	@ManyToMany
	private List<Permicao> permicoes;

	@OneToOne(cascade = CascadeType.ALL, mappedBy = "idUsuario")
	private Invocador invocador;
}

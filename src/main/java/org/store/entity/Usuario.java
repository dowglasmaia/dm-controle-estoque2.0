package org.store.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer matricula;

	@Column(length = 11, unique = true, nullable = false)
	private String cpf;

	@Column(length = 80, nullable = false)
	private String nome;

	@Column(length = 80)
	private String email;

	@Column(length = 80, nullable = false)
	private String senha;

	/* Criando o mapeamento das permissões com o Usuario, sem a necessidade de criar a class UsuarioPermissao */
	@ElementCollection(targetClass = String.class)
	@JoinTable(name = "usuario_permissao", uniqueConstraints = {
			@UniqueConstraint(columnNames = { "usuario", "permissao" }) }, joinColumns = @JoinColumn(name = "usuario"))
	@Column(name = "permissao", length = 50)
	private Set<String> permisao = new HashSet<String>();

	public Integer getMatricula() {
		return matricula;
	}

	public void setMatricula(Integer matricula) {
		this.matricula = matricula;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cpf == null) ? 0 : cpf.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (cpf == null) {
			if (other.cpf != null)
				return false;
		} else if (!cpf.equals(other.cpf))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return String.format("%s[matricula=%d]", getClass().getSimpleName(), getMatricula());
	}

}

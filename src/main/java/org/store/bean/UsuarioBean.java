package org.store.bean;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;

import org.omnifaces.util.Messages;
import org.store.dao.UsuarioDAO;
import org.store.entity.Usuario;

@Model
@ViewScoped
public class UsuarioBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@EJB
	private UsuarioDAO uDao;

	@Inject
	private Usuario usuario;

	private String cpf;

	private String senha;

	// Logar
	public String logar() {
		usuario = uDao.getUsuario(cpf, senha);
		if (usuario == null) {
			usuario = new Usuario();
			Messages.addGlobalError("Usuário não encontrado!");
			return null;
		} else {
			if (cpf.equals(usuario.getCpf()) && senha.equals(usuario.getSenha())) {
				System.out.println(usuario.getNome());
				return "/pages/index.xhtml?faces-redirect=true";
			}
		}
		return null;

	}

	public String logout() {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		usuario = null;
		return "/login?faces-redirect=true";
	}

	// ***//***//***//**
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

}

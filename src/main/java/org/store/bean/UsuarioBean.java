package org.store.bean;

import java.io.Serializable;

import javax.faces.context.FacesContext;

import javax.servlet.http.HttpSession;

import org.omnifaces.util.Messages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;
import org.store.dao.UsuarioDAO;
import org.store.entity.Usuario;

@Component
@SessionScope
public class UsuarioBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@Autowired
	private UsuarioDAO uDao;

	private Usuario usuario = new Usuario();

	private String cpf;

	private String senha;

	// Logar
	public String logar() {
		try {
			usuario = uDao.getUsuario(cpf, senha);
			if (usuario != null) {
				HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext()
						.getSession(false);
				session.setAttribute("usuarioLogado", usuario); // Cria uma Sessão Valida para o Usuario
				
				return "/pages/listagem?faces-redirect=true";
			}
			return null;
		} catch (Throwable e) {
			Messages.addGlobalError("Usuário não encontrado!");
			e.printStackTrace();			
			return null;
		}
	}

	public String logout() {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
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

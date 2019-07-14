package org.store.bean;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.omnifaces.util.Messages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.annotation.SessionScope;
import org.store.dao.UsuarioDAO;
import org.store.entity.Usuario;

@SessionScope
@Controller
public class UsuarioBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@Autowired
	private UsuarioDAO uDao;

	private Usuario usuario;

	
	public UsuarioBean() {
		this.usuario = new Usuario();
	}


	// Logar
	public String logar() {
		//Usuario user = new Usuario();
		
		try {
			uDao.getUsuario(usuario);
			
			System.out.println("Usuario: " + usuario.getNome());
			
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


}

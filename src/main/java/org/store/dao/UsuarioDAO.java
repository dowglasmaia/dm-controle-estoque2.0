package org.store.dao;

import javax.ejb.Stateless;

import org.store.entity.Usuario;

@Stateless
public class UsuarioDAO extends GenericDao<Usuario> {
	private static final long serialVersionUID = 1L;

	public UsuarioDAO() {
		classPersistente = Usuario.class;
	}

	// Buscar Usuario com Base no cpf e senha
	public Usuario getUsuario(String cpf, String senha) {
		try {
			Usuario usuario = (Usuario) getEm()
					.createQuery("SELECT u from Usuario u where u.cpf = :cpf and u.senha = :senha")
					.setParameter("cpf", cpf).setParameter("senha", senha).getSingleResult();
			return usuario;
		} catch (Throwable e) {
			e.printStackTrace();
			return null;

		}

	}

}
package org.store.dao;

import org.springframework.stereotype.Component;
import org.store.entity.Usuario;

@Component
public class UsuarioDAO extends GenericDAO<Usuario> {
	private static final long serialVersionUID = 1L;

	public UsuarioDAO() {
		classPersistente = Usuario.class;
	}

	// Buscar Usuario com Base no cpf e senha
	public Usuario getUsuario(String cpf, String senha) throws Throwable {

		return null;

	}

}
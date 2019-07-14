package org.store.dao;

import org.hibernate.Session;
import org.springframework.stereotype.Component;
import org.store.entity.Usuario;
import org.store.util.HibernateUtil;

@Component
public class UsuarioDAO extends GenericDAO<Usuario> {
	private static final long serialVersionUID = 1L;

	public UsuarioDAO() {
		classPersistente = Usuario.class;
	}

	// Buscar Usuario com Base no cpf e senha
	public Usuario getUsuario(Usuario usuario) throws Throwable {
		Session session = HibernateUtil.getSessionFactory().openSession();
		String hql = "select u from Usuario u where u.cpf = :cpf and u.senha = :senha";
		
		return  (Usuario) session.createQuery(hql)
				.setParameter("cpf",usuario.getCpf())
				.setParameter("senha", usuario.getSenha()).uniqueResult();

	}

}
package org.store.test;


import org.hibernate.Session;
import org.store.dao.UsuarioDAO;
import org.store.entity.Usuario;
import org.store.util.HibernateUtil;

public class Tests {

	
	
	public static void main(String[] args) throws Throwable {
		
	/*	 */
		UsuarioDAO userB = new UsuarioDAO();
		
		Usuario u = new Usuario();
		u.setCpf("120");
		u.setEmail("dow@live.com.ma");
		u.setMatricula(1200);
		u.setNome("Dowglas Maia 35000 iuiuiu");
		u.setSenha("120");
		
		 userB.save(u);
		
		 userB.getUsuario(u);
		 
		 System.out.println("Usuario: " + u.getNome());
		 
	
		
	}
	
	
	// Buscar Usuario com Base no cpf e senha
	public Usuario getUsuario(Usuario usuario) throws Throwable {
		Session session = HibernateUtil.getSessionFactory().openSession();
		String hql = " form Usuario u where u.cpf = :cpf and u.senha = :senha";
		
		return (Usuario) session.createQuery(hql)
				.setParameter("cpf",usuario.getCpf())
				.setParameter("senha", usuario.getSenha()).uniqueResult();

	}


}

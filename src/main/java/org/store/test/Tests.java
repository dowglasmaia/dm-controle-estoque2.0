package org.store.test;


import org.store.dao.UsuarioDAO;
import org.store.entity.Usuario;

public class Tests {

	
	
	public static void main(String[] args) throws Exception {
		
		
		UsuarioDAO userB = new UsuarioDAO();
		
		Usuario u = new Usuario();
		u.setCpf("120");
		u.setEmail("dow@live.com");
		u.setMatricula(120);
		u.setNome("Dowglas Maia");
		u.setSenha("120");
		
		 userB.save(u);
		
	}

}

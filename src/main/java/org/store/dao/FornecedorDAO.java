package org.store.dao;



import org.springframework.stereotype.Component;
import org.store.entity.Fornecedor;

/*
 * Autor: Dowglas Maia
 * Skype: live:dowglasmaia
 * E-mail:dowglasmaia@live.com
 * 
 * */

@Component
public class FornecedorDAO extends GenericDAO<Fornecedor>{
	private static final long serialVersionUID = 1L;

	public FornecedorDAO() {
		super();
		classPersistente = Fornecedor.class;
	}

}

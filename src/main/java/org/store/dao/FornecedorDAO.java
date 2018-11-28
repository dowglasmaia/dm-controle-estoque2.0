package org.store.dao;

import javax.ejb.Stateless;

import org.store.entity.Fornecedor;

/*
 * Autor: Dowglas Maia
 * Skype: live:dowglasmaia
 * E-mail:dowglasmaia@live.com
 * 
 * */

@Stateless
public class FornecedorDAO extends GenericDao<Fornecedor>{
	private static final long serialVersionUID = 1L;

	public FornecedorDAO() {
		super();
		classPersistente = Fornecedor.class;
	}

}

package org.store.dao;

import javax.ejb.Stateless;

import org.store.entity.Produto;

/*
 * Autor: Dowglas Maia
 * Skype: live:dowglasmaia
 * E-mail:dowglasmaia@live.com
 * */

@Stateless
public class ProdutoDAO extends GenericDao<Produto> {
	private static final long serialVersionUID = 1L;

	public ProdutoDAO() {
		super();
		classPersistente = Produto.class;
	}

}

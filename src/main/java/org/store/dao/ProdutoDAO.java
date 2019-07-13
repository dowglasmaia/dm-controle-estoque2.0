package org.store.dao;



import org.springframework.stereotype.Component;
import org.store.entity.Produto;

/*
 * Autor: Dowglas Maia
 * Skype: live:dowglasmaia
 * E-mail:dowglasmaia@live.com
 * */

@Component
public class ProdutoDAO extends GenericDAO<Produto>{
	private static final long serialVersionUID = 1L;

	public ProdutoDAO() {
		super();
		classPersistente = Produto.class;
	}

}

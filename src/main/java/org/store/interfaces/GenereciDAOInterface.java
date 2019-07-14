package org.store.interfaces;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

public interface GenereciDAOInterface<E> extends Serializable {
	
	E save(E obj) throws Exception;

	E update(E obj) throws Exception;

	void delete(E obj) throws Exception;

	E findById(Long id) throws Exception;

	List<E> FindAll() throws Exception;

	List<E> findByName(String nome) throws Exception;
	
	E findByLogin(String docCredencial, String senha);

	List<E> findByDate(LocalDate entrada, LocalDate saida) throws Exception;

}

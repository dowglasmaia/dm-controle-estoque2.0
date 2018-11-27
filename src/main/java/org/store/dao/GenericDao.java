package org.store.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.store.interfaces.GenericDAO;

@SuppressWarnings("serial")
public class GenericDao<E> implements GenericDAO<E>{

	@PersistenceContext
	private EntityManager em;

	protected EntityManager getEm() {
		return em;
	}

	// Captuando a Class de Instancia
	protected Class classPersistente;

	public GenericDao() {

	}

	@Override
	@Transactional(rollbackOn = { Exception.class })
	public void save(E entidade) throws Exception {
		em.persist(entidade);
		em.flush();
	}

	@Override
	@Transactional(rollbackOn = { Exception.class })
	public void update(E entidade) throws Exception {
		em.merge(entidade);
		em.flush();

	}

	@Override
	@Transactional(rollbackOn = { Exception.class })
	public void remove(E entidade) throws Exception {
		entidade = em.merge(entidade);
		em.remove(entidade);
		em.flush();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<E> findAll() throws Exception {
		String jpql = "from " + classPersistente.getSimpleName();
		return em.createQuery(jpql).getResultList();
	}

	// consulta dinamica
	protected List<E> createQuery(String jpql, Object... params) {
		TypedQuery<E> query = em.createQuery(jpql, classPersistente);
		for (int i = 0; i < params.length; i++) {
			query.setParameter(i + 1, params[i]);
		}
		return query.getResultList();
	}

	@Override
	public E findById(Integer id) throws Exception {
		return (E) em.find(classPersistente, id);
	}

}

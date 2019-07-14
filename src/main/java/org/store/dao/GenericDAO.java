package org.store.dao;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.store.interfaces.GenereciDAOInterface;
import org.store.util.HibernateUtil;

public class GenericDAO<E> implements GenereciDAOInterface<E> {
	private static final long serialVersionUID = 1L;

	// Captuando a Class de Instancia
	protected Class classPersistente;

	/* == salvar == */
	@Override
	public E save(E obj) throws Exception {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.save(obj);
			session.flush();
			tx.commit();
			return obj;
		} catch (RuntimeException e) {
			e.printStackTrace();
			if (tx != null) {
				tx.rollback();
			}
			throw e;
		} finally {
			session.clear();
			session.close();
		}
	}

	/* Update */
	@Override
	public E update(E obj) throws Exception {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.merge(obj);
			session.flush();
			tx.commit();
			return obj;
		} catch (RuntimeException e) {
			e.printStackTrace();
			if (tx != null) {
				tx.rollback();
			}
			throw e;
		} finally {
			session.clear();
			session.close();
		}
	}

	/* Delete */
	@Override
	public void delete(E obj) throws Exception {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			session.remove(obj);
			session.flush();
			tx.commit();

		} catch (RuntimeException e) {
			e.printStackTrace();
			if (tx != null) {
				tx.rollback();
			}
			throw e;
		} finally {
			session.clear();
			session.close();
		}
	}

	/* Find by ID */
	@SuppressWarnings("unchecked")
	@Override
	public E findById(Long id) throws Exception {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			return (E) session.find(classPersistente, id);
		} catch (RuntimeException e) {
			e.printStackTrace();
			throw e;
		} finally {
			session.close();
		}
	}

	/* Find All */
	@SuppressWarnings({ "unchecked", "deprecation" })
	@Override
	public List<E> FindAll() throws Exception {

		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			Criteria consulta = session.createCriteria(classPersistente);

			List<E> result = consulta.list();
			return result;
		} catch (RuntimeException e) {
			e.printStackTrace();
			throw e;
		} finally {
			session.close();
		}
	}

	/* Find by Name */
	@Override
	public List<E> findByName(String nome) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	/* Find by Date */
	@Override
	public List<E> findByDate(LocalDate entrada, LocalDate saida) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E findByLogin(String docCredencial, String senha) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		
		return null;
	}

}






























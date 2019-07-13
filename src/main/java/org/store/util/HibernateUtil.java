package org.store.util;

import java.io.Serializable;

import javax.faces.bean.ApplicationScoped;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Component;

@Component
@ApplicationScoped
public class HibernateUtil implements Serializable {
	private static final long serialVersionUID = 1L;

	// Criando a fabrica de Sess�o de pega os Dados do hibernate.cfg.xml
	protected static SessionFactory criandoSessionFactory() {
		try {
			Configuration configuration = new Configuration().configure();

			SessionFactory factory = configuration.buildSessionFactory();

			return factory;
		} catch (Throwable erro) {
			System.out.println("A Fabrica de Sessão não Pode ser Criada! " + erro);
			throw new ExceptionInInitializerError(erro);
		}
	}

	// metodo statico que recebi os dados do metodo que cria a Fabrica de Sess�es
	private static SessionFactory sessionFactory = criandoSessionFactory();

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	/* abre uma nova sess�o no SessionFactory e retorna uma Session */
	public static Session openSession() {
		if (sessionFactory == null) {
			criandoSessionFactory();
		}
		return sessionFactory.openSession();
	}

}

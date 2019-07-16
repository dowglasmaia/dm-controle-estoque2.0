package org.store.util;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/*
 * Class para Criar a Fabrica de Sessão, ao Carregar o Toncat
 * */

public class ContextListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		HibernateUtil.getSessionFactory().openSession();

	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		HibernateUtil.getSessionFactory().close();
	}

}

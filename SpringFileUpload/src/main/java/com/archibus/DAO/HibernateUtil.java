package com.archibus.DAO;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
	private static final SessionFactory sessionFactory;

	static {
		try {
			
			/**
			 * Create the SessionFactory from hibernate.cfg.xml
			 */
			sessionFactory = new Configuration().configure().buildSessionFactory();
		} catch (Throwable ex) {

			/**
			 * Logging the exception, as it might be swallowed
			 */
			
			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
}

package com.archibus.DAO;

import org.hibernate.Session;

/**
 * Creates a session and its session factory object to open a session to store
 * file in database
 */
public class DAO {

	public Session getSession() {
		return HibernateUtil.getSessionFactory().openSession();
	}
}

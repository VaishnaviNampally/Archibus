package com.archibus.DAO;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.archibus.model.FileUploadForm;

@Repository
public class FileUploadDAO extends DAO {

	/**
	 * Method to upload a file to database Opens a session Begins a transaction
	 * save() method stores the file to the database commits the transaction and
	 * then the session is closed
	 */
	public void addFile(FileUploadForm file) {

		try {

			Session session = getSession();
			Transaction transaction = session.beginTransaction();

			session.save(file);

			transaction.commit();
			session.close();
		} catch (Exception e) {
			System.out.println("cannot add file:" + e.getMessage());
		}
	}

	/**
	 * Getting a file with user specified fileID uploaded to database Opens a
	 * session Begins a transaction Query gets the file with the particular ID
	 * uniqueResult() method gets one single file from database commits the
	 * transaction and then the session is closed
	 */

	public FileUploadForm getFile(int id) {

		Session session = getSession();
		Transaction transaction = session.beginTransaction();

		Query q = session.createQuery("from FileUploadForm where id=:id");
		q.setInteger("id", id);
		FileUploadForm p = (FileUploadForm) q.uniqueResult();

		transaction.commit();
		session.close();

		return p;

	}

	/**
	 * Getting all the files uploaded to database in a list Opens a session
	 * Begins a transaction Query gets all the files stored in database list()
	 * method gets all the files in a list commits the transaction and then the
	 * session is closed
	 */
	public List<FileUploadForm> getAllFiles() {
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		Query q = session.createQuery("from FileUploadForm");
		List list = q.list();
		transaction.commit();
		session.close();
		return list;
	}

}

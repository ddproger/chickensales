package ua.goryainov.hibernate.dao;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import ua.goryainov.hibernate.model.Administration;

public class AdministrationDaoImpl implements TablesDao<Administration, String> {
	private Session currentSession;
	private Transaction currentTransaction;

	public AdministrationDaoImpl() {
	}

	public Session openCurrentSession() {
		currentSession = getSessionFactory().openSession();
		return currentSession;
	}

	public Session openCurrentSessionwithTransaction() {
		currentSession = getSessionFactory().openSession();
		currentTransaction = currentSession.beginTransaction();
		return currentSession;
	}

	public void closeCurrentSession() {
		currentSession.close();
	}

	public void closeCurrentSessionwithTransaction() {
		currentTransaction.commit();
		currentSession.close();
	}

	private static SessionFactory getSessionFactory() {
		Configuration configuration = new Configuration().configure();
		StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
				.applySettings(configuration.getProperties());
		SessionFactory sessionFactory = configuration.buildSessionFactory(builder.build());
		return sessionFactory;
	}

	public Session getCurrentSession() {
		return currentSession;
	}

	public void setCurrentSession(Session currentSession) {
		this.currentSession = currentSession;
	}

	public Transaction getCurrentTransaction() {
		return currentTransaction;
	}

	public void setCurrentTransaction(Transaction currentTransaction) {
		this.currentTransaction = currentTransaction;
	}

	public void persist(Administration entity) {
		getCurrentSession().save(entity);
	}

	public void update(Administration entity) {
		getCurrentSession().update(entity);
	}



	public void delete(Administration entity) {
		getCurrentSession().delete(entity);
	}

	@SuppressWarnings("unchecked")
	public List<Administration> findAll() {
		List<Administration> admins = (List<Administration>) getCurrentSession().createQuery("from Administrator").list();
		return admins;
	}

	public void deleteAll() {
		List<Administration> entityList = findAll();
		for (Administration entity : entityList) {
			delete(entity);
		}
	}
	public Administration findById(String login) {
		// TODO Auto-generated method stub
		Administration admin = (Administration) getCurrentSession().get(Administration.class, login);
		return admin;
	}	
}
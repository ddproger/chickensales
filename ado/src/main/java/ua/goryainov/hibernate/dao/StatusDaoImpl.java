package ua.goryainov.hibernate.dao;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import ua.goryainov.hibernate.model.Status;


public class StatusDaoImpl implements TablesDao<Status, Integer> {
	private Session currentSession;
	private Transaction currentTransaction;

	public StatusDaoImpl() {
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

	public void persist(Status entity) {
		getCurrentSession().save(entity);
	}

	public void update(Status entity) {
		getCurrentSession().update(entity);
	}



	public void delete(Status entity) {
		getCurrentSession().delete(entity);
	}

	@SuppressWarnings("unchecked")
	public List<Status> findAll() {
		List<Status> admins = (List<Status>) getCurrentSession().createQuery("from Status").list();
		return admins;
	}

	public void deleteAll() {
		List<Status> entityList = findAll();
		for (Status entity : entityList) {
			delete(entity);
		}
	}
	public Status findById(Integer id) {
		// TODO Auto-generated method stub
		Status status = (Status) getCurrentSession().get(Status.class, id);
		return status;
	}	
}
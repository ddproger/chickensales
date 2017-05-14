package ua.goryainov.hibernate.dao;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;


import ua.goryainov.hibernate.model.ActionType;

public class ActionTypeDaoImpl implements TablesDao<ActionType, Integer> {
	private Session currentSession;
	private Transaction currentTransaction;

	public ActionTypeDaoImpl() {
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

	public void persist(ActionType entity) {
		getCurrentSession().save(entity);
	}

	public void update(ActionType entity) {
		getCurrentSession().update(entity);
	}



	public void delete(ActionType entity) {
		getCurrentSession().delete(entity);
	}

	@SuppressWarnings("unchecked")
	public List<ActionType> findAll() {
		List<ActionType> actions = (List<ActionType>) getCurrentSession().createQuery("from ActionType").list();
		return actions;
	}

	public void deleteAll() {
		List<ActionType> entityList = findAll();
		for (ActionType entity : entityList) {
			delete(entity);
		}
	}
	public ActionType findById(Integer id) {
		ActionType actions = (ActionType) getCurrentSession().get(ActionType.class, id);
		return actions;
	}	
}
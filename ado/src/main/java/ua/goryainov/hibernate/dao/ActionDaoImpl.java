package ua.goryainov.hibernate.dao;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import ua.goryainov.hibernate.model.Action;

public class ActionDaoImpl implements TablesDao<Action, Integer> {
	private Session currentSession;
	private Transaction currentTransaction;

	public ActionDaoImpl() {
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

	public void persist(Action entity) {
		getCurrentSession().save(entity);
	}

	public void update(Action entity) {
		getCurrentSession().update(entity);
	}



	public void delete(Action entity) {
		getCurrentSession().delete(entity);
	}

	@SuppressWarnings("unchecked")
	public List<Action> findAll() {
		List<Action> actions = (List<Action>) getCurrentSession().createQuery("from Action").list();
		return actions;
	}
	@SuppressWarnings("unchecked")
	public List<Action> findByUser(int UserId) {
		List<Action> actions = (List<Action>) getCurrentSession().createQuery("from Action a WHERE a.user.userId="+UserId).list();
		return actions;
	}

	public void deleteAll() {
		List<Action> entityList = findAll();
		for (Action entity : entityList) {
			delete(entity);
		}
	}
	public Action findById(Integer id) {
		Action actions = (Action) getCurrentSession().get(Action.class, id);
		return actions;
	}	
}
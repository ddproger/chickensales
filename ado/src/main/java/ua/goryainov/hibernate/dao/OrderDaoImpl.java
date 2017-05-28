package ua.goryainov.hibernate.dao;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import ua.goryainov.hibernate.model.Commission;

public class OrderDaoImpl implements TablesDao<Commission, Integer> {
	private Session currentSession;
	private Transaction currentTransaction;

	public OrderDaoImpl() {
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

	public void persist(Commission entity) {
		getCurrentSession().save(entity);
	}

	public void update(Commission entity) {
		getCurrentSession().update(entity);
	}



	public void delete(Commission entity) {
		getCurrentSession().delete(entity);
	}

	@SuppressWarnings("unchecked")
	public List<Commission> findAll() {
		List<Commission> orders = (List<Commission>) getCurrentSession().createQuery("from Commission c ORDER BY c.date DESC").list();
		return orders;
	}
	@SuppressWarnings("unchecked")
	public List<Commission> findByUser(int userId) {
		List<Commission> orders = (List<Commission>) getCurrentSession().createQuery("from Commission c WHERE c.user.userId="+userId+" ORDER BY c.date DESC").list();
		return orders;
	}

	public void deleteAll() {
		List<Commission> entityList = findAll();
		for (Commission entity : entityList) {
			delete(entity);
		}
	}
	public Commission findById(Integer id) {
		// TODO Auto-generated method stub
		Commission order = (Commission) getCurrentSession().get(Commission.class, id);
		return order;
	}

    public List<?> findGrouped(int userId) {
		List<?> lst = getCurrentSession().createSQLQuery("Select c.date as date, sum(op.count) as count, sum(op.count*op.price) as sum from user as u " +
														"right join commission as c on u.userid = c.userid " +
														"right join orderproduct as op on op.orderid=c.orderid " +
														"where u.userid="+userId+
														" group by c.date").list();
		return lst;
	}
}
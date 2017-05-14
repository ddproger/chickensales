package ua.goryainov.hibernate.dao;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import ua.goryainov.hibernate.model.OrderProduct;

public class OrderProductDaoImpl implements TablesDao<OrderProduct, Integer> {
	private Session currentSession;
	private Transaction currentTransaction;

	public OrderProductDaoImpl() {
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

	public void persist(OrderProduct entity) {
		getCurrentSession().save(entity);
	}

	public void update(OrderProduct entity) {
		getCurrentSession().update(entity);
	}



	public void delete(OrderProduct entity) {
		getCurrentSession().delete(entity);
	}

	@SuppressWarnings("unchecked")
	public List<OrderProduct> findAll() {
		List<OrderProduct> orderProducts = (List<OrderProduct>) getCurrentSession().createQuery("from OrderProduct").list();
		return orderProducts;
	}
	@SuppressWarnings("unchecked")
	public List<OrderProduct> findByUser(int userId) {
		List<OrderProduct> orderProducts = (List<OrderProduct>) getCurrentSession().createQuery("from OrderProduct o WHERE o.user.userId="+userId).list();
		return orderProducts;
	}

	public void deleteAll() {
		List<OrderProduct> entityList = findAll();
		for (OrderProduct entity : entityList) {
			delete(entity);
		}
	}
	public OrderProduct findById(Integer id) {
		// TODO Auto-generated method stub
		OrderProduct orderProducts = (OrderProduct) getCurrentSession().get(OrderProduct.class, id);
		return orderProducts;
	}	
}